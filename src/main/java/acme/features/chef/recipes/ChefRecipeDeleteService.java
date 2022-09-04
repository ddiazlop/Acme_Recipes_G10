
package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.entities.recipes.WareType;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefRecipeDeleteService implements AbstractDeleteService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository					repository;

	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService	moneyExchange;

	// AbstractDeleteService<Chef, Recipe> interface -------------------------


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Recipe recipe = this.repository.findOneRecipeById(id);

		return (recipe.getChef().getId() == chefId && !recipe.isPublished());
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading", "description", "preparationNotes", "info");

	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<Money> prices = new ArrayList<>();
		final SystemConfigurationSep sc = this.repository.findSystemConfigurationSep();
		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
			final Money price = new Money();
			price.setCurrency(curr);
			price.setAmount(this.repository.getRecipePricesByIdAndCurrency(entity.getId(), curr));
			prices.add(price);
		}

		request.unbind(entity, model, "heading", "description", "preparationNotes", "info", "published");

		final List<Money> pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
		model.setAttribute("chef", entity.getChef().getIdentity().getFullName());

		model.setAttribute("ableToPublish", 
			this.repository.getIngredientsFromRecipe(entity.getId()).stream().anyMatch(e->e.getWareType().equals(WareType.INGREDIENT))
			&& this.repository.getUtensilsFromRecipe(entity.getId()).stream().anyMatch(e->e.getWareType().equals(WareType.KITCHEN_UTENSIL)));
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		int id;
		Recipe recipe;
		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		return recipe;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		Collection<KitchenwareRecipe> recipeContent;

		recipeContent = this.repository.getRecipeContentByRecipeId(entity.getId());
		for (final KitchenwareRecipe kw : recipeContent) {
			this.repository.delete(kw);
		}

		this.repository.delete(entity);

	}

}
