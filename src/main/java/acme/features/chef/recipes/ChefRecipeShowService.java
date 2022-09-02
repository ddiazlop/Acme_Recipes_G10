package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe>{

	@Autowired
	private ChefRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService	moneyExchange;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		final boolean result;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int recipeId = request.getModel().getInteger("id");	
		final Recipe recipe = this.repository.findOneRecipeById(recipeId);
		
		result = (recipe.getChef().getId() == chefId);
		return result;
		
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		return recipe;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Integer recipeId = request.getModel().getInteger("id");
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		
		final List<Kitchenware> kitchenwares = (List<Kitchenware>) this.repository.getKitchenwaresOfARecipe(recipeId);
		final List<Money> convertedPrices = new ArrayList<>();
		
		for(final Kitchenware k : kitchenwares) {
			convertedPrices.add(k.getRetailPrice());
		}
		
		
		this.moneyExchange.convertMoney(convertedPrices, sc.getSystemCurrency());
		Double totalAmount = 0.0;
		for(int i = 0; i < convertedPrices.size(); i++) {
			final Kitchenware kitchenware = kitchenwares.get(i);
			final Double quantity = this.repository.getKitchenwareQuantityFromARecipe(recipeId, kitchenware.getId());
			totalAmount = totalAmount + quantity*convertedPrices.get(i).getAmount();
		}
		
		final Money totalPrice = new Money();
		totalPrice.setAmount(totalAmount);
		totalPrice.setCurrency(sc.getSystemCurrency());
		
		model.setAttribute("totalPrice", totalPrice);
		request.unbind(entity, model, "code", "heading", "description", "info", "preparationNotes", "published");
	
		if (entity.isPublished()) {
			model.setAttribute("state", "PUBLISHED");
		} else {
			model.setAttribute("state", "NOT PUBLISHED");
		}
	}

}
