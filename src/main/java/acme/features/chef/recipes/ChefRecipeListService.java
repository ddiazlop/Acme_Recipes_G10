package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
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
		
		final List<Money> prices = new ArrayList<>();
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		
		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
			final Money price = new Money();
			price.setCurrency(curr);
			price.setAmount(this.repository.getRecipePricesByIdAndCurrency(entity.getId(), curr));
			prices.add(price);
		}
		
		request.unbind(entity, model, "code", "heading", "description");
		
		final List<Money> convertedPrices;
		convertedPrices = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());
		
		final Money money = new Money();
		final Double amount = convertedPrices.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
		if (entity.isPublished()) {
			model.setAttribute("state", "PUBLISHED");
		} else {
			model.setAttribute("state", "NOT PUBLISHED");
		}
		
	}

}
