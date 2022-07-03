package acme.features.patron.dashboard;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select count(p) from Patronage p where p.status =:status and p.patron.id =:id")
	Integer numPatronagesByStatus(PatronageStatus status, int id);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select sum(p.budget.amount), avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.status=:status and p.budget.currency=:currency and p.patron.id=:id")
	Tuple getPatronageBudgetDataByIdAndStatusAndCurrency(PatronageStatus status, String currency, int id);
}
