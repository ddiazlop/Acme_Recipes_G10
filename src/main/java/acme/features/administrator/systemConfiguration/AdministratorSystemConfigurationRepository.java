
package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc")
	String findAcceptedCurrencies();
	
	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc where sc.acceptedCurrencies=:currency")
	String findAcceptedCurrnciesByName(String currency);
}
