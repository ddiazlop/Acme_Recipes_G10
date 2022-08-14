package acme.features.chef.recipes;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefRecipeListService implements AbstractListService<Chef, Recipe>{

	@Autowired
	private ChefRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService	moneyExchange;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		
		assert request != null;
		boolean result;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<Recipe> recipes = this.repository.findRecipesByChefId(chefId);
		
		result = recipes.stream().allMatch(e -> e.getChef().getId() == chefId);
		return result;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<Recipe> recipes = this.repository.findRecipesByChefId(chefId);
		
		return recipes;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description");
		
		if (entity.isPublished()) {
			model.setAttribute("state", "PUBLISHED");
		} else {
			model.setAttribute("state", "NOT PUBLISHED");
		}
		
	}

}
