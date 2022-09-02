package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.datatypes.StatData;
import acme.entities.fineDish.DishStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	
	private static final long						serialVersionUID	= 1L;

	Integer											numOfIngredients;
	Integer											numOfKitchenUtensils;
	Integer											numDishesRequested;
	Integer											numDishesAccepted;
	Integer											numDishesDenied;

	//StatData=[Sum, Average, Desviation, Minimum, Maximum]

	Map<String, StatData>							ingredientsDataByCurrency;

	Map<String, StatData>							kitchenUtensilsDataByCurrency;

	Map<Pair<DishStatus, String>, StatData>	dishesBudgetData;
}
