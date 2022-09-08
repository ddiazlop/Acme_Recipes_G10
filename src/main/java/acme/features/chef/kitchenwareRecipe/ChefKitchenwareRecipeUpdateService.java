package acme.features.chef.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.UnitType;
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
			
			if (entity.getKitchenware().getWareType().equals(WareType.INGREDIENT)) {
				request.bind(entity, errors, "quantity", "unitType");
			}else {
				request.bind(entity, errors, "quantity");
			}
			
		}

		@Override
		public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "quantity","unitType", "kitchenware.name", "kitchenware.code", "kitchenware.description", 
                "kitchenware.retailPrice", "kitchenware.info");
			model.setAttribute("wareType", entity.getKitchenware().getWareType().name());
			model.setAttribute("published", entity.getRecipe().isPublished());
			
			for (int i = 0; i < UnitType.values().length; i++) {
				final UnitType unitType = UnitType.values()[i];
				model.setAttribute("enum" + unitType, unitType);
			}
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
			
			
			if (!errors.hasErrors("unitType")) {
				if (entity.getKitchenware().getWareType().equals(WareType.INGREDIENT)) {
					errors.state(request, entity.getUnitType() != null, "unitType", "chef.kitchenware-recipe.form.error.unit-not-selected");
				}else {
					errors.state(request, entity.getUnitType() == null, "*", "chef.kitchenware-recipe.form.error.no-unit-for-utensils");
				}
			}
			
		}

		@Override
		public void update(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity) {
			assert request != null;
			assert entity != null;
			
			
			
			this.repository.save(entity);
			
		}

}
