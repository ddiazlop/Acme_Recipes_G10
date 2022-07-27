package acme.features.any.object.ingredient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyObjectIngredientListService implements AbstractListService<Any, Object>{
	
	@Autowired
	protected AnyObjectIngredientRepository repo;

	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Object> findMany(final Request<Object> request) {
		assert request != null;
		Collection<Object> res;
		res = this.repo.findPublishedIngredients();
		return res;
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "published");
		
	}

	

}
