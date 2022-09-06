package acme.features.chef.kitchenwareRecipe;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
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
			request.bind(entity, errors, "quantity");
			
			final int kitchenwareId= request.getModel().getInteger("kitchenwareId");
			entity.setKitchenware(this.repository.findKitchenwareById(kitchenwareId));
			
		}
		
		@Override
		public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			request.unbind(entity, model, "quantity");
			
			final Integer recipeId = request.getModel().getInteger("recipeId");
			final Collection<Kitchenware> publishedKitchenwares = this.repository.findAllPublishedKitchenwares();  
			final Collection<KitchenwareRecipe> kitchenwareRecipes = this.repository.findAllKitchenwareRecipesByRecipeId(recipeId); 
			final List<Kitchenware> addedKitchenwares = kitchenwareRecipes.stream().map(KitchenwareRecipe::getKitchenware).collect(Collectors.toList());
			publishedKitchenwares.removeAll(addedKitchenwares);
			
			model.setAttribute("kitchenwares", publishedKitchenwares);
			model.setAttribute("recipe", this.repository.findRecipeById(recipeId));
			model.setAttribute("readOnly", false);
			try {
				final Integer kitchenwareId = entity.getKitchenware().getId();
				model.setAttribute("previd", kitchenwareId);
			} catch (final NullPointerException e) {
				model.setAttribute("previd", "");
			}
				
		}

		@Override
		public void validate(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Errors errors) {
			
			
			
			
			
			if(!errors.hasErrors("quantity")) {
				errors.state(request, entity.getKitchenware().getWareType()!=WareType.KITCHEN_UTENSIL||entity.getQuantity()==1
					,"quantity", "chef.kitchenware-recipe.form.error.wrong-tool-quantity");
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
