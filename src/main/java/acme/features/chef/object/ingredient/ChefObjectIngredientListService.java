package acme.features.chef.object.ingredient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefObjectIngredientListService implements AbstractListService<Chef, Object>{

	@Autowired
	protected ChefObjectIngredientRepository repo;
	
	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public Collection<Object> findMany(final Request<Object> request) {
		assert request != null;
		int chefId;
		Collection<Object> ingredients;
		
		chefId = request.getPrincipal().getActiveRoleId();
		ingredients = this.repo.findManyChefIngredients(chefId);
		
		return ingredients;
		
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "published");
		
	}

	

}
