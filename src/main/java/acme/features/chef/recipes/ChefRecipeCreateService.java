
package acme.features.chef.recipes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.Recipe;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import justenoughspam.detector.SpamDetector2;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository						repository;

	@Autowired
	protected AdministratorSystemConfigurationSepRepository	systemConfigRepository;

	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService		moneyExchange;

	// AbstractCreateService<Chef, Recipe> interface --------------------------------


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;

		final Recipe recipe = new Recipe();
		final Chef chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId());

		recipe.setCode("");
		recipe.setHeading("");
		recipe.setDescription("");
		recipe.setPreparationNotes("");

		recipe.setPublished(false);
		recipe.setChef(chef);
		return recipe;
	}
	

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "heading", "preparationNotes", "description", "info");

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

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "info", "published");

		final List<Money> pricesFix = this.moneyExchange.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());
		model.setAttribute("readOnly", false);
		model.setAttribute("price", money);
		model.setAttribute("chef", entity.getChef().getIdentity().getFullName());
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		if (!errors.hasErrors("code")) {
			final Recipe existing = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}

		if (!errors.hasErrors("code")) {
			final Kitchenware existing = this.repository.findOneKitchenwareByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}
		
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
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	

}
