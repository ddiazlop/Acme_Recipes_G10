package acme.features.chef.kitchenware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.WareType;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareShowService implements AbstractShowService<Chef, Kitchenware>{

	@Autowired
	protected ChefKitchenwareRepository repo;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int chefId;
		int kitchenwareId;
		Kitchenware kitchenware;
		
		chefId = request.getPrincipal().getActiveRoleId();
		kitchenwareId = request.getModel().getInteger("id");
		kitchenware = this.repo.findOneKitchenwareById(kitchenwareId);
		
		return kitchenware.getChef().getId() == chefId;
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int id;
		Kitchenware kitchenware;
		id = request.getModel().getInteger("id");
		kitchenware = this.repo.findOneKitchenwareById(id);
		
		return kitchenware;
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info", "published");
		model.setAttribute("IngredientType", WareType.INGREDIENT);
		model.setAttribute("KitchenUtensilType", WareType.KITCHEN_UTENSIL);
		model.setAttribute("readOnly", true);
		this.unbindConvertedMoney(entity, model);
	}

	private void unbindConvertedMoney(final Kitchenware entity, final Model model) {
		final SystemConfigurationSep sc = this.config.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getRetailPrice(), sc.getSystemCurrency()).getChange();
		model.setAttribute("retailPriceConverted", money);
	}
}
