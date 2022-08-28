package acme.features.any.recipes.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.features.any.recipes.AnyRecipeRepository;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
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
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final KitchenwareRecipe it=this.repository.findKitchenwareRecipeById(id);
		return it.getRecipe().getChef().getId()==request.getPrincipal().getActiveRoleId();

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
		
		request.unbind(entity, model, "quantity", "unitType","kitchenware.code", "kitchenware.name", "kitchenware.wareType", "kitchenware.description", "kitchenware.retailPrice", "kitchenware.info", "kitchenware.published" );
		model.setAttribute("published", entity.getKitchenware().isPublished());
		this.unbindConvertedMoney(entity.getKitchenware(), model);
	}
	
	private void unbindConvertedMoney(final Kitchenware entity, final Model model) {
		final SystemConfigurationSep sc = this.repository.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getRetailPrice(), sc.getSystemCurrency()).getChange();
		model.setAttribute("price", money);
	}
	

}
