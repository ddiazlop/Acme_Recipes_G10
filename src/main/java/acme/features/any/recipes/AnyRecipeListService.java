
package acme.features.any.recipes;

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
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListService implements AbstractListService<Any, Recipe> {

	// Internal State ------------------------------------------------------------------

	@Autowired
	protected AnyRecipeRepository repository;

	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;

		final Collection<Recipe> result;

		result = this.repository.findAllPublishedRecipes();

		return result;
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

		final List<Money> pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
	}

}
