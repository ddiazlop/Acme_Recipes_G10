package acme.features.authenticated.moneyExchangeSep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.MoneyExchangeSep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoneyExchangeSepRepository extends AbstractRepository{
		
	@Query("select sc.acceptedCurrencies from SystemConfigurationSep sc")
	String findAcceptedCurrencies();
	
	@Query("select sc.systemCurrency from SystemConfigurationSep sc")
	String findSystemCurrency();
	
	@Query("select me from MoneyExchangeSep me where me.source.currency = :source and me.targetCurrency = :target")
	MoneyExchangeSep findMoneyExchangeFromCurrencies(String source, String target);
	
}
