
package acme.features.any.itemToolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.ItemToolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyItemFromToolkitListService implements AbstractListService<Any, ItemToolkit> {

	@Autowired
	protected AnyItemToolkitRepository repository;


	@Override
	public boolean authorise(final Request<ItemToolkit> request) {
		assert request != null;
		final int toolkitId = request.getModel().getInteger("toolkitId");

		return this.repository.findToolkitById(toolkitId).isPublished();
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
	}

}
