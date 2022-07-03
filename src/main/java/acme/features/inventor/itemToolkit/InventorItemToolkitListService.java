package acme.features.inventor.itemToolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemToolkitListService implements AbstractListService<Inventor, ItemToolkit>{

	@Autowired
	protected InventorItemToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<ItemToolkit> request) {
		assert request != null;
		final Toolkit toolkit=this.repository.findToolkitById(request.getModel().getInteger("toolkitId"));
		return toolkit.getInventor().getId()==request.getPrincipal().getActiveRoleId();
	
	}

	@Override
	public Collection<ItemToolkit> findMany(final Request<ItemToolkit> request) {
		assert request != null;
		Collection<ItemToolkit> result;
		final int toolkitId = request.getModel().getInteger("toolkitId");

		result = this.repository.findAllItemToolkitsByToolkitId(toolkitId);

		return result;
	}

	@Override
	public void unbind(final Request<ItemToolkit> request, final ItemToolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "quantity", "item.code", "item.name", "item.technology", "item.retailPrice", "item.itemType");
		model.setAttribute("published", entity.getToolkit().isPublished());
	}

}
