package acme.features.any.recipes.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.KitchenwareRecipe;
import acme.features.any.recipes.AnyRecipeRepository;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyKitchenwareRecipeShowService implements AbstractShowService<Any, KitchenwareRecipe>{

	// Internal State -------------------------------------------------------------------
	
	@Autowired
	protected AnyRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final KitchenwareRecipe it=this.repository.findKitchenwareRecipeById(id);
		return it.getRecipe().isPublished();

	}

	@Override
	public KitchenwareRecipe findOne(final Request<KitchenwareRecipe> request) {
		assert request != null;
		
		final KitchenwareRecipe it;
		final int id = request.getModel().getInteger("id");
		it=this.repository.findKitchenwareRecipeById(id);
		return it;
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity","kitchenware.code", "kitchenware.name", "kitchenware.description", "kitchenware.info", "kitchenware.published", "kitchenware.retailPrice");
		model.setAttribute("published", entity.getKitchenware().isPublished());
		model.setAttribute("wareType", entity.getKitchenware().getWareType().name());
		
		if (entity.getUnitType() != null) {
			model.setAttribute("unitType", entity.getUnitType().name());
		}
		this.unbindConvertedMoney(entity, model);
	}
	
	private void unbindConvertedMoney(final KitchenwareRecipe entity, final Model model) {
		final SystemConfigurationSep sc = this.repository.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getKitchenware().getRetailPrice(), sc.getSystemCurrency()).getChange();
		final Money moneyPerUnit = new Money();
		moneyPerUnit.setAmount(money.getAmount());
		moneyPerUnit.setCurrency(money.getCurrency());
		model.setAttribute("retailPrice", moneyPerUnit);
		money.setAmount(money.getAmount()*entity.getQuantity());
		model.setAttribute("totalPrice", money);
	}
	

}
