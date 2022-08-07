package acme.features.any.kitchenware.ingredient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyKitchenwareIngredientListService implements AbstractListService<Any, Kitchenware>{
	
	@Autowired
	protected AnyKitchenwareIngredientRepository repo;

	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Kitchenware> findMany(final Request<Kitchenware> request) {
		assert request != null;
		Collection<Kitchenware> res;
		res = this.repo.findPublishedIngredients();
		return res;
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "published");
		
	}

	

}
