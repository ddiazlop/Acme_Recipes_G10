
package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Recipe;
import acme.entities.recipes.WareType;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import justenoughspam.detector.SpamDetector2;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository						repository;
	@Autowired
	protected AdministratorSystemConfigurationSepRepository	systemConfigRepository;

	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService		moneyExchange;

	// AbstractUpdateService<Chef, Recipe> interface -------------------------


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
		model.setAttribute("readOnly", true);
	
		model.setAttribute("ableToPublish", 
			this.repository.getIngredientsFromRecipe(entity.getId()).stream().allMatch(e->e.getWareType().equals(WareType.INGREDIENT) && e.isPublished() )
			&& this.repository.getUtensilsFromRecipe(entity.getId()).stream().allMatch(e->e.getWareType().equals(WareType.KITCHEN_UTENSIL) && e.isPublished()));
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
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.systemConfigRepository.findSpamTuple(), this.systemConfigRepository.findSpamThreshold());
		
		if (!errors.hasErrors("heading")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getHeading()), "heading", "spamDetector.spamDetected");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getDescription()), "description", "spamDetector.spamDetected");
		}
		if (!errors.hasErrors("preparationNotes")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getPreparationNotes()), "preparationNotes", "spamDetector.spamDetected");
		}
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
	
}
