package acme.features.chef.kitchenware;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.WareType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareDeleteService implements AbstractDeleteService<Chef, Kitchenware>{

	@Autowired
	protected ChefKitchenwareRepository repository;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int chefId;
		int kitchenwareId;
		Kitchenware kitchenware;
		
		chefId = request.getPrincipal().getActiveRoleId();
		kitchenwareId = request.getModel().getInteger("id");
		kitchenware = this.repository.findOneKitchenwareById(kitchenwareId);
		
		return kitchenware.getChef().getId() == chefId && !kitchenware.isPublished();
	}

	@Override
	public void bind(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "wareType", "description", "retailPrice", "info", "published");
		
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info", "published");
		model.setAttribute("IngredientType", WareType.INGREDIENT);
		model.setAttribute("KitchenUtensilType", WareType.KITCHEN_UTENSIL);
		model.setAttribute("readOnly", true);
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int id;
		Kitchenware kitchenware;
		id = request.getModel().getInteger("id");
		kitchenware = this.repository.findOneKitchenwareById(id);
		
		return kitchenware;	
		}

	@Override
	public void validate(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors("published")) {
			errors.state(request, !entity.isPublished(), "published", 
				"chef.kitchenware.form.error.delete.published-kitchenware");
		}
	}	

	@Override
	public void delete(final Request<Kitchenware> request, final Kitchenware entity) {
		assert request != null;
		assert entity != null;
		
		Collection<KitchenwareRecipe> kr;
		kr = this.repository.findManyKitchenwareRecipesByKitchenwareId(entity.getId());
		this.repository.deleteAll(kr);
		
		this.repository.delete(entity);
		
	}

	
}
