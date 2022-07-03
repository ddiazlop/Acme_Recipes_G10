package acme.features.inventor.itemToolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ItemToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemToolkitShowService implements AbstractShowService<Inventor, ItemToolkit>{

	// Internal State -------------------------------------------------------------------
	
	@Autowired
	protected InventorItemToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<ItemToolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final ItemToolkit it=this.repository.findItemToolkitById(id);
		return it.getToolkit().getInventor().getId()==request.getPrincipal().getActiveRoleId();

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
	public void unbind(final Request<ItemToolkit> request, final ItemToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "item.name");
		model.setAttribute("published", entity.getToolkit().isPublished());
	}
	

}
