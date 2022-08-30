package acme.features.any.recipes.kitchenwareRecipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepositorySep;
import acme.features.any.kitchenware.AnyKitchenwareRepository;
import acme.features.any.recipes.AnyRecipeRepository;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyKitchenwareRecipeListService implements AbstractListService<Any, KitchenwareRecipe>{

	@Autowired
	protected AnyRecipeRepository recipeRepository;
	
	@Autowired
	protected AnyKitchenwareRepository kitchenwareRepository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchange;
	
	@Autowired
	protected AdministratorSystemConfigurationRepositorySep sysconfig;

	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		assert request != null;
		final Integer recipeId = request.getModel().getInteger("recipeId");
		return this.recipeRepository.findOneRecipeById(recipeId).isPublished();
	}

	@Override
	public Collection<KitchenwareRecipe> findMany(final Request<KitchenwareRecipe> request) {
		assert request != null;
		Collection<KitchenwareRecipe> res;
		
		final Integer recipeId = request.getModel().getInteger("recipeId");
		if (request.getCommand().equals("list-ingredients")) {
			res = this.recipeRepository.getKitchenwareRecipeOfIngredientsFromRecipe(recipeId);
		}else {
			res = this.recipeRepository.getKitchenwareRecipeOfUtensilsFromRecipe(recipeId);
		}
		return res;
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "kitchenware.code", "kitchenware.name", "unitType");
		this.unbindConvertedMoney(entity.getKitchenware(), model);
	}
	
	private void unbindConvertedMoney(final Kitchenware entity, final Model model) {
		final SystemConfigurationSep sc = this.sysconfig.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getRetailPrice(), sc.getSystemCurrency()).getChange();
		model.setAttribute("price", money);
	}
	

	

}
