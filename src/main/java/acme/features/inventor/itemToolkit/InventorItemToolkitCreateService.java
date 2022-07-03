package acme.features.inventor.itemToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.ItemType;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemToolkitCreateService implements AbstractCreateService<Inventor, ItemToolkit>{
	
	// Internal state --------------------------------------------------------------------

	@Autowired
	protected InventorItemToolkitRepository repository;

	
	// AbstractCreateService<Inventor, ItemToolkit> interface --------------------------------

	@Override
	public boolean authorise(final Request<ItemToolkit> request) {
		assert request != null;
		final Toolkit toolkit=this.repository.findToolkitById(request.getModel().getInteger("toolkitId"));
		return toolkit.getInventor().getId()==request.getPrincipal().getActiveRoleId()  && !toolkit.isPublished();
	}

	@Override
	public ItemToolkit instantiate(final Request<ItemToolkit> request) {
		final Toolkit toolkit=this.repository.findToolkitById(request.getModel().getInteger("toolkitId"));
		final ItemToolkit it= new ItemToolkit();
		it.setToolkit(toolkit);
		return it;
	}

	@Override
	public void bind(final Request<ItemToolkit> request, final ItemToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "quantity");
		
		final int itemId=request.getModel().getInteger("itemId");
		entity.setItem(this.repository.findItemById(itemId));
		
	}

	@Override
	public void unbind(final Request<ItemToolkit> request, final ItemToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "quantity");
		model.setAttribute("items", this.repository.findAllPublishedItems());
		model.setAttribute("toolkit", this.repository.findToolkitById(request.getModel().getInteger("toolkitId")));
	}

	@Override
	public void validate(final Request<ItemToolkit> request, final ItemToolkit entity, final Errors errors) {
		
		if(!errors.hasErrors("quantity")) {
			errors.state(request, entity.getItem().getItemType()!=ItemType.TOOL||entity.getQuantity()==1
				,"quantity", "inventor.item-toolkit.form.error.wrong-tool-quantity");
		}
		
		
		if(!errors.hasErrors("*")) {
			final Item existing = this.repository.findItemByIdInToolkit(entity.getItem().getId(), entity.getToolkit().getId());
			errors.state(request, existing==null, "*", "inventor.item-toolkit.form.error.item-already-added");
		}
		
	}

	@Override
	public void create(final Request<ItemToolkit> request, final ItemToolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);

	}

}
