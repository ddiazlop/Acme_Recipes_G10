package acme.features.chef.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.UnitType;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeShowService implements AbstractShowService<Chef, KitchenwareRecipe>{

	@Autowired
	protected ChefKitchenwareRecipeRepository  repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;
	
	
	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		
		assert request != null;
		final int kitchenwareRecipeId = request.getModel().getInteger("id");
		
		final KitchenwareRecipe kitchenware = this.repository.findKitchenwareRecipeById(kitchenwareRecipeId);
		
		return kitchenware.getRecipe().getChef().getId() == request.getPrincipal().getActiveRoleId();
		
	}

	@Override
	public KitchenwareRecipe findOne(final Request<KitchenwareRecipe> request) {

		assert request != null;
		KitchenwareRecipe result;
		
		final int kitchenwareRecipeId = request.getModel().getInteger("id");
		result = this.repository.findKitchenwareRecipeById(kitchenwareRecipeId);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "kitchenware.name", "kitchenware.code", "kitchenware.description", 
			                          "kitchenware.retailPrice", "kitchenware.info");
		model.setAttribute("wareType", entity.getKitchenware().getWareType().name());
		model.setAttribute("readOnly", true);
		if (entity.getUnitType() != null) {
			model.setAttribute("unitType", entity.getUnitType());
		}
		this.unbindConvertedMoney(entity, model);
		
		for (int i = 0; i < UnitType.values().length; i++) {
			final UnitType unitType = UnitType.values()[i];
			model.setAttribute("enum" + unitType, unitType);
		}
	}
	
	private void unbindConvertedMoney(final KitchenwareRecipe entity, final Model model) {
		final SystemConfigurationSep sc = this.config.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getKitchenware().getRetailPrice(), sc.getSystemCurrency()).getChange();
		final Money moneyPerUnit = new Money();
		moneyPerUnit.setAmount(money.getAmount());
		moneyPerUnit.setCurrency(money.getCurrency());
		model.setAttribute("retailPrice", moneyPerUnit);
		money.setAmount(money.getAmount()*entity.getQuantity());
		model.setAttribute("totalPrice", money);
		
	}

}
