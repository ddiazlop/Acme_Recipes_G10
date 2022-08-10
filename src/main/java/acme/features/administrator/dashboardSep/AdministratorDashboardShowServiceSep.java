
package acme.features.administrator.dashboardSep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.datatypes.StatData;
import acme.entities.fineDish.DishStatus;
import acme.entities.recipes.WareType;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowServiceSep implements AbstractShowService<Administrator, AdministratorDashboard> {

	@Autowired
	protected AdministratorDashboardRepositorySep repository;


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		final AdministratorDashboard result;

		Integer numIngredients;
		Integer numKitchenUtensils;
		Integer numFDishRequested;
		Integer numFDishAccepted;
		Integer numFDishDenied;

		numIngredients = this.repository.getNumIngredients();
		numKitchenUtensils = this.repository.getNumKitchenUtensils();
		numFDishRequested = this.repository.getNumDishes(DishStatus.PROPOSED);
		numFDishAccepted = this.repository.getNumDishes(DishStatus.ACCEPTED);
		numFDishDenied = this.repository.getNumDishes(DishStatus.DENIED);

		final Map<String, StatData> ingredientsDataByCurrency = new HashMap<>();
		final Map<String, StatData> kitchenUtensilsDataByCurrency = new HashMap<>();
		final Map<Pair<DishStatus, String>, StatData> fDishesBudgetData = new HashMap<>();

		final SystemConfiguration sc = this.repository.findSystemConfigurationSep();
		final List<String> acceptedCurrencies = Arrays.asList(sc.getAcceptedCurrencies().trim().split(","));

		//---------INGREDIENT and KITCHEN_UTENSIL Data By Currency--------------

		acceptedCurrencies.forEach(x -> ingredientsDataByCurrency.put(x, StatData.of(this.repository.getKitchenwareDataByCurrency(WareType.INGREDIENT, x), x)));
		acceptedCurrencies.forEach(x -> kitchenUtensilsDataByCurrency.put(x, StatData.of(this.repository.getKitchenwareDataByCurrency(WareType.KITCHEN_UTENSIL, x), x)));


		//---------FineDish Data By Status and Currency-----------------------

		for (final DishStatus status : DishStatus.values()) {
			acceptedCurrencies.forEach(x -> fDishesBudgetData.put(Pair.of(status, x), StatData.of(this.repository.getDishesBudgetDataByStatusAndCurrency(status, x), x)));
		}

		result = new AdministratorDashboard();

		result.setNumOfIngredients(numIngredients);
		result.setNumOfKitchenUtensils(numKitchenUtensils);
		result.setNumDishesRequested(numFDishRequested);
		result.setNumDishesAccepted(numFDishAccepted);
		result.setNumDishesDenied(numFDishDenied);
		result.setIngredientsDataByCurrency(ingredientsDataByCurrency);
		result.setKitchenUtensilsDataByCurrency(kitchenUtensilsDataByCurrency);
		result.setDishesBudgetData(fDishesBudgetData);

		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numOfIngredients", "numOfKitchenUtensils", "numDishesRequested", "numDishesAccepted", "numDishesDenied");
		final Map<String, StatData> accepteds=new HashMap<String, StatData>();
		final Map<String, StatData> denieds=new HashMap<String, StatData>();
		final Map<String, StatData> pendings=new HashMap<String, StatData>();
		final Map<String, StatData> ingredients=new HashMap<String, StatData>();
		final Map<String, StatData> kitchenUtensils=new HashMap<String, StatData>();
		
		for(final String curr:this.repository.findSystemConfigurationSep().getAcceptedCurrencies().split(",")) {
			model.setAttribute("dataIngredient"+curr,  entity.getIngredientsDataByCurrency().get(curr));
			model.setAttribute("dataKitchenUtensil"+curr, entity.getKitchenUtensilsDataByCurrency().get(curr));

			accepteds.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.ACCEPTED, curr)));
			model.setAttribute("accepteds", accepteds);
			denieds.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.DENIED, curr)));
			model.setAttribute("denieds", denieds);
			pendings.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.PROPOSED, curr)));
			model.setAttribute("pendings", pendings);
			
			kitchenUtensils.put(curr, entity.getKitchenUtensilsDataByCurrency().get(curr));
			model.setAttribute("kitchenUtensils", kitchenUtensils);
			ingredients.put(curr, entity.getIngredientsDataByCurrency().get(curr));
			model.setAttribute("ingredients",ingredients);
		};
}

}
