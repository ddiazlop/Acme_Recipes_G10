package acme.features.authenticated.systemConfigurationSep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.components.configuration.SystemConfigurationSep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSystemConfigurationSepRepository extends AbstractRepository{

	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfiguration();
	
	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc")
	String findAcceptedCurrencies();
	
	@Query("select sc.systemCurrency from SystemConfigurationSep sc")
	String findSystemCurrency();
}