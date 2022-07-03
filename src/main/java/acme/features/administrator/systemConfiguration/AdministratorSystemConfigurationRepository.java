
package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrncies();
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc where sc.acceptedCurrencies=:currency")
	String findAcceptedCurrnciesByName(String currency);
}
