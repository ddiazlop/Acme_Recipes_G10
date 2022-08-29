
package acme.features.administrator.systemConfigurationSep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationSepRepository extends AbstractRepository {

	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfiguration();

	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc")
	String findAcceptedCurrncies();
	
	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc where sc.acceptedCurrencies=:currency")
	String findAcceptedCurrnciesByName(String currency);
}
