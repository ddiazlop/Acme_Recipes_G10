package acme.features.chef.object.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefObjectIngredientDeleteService implements AbstractDeleteService<Chef, Object>{
	
	@Autowired
	protected ChefObjectIngredientRepository repo;

	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		int chefId;
		int ingredientId;
		Object ingredient;
		
		chefId = request.getPrincipal().getActiveRoleId();
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient.getChef().getId() == chefId && !ingredient.isPublished();
	}

	@Override
	public void bind(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "objectType", "description", "retailPrice", "info", "published");
		
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "objectType", "description", "retailPrice", "info", "published");
		
	}

	@Override
	public Object findOne(final Request<Object> request) {
		assert request != null;
		int id;
		Object ingredient;
		id = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(id);
		
		return ingredient;
	}

	@Override
	public void validate(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Object> request, final Object entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.delete(entity);
		
	}

	

}
