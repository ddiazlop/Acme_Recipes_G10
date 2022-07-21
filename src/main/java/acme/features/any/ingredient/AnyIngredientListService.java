package acme.features.any.ingredient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyIngredientListService implements AbstractListService<Any, Ingredient>{
	
	@Autowired
	protected AnyIngredientRepository repo;

	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Ingredient> findMany(final Request<Ingredient> request) {
		assert request != null;
		Collection<Ingredient> res;
		res = this.repo.findPublishedIngredients();
		return res;
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "published");
		
	}

	

}
