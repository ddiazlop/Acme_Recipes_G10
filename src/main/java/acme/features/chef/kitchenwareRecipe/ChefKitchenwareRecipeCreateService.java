package acme.features.chef.kitchenwareRecipe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.entities.recipes.UnitType;
import acme.entities.recipes.WareType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeCreateService implements AbstractCreateService<Chef, KitchenwareRecipe>{
	// Internal state --------------------------------------------------------------------

		@Autowired
		protected ChefKitchenwareRecipeRepository repository;
		
		// AbstractCreateService<Chef, KitchenwareRecipe> interface --------------------------------

		@Override
		public boolean authorise(final Request<KitchenwareRecipe> request) {
			assert request != null;
			final Recipe recipe=this.repository.findRecipeById(request.getModel().getInteger("recipeId"));
			return recipe.getChef().getId()==request.getPrincipal().getActiveRoleId()  && !recipe.isPublished();
		}

		@Override
		public KitchenwareRecipe instantiate(final Request<KitchenwareRecipe> request) {
			final Recipe recipe=this.repository.findRecipeById(request.getModel().getInteger("recipeId"));
			final KitchenwareRecipe it= new KitchenwareRecipe();
				it.setRecipe(recipe);
		
			return it;
		}

		@Override
		public void bind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if (request.getModel().getString("type").equals("INGREDIENT")) {
				request.bind(entity, errors, "quantity", "unitType");
			}else {
				request.bind(entity, errors, "quantity");
			}
			
			final String kitchenwareCode= request.getModel().getString("kitchenwareCode");
			final Kitchenware kitchenware = this.repository.findKitchenwareByCode(kitchenwareCode);
			if (kitchenware == null) {
				errors.state(request, false, "kitchenwareCode", "chef.kitchenware-recipe.create.errors.null-kitchenware");
			}else {
				entity.setKitchenware(kitchenware);
			}
			
		}
		
		@Override
		public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "quantity", "unitType");
			
			
			final Integer recipeId = request.getModel().getInteger("recipeId");
			
			
			if (entity.getKitchenware() == null) {
				model.setAttribute("kitchenwareCode", "");
			}else {
				model.setAttribute("kitchenwareCode", entity.getKitchenware().getCode());
			}
			
			model.setAttribute("recipe", this.repository.findRecipeById(recipeId));
			model.setAttribute("readOnly", false);
			try {
				final Integer kitchenwareId = entity.getKitchenware().getId();
				model.setAttribute("previd", kitchenwareId);
				model.setAttribute("prevType", entity.getKitchenware().getWareType().toString());
			} catch (final NullPointerException e) {
				model.setAttribute("previd", "");
			}
			
			for (int i = 0; i < UnitType.values().length; i++) {
				final UnitType unitType = UnitType.values()[i];
				model.setAttribute("enum" + unitType, unitType);
			}
			
			if (request.getModel().getString("type").equals("INGREDIENT")) {
				model.setAttribute("type", "INGREDIENT");
			}else {
				model.setAttribute("type", "KITCHEN_UTENSIL");
			}
				
		}

		@Override
		public void validate(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
			
			if (!errors.hasErrors("kitchenwareCode")) {
				errors.state(request, entity.getKitchenware().isPublished(), "kitchenwareCode", "chef.kitchenware-recipe.form.error.kitchenware-not-published");
				if (request.getModel().getAttribute("type").equals("INGREDIENT")) {
					errors.state(request, entity.getKitchenware().getWareType().equals(WareType.INGREDIENT), "kitchenwareCode", "chef.kitchenware-recipe.form.error.not-an-ingredient");
				}else {
					errors.state(request, entity.getKitchenware().getWareType().equals(WareType.KITCHEN_UTENSIL), "kitchenwareCode", "chef.kitchenware-recipe.form.error.not-an-utensil");
				}
			}
			
			if (!errors.hasErrors("unitType")) {
				if (request.getModel().getAttribute("type").equals("INGREDIENT")) {
					errors.state(request, entity.getUnitType() != null, "unitType", "chef.kitchenware-recipe.form.error.unit-not-selected");
				}else {
					errors.state(request, entity.getUnitType() == null, "*", "chef.kitchenware-recipe.form.error.no-unit-for-utensils");
				}
			}
			
			
			
			if(!errors.hasErrors("*")) {
				final Kitchenware existing = this.repository.findKitchenwareByIdInRecipe(entity.getKitchenware().getId(), entity.getRecipe().getId());
				errors.state(request, existing==null, "*", "chef.kitchenware-recipe.form.error.kitchenware-already-added");
				
				
			}
			
		}

		@Override
		public void create(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity) {
			assert request != null;
			assert entity != null;
			
			this.repository.save(entity);

		}

		

}
