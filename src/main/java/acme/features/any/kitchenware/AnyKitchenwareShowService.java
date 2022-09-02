package acme.features.any.kitchenware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyKitchenwareShowService implements AbstractShowService<Any, Kitchenware>{

	@Autowired
	protected AnyKitchenwareRepository repo;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;
	

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
		
		request.unbind(entity, model, "code", "name", "description", "retailPrice", "info", "published", "wareType");
		this.unbindConvertedMoney(entity, model);
	}
	
	private void unbindConvertedMoney(final Kitchenware entity, final Model model) {
		final SystemConfigurationSep sc = this.config.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getRetailPrice(), sc.getSystemCurrency()).getChange();
		model.setAttribute("retailPriceConverted", money);
	}
	



}