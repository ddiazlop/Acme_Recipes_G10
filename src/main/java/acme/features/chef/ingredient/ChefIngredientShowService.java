package acme.features.chef.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefIngredientShowService implements AbstractShowService<Chef, Ingredient>{

	@Autowired
	protected ChefIngredientRepository repo;
	
	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;
		int chefId;
		int ingredientId;
		Ingredient ingredient;
		
		chefId = request.getPrincipal().getActiveRoleId();
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient.getChef().getId() == chefId;
	}

	@Override
	public Ingredient findOne(final Request<Ingredient> request) {
		assert request != null;
		int id;
		Ingredient ingredient;
		id = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(id);
		
		return ingredient;
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "description", "retailPrice", "info", "published");
		
	}

	

}
