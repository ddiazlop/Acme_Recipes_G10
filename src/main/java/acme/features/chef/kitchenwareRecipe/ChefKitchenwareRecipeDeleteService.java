package acme.features.chef.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.KitchenwareRecipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeDeleteService implements AbstractDeleteService<Chef, KitchenwareRecipe>{

	// Internal state --------------------------------------------------------------------

	@Autowired
	protected ChefKitchenwareRecipeRepository repository;

	
	// AbstractDeleteService<Chef, KitchenwareRecipe> interface --------------------------------

	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final KitchenwareRecipe it=this.repository.findKitchenwareRecipeById(id);
		return it.getRecipe().getChef().getId()==request.getPrincipal().getActiveRoleId() && !it.getRecipe().isPublished();

	}

	@Override
	public void bind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "quantity");
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "kitchenware.name");
		model.setAttribute("published", entity.getRecipe().isPublished());
		
	}

	@Override
	public KitchenwareRecipe findOne(final Request<KitchenwareRecipe> request) {
		assert request != null;
		
		final KitchenwareRecipe it;
		final int id = request.getModel().getInteger("id");
		it=this.repository.findKitchenwareRecipeById(id);
		return it;
	}

	@Override
	public void validate(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}

}


