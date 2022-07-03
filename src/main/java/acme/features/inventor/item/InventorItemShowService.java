
package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.features.authenticated.userAccount.AuthenticatedUserAccountRepository;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository						repository;

	@Autowired
	protected AuthenticatedUserAccountRepository			userRepository;

	@Autowired
	protected AuthenticatedSystemConfigurationRepository	authenticatedSystemConfigurationRepository;

	@Autowired
	protected AuthenticatedMoneyExchangePerformService		moneyExchangePerformService;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final int id = request.getModel().getInteger("id");
		final Item item = this.repository.findOneItemById(id);
		return (inventorId == item.getInventor().getId());
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		int id;
		Item result;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final boolean retailPriceIsInSystemCurrency = this.authenticatedSystemConfigurationRepository.findSystemCurrency().equals(entity.getRetailPrice().getCurrency());
		model.setAttribute("retailPriceIsInSystemCurrency", retailPriceIsInSystemCurrency);
		if (!retailPriceIsInSystemCurrency) {

			final Money systemMoney = this.moneyExchangePerformService.computeMoneyExchange(entity.getRetailPrice(), this.authenticatedSystemConfigurationRepository.findSystemConfiguration().getSystemCurrency()).getChange();

			model.setAttribute("systemMoney", systemMoney);

		}

		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "info", "published");

	}

}
