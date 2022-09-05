package acme.features.chef.kitchenware;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareListService implements AbstractListService<Chef, Kitchenware>{

	@Autowired
	protected ChefKitchenwareRepository repo;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public Collection<Kitchenware> findMany(final Request<Kitchenware> request) {
		assert request != null;
		int chefId;
		final Collection<Kitchenware> kitchenwares;
		
		chefId = request.getPrincipal().getActiveRoleId();
		kitchenwares = this.repo.findManyChefKitchenwares(chefId);
		
		return kitchenwares;
		
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType");
		if(entity.isPublished()) {
			model.setAttribute("published", "PUBLISHED");
		}
		else {
			model.setAttribute("published", "NOT PUBLISHED");
		}
		
	}

	

}
