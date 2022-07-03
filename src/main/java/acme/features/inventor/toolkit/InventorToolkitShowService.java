
package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.ItemType;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {

	// Internal State -------------------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	@Autowired
	protected AuthenticatedMoneyExchangePerformService	moneyExchange;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findOneToolkitByIdFromInventor(id, inventorId);
		return (inventorId == toolkit.getInventor().getId());
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		int id;
		Toolkit toolkit;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitByIdFromInventor(id, inventorId);
		return toolkit;
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

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "info", "published");
		
		final List<Money> pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
		model.setAttribute("inventor", entity.getInventor().getIdentity().getFullName());
		
		model.setAttribute("ableToPublish", this.repository.getToolsFromToolkit(entity.getId()).stream().anyMatch(e->e.getItemType().equals(ItemType.TOOL)));
	}

}
