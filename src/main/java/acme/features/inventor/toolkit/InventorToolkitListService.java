
package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor, Toolkit> {

	// Internal State ------------------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository					repository;

	@Autowired
	protected AuthenticatedMoneyExchangePerformService	moneyExchange;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Collection<Toolkit> toolkits = this.repository.findAllToolkitsByInventor(inventorId);
		return toolkits.stream().allMatch(e -> e.getInventor().getId() == inventorId);
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		final Collection<Toolkit> result;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findAllToolkitsByInventor(inventorId);

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final List<Money> prices = new ArrayList<>();
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
			final Money price = new Money();
			price.setCurrency(curr);
			price.setAmount(this.repository.getToolkitPricesByIdAndCurrency(entity.getId(), curr));
			prices.add(price);
		}

		request.unbind(entity, model, "code", "title", "description");

		List<Money> pricesFix;
		pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
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
