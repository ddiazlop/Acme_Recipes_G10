package acme.features.chef.ingredients;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefIngredientListService implements AbstractListService<Chef, Ingredient>{

	@Autowired
	protected ChefIngredientRepository repository;
	
	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Ingredient> findMany(final Request<Ingredient> request) {
		assert request != null;
		
		Collection<Ingredient> result;
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		result = this.repository.findAllIngredientsFromChefId(chefId);
		return result;
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "description", "retailPrice", "info");
		
	}

}
