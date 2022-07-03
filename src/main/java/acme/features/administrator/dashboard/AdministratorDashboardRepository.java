
package acme.features.administrator.dashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.PatronageStatus;
import acme.entities.toolkits.ItemType;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Item i where i.itemType='COMPONENT'")
	Integer getNumComps();

	@Query("select count(i) from Item i where i.itemType='TOOL'")
	Integer getNumTools();

	@Query("select count(p) from Patronage p where p.status=:status")
	Integer getNumPatronage(PatronageStatus status);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	@Query("select i.retailPrice from Item i where i.itemType=:type")
	List<Money> getItemPrices(ItemType type);

	@Query("select distinct i.technology from Item i")
	List<String> getTechnologies();

	@Query("select sum(i.retailPrice.amount), avg(i.retailPrice.amount), stddev(i.retailPrice.amount), " + "min(i.retailPrice.amount), max(i.retailPrice.amount) from Item i " + "where i.itemType=:type and i.retailPrice.currency=:currency")
	Tuple getItemDataByCurr(ItemType type, String currency);

	@Query("select sum(p.budget.amount), avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.status=:status and p.budget.currency=:currency")
	Tuple getPatronageBudgetDataByStatusAndCurrency(PatronageStatus status, String currency);

	@Query("select sum(i.retailPrice.amount), avg(i.retailPrice.amount), stddev(i.retailPrice.amount),min(i.retailPrice.amount)," + " max(i.retailPrice.amount) from Item i where i.itemType=:type "
		+ "and i.retailPrice.currency=:currency and i.technology=:technology")
	Tuple getItemDataByTechnologyAndCurrency(ItemType type, String technology, String currency);

}
