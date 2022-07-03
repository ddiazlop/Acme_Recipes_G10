
package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.patron.id = :id")
	Collection<Patronage> findManyPatronagesByPatronId(int id);

	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	Collection<PatronageReport> findPatronageReportById(int id);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(int id);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("select i from Inventor i")
	Collection<Inventor> findAllInventors();
	
	@Query("select s.acceptedCurrencies from SystemConfiguration s")
	String findAcceptedCurrencies();
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	
}
