package acme.features.chef.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.WareType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeUpdateService implements AbstractUpdateService<Chef, KitchenwareRecipe>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ChefKitchenwareRecipeRepository repository;

		
		// AbstractUpdateService<Chef, KitchenwareRecipe> interface -------------------------
		
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
			
			final KitchenwareRecipe kwr;
			final int id = request.getModel().getInteger("id");
			kwr=this.repository.findKitchenwareRecipeById(id);
			return kwr;
		}

		@Override
		public void validate(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if(!errors.hasErrors("quantity")) {
				errors.state(request, entity.getKitchenware().getWareType()!=WareType.KITCHEN_UTENSIL||entity.getQuantity()==1, "quantity", "chef.kitchenware-recipe.form.error.wrong-utensil-quantity");
			}
		}

		@Override
		public void update(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity) {
			assert request != null;
			assert entity != null;
			
			
			
			this.repository.save(entity);
			
		}

}
