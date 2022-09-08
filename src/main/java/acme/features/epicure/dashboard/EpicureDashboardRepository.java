package acme.features.epicure.dashboard;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.fineDish.DishStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository {

	@Query("select count(fd) from FineDish fd where fd.status =:status and fd.epicure.id =:id")
	Integer numDishesByStatus(DishStatus status, int id);
	
	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfiguration();
	
	@Query("select sum(fd.budget.amount), avg(fd.budget.amount), stddev(fd.budget.amount), min(fd.budget.amount), max(fd.budget.amount) from FineDish fd where fd.status=:status and fd.budget.currency=:currency and fd.epicure.id=:id")
	Tuple getDishesBudgetDataByIdAndStatusAndCurrency(DishStatus status, String currency, int id);
}
