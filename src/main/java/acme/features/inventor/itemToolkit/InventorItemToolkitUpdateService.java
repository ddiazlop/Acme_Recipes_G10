package acme.features.inventor.itemToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemToolkitUpdateService implements AbstractUpdateService<Inventor, ItemToolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemToolkitRepository repository;

	
	// AbstractUpdateService<Inventor, ItemToolkit> interface -------------------------
	
	@Override
	public boolean authorise(final Request<ItemToolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final ItemToolkit it=this.repository.findItemToolkitById(id);
		return it.getToolkit().getInventor().getId()==request.getPrincipal().getActiveRoleId() && !it.getToolkit().isPublished();

	}

	@Override
	public void bind(final Request<ItemToolkit> request, final ItemToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "quantity");
		
	}

	@Override
	public void unbind(final Request<ItemToolkit> request, final ItemToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "item.name");
		model.setAttribute("published", entity.getToolkit().isPublished());
		
	}

	@Override
	public ItemToolkit findOne(final Request<ItemToolkit> request) {
		assert request != null;
		
		final ItemToolkit it;
		final int id = request.getModel().getInteger("id");
		it=this.repository.findItemToolkitById(id);
		return it;
	}

	@Override
	public void validate(final Request<ItemToolkit> request, final ItemToolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("quantity")) {
			errors.state(request, entity.getItem().getItemType()!=ItemType.TOOL||entity.getQuantity()==1, "quantity", "inventor.item-toolkit.form.error.wrong-tool-quantity");
		}
	}

	@Override
	public void update(final Request<ItemToolkit> request, final ItemToolkit entity) {
		assert request != null;
		assert entity != null;
		
		
		
		this.repository.save(entity);
		
	}

}
