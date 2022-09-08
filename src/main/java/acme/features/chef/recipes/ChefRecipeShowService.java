package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Recipe;
import acme.entities.recipes.WareType;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
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
	protected AuthenticatedMoneyExchangeSepPerformService	moneyExchange;
	
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
		int id;
		Recipe recipe;
		final int chefId = request.getPrincipal().getActiveRoleId();
		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeByIdFromChef(id,chefId);
		return recipe;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<Money> prices = new ArrayList<>();
		final SystemConfigurationSep sc = this.repository.findSystemConfigurationSep();
		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
			final Money price = new Money();
			price.setCurrency(curr);
			price.setAmount(this.repository.getRecipePricesByIdAndCurrency(entity.getId(), curr));
			prices.add(price);
		}
		
		request.unbind(entity, model, "code", "heading", "description", "info", "preparationNotes", "published");
	

		final List<Money> pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());
		model.setAttribute("price", money);
		model.setAttribute("readOnly", true);
		
		model.setAttribute("ableToPublish", 
			this.repository.getIngredientsFromRecipe(entity.getId()).stream().allMatch(e->e.getWareType().equals(WareType.INGREDIENT) && e.isPublished())
			&& this.repository.getUtensilsFromRecipe(entity.getId()).stream().allMatch(e->e.getWareType().equals(WareType.KITCHEN_UTENSIL) && e.isPublished()));
		
	}

}
