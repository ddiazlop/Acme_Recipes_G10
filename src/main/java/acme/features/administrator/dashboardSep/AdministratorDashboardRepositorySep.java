
package acme.features.administrator.dashboardSep;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.fineDish.DishStatus;
import acme.entities.recipes.WareType;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepositorySep extends AbstractRepository {

	@Query("select count(k) from Kitchenware k where k.wareType='INGREDIENT'")
	Integer getNumIngredients();

	@Query("select count(k) from Kitchenware k where k.wareType='KITCHEN_UTENSIL'")
	Integer getNumKitchenUtensils();

	@Query("select count(fd) from FineDish fd where fd.status =:status")
	Integer getNumDishes(DishStatus status);

	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfigurationSep();

	//Para ingredientes como para Utensilios de cocina
	@Query("select k.retailPrice from Kitchenware k where k.wareType=:type")
	List<Money> getKitchenwarePrices(WareType type);

	@Query("select sum(k.retailPrice.amount), avg(k.retailPrice.amount), stddev(k.retailPrice.amount),min(k.retailPrice.amount)," + " max(k.retailPrice.amount) from Kitchenware k where k.wareType=:type "
		+ "and k.retailPrice.currency=:currency")
	Tuple getKitchenwareDataByCurrency(WareType type, String currency);
	//--------------------------------------------------------------------
	
	@Query("select sum(fd.budget.amount), avg(fd.budget.amount), stddev(fd.budget.amount), min(fd.budget.amount), max(fd.budget.amount) from FineDish fd where fd.status=:status and fd.budget.currency=:currency")
	Tuple getDishesBudgetDataByStatusAndCurrency(DishStatus status, String currency);


}
