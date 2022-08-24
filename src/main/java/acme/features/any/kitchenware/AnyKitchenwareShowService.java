package acme.features.any.kitchenware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyKitchenwareShowService implements AbstractShowService<Any, Kitchenware>{

	@Autowired
	protected AnyKitchenwareRepository repo;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int ingredientId;
		Kitchenware ingredient;
		boolean res;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneKitchenwareById(ingredientId);
		res = ingredient.isPublished();
		return res;
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int ingredientId;
		Kitchenware ingredient;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneKitchenwareById(ingredientId);
		
		return ingredient;
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info", "published");
		
		
	}

	

}
