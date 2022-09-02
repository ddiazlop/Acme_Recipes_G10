package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.fineDish.FineDish;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefFineDishListService implements AbstractListService<Chef, FineDish>{
	
	@Autowired
	protected ChefFineDishRepository						repository;

	@Autowired
	protected AuthenticatedSystemConfigurationRepository	systemConfigurationRepository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService		moneyExchangeService;
	

	@Override
	public boolean authorise(final Request<FineDish> request) {
		
		assert request !=null;
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<FineDish> fineDishes = this.repository.findFineDishesByChefId(chefId);
		
		return fineDishes;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final SystemConfiguration sc = this.systemConfigurationRepository.findSystemConfiguration();
		final Money budget = entity.getBudget();
		final String systemCurrency = sc.getSystemCurrency();
		
		if (!budget.getCurrency().equals(systemCurrency)) {
			final Money systemBudget = new Money();
			systemBudget.setCurrency(systemCurrency);
			
			systemBudget.setAmount(this.moneyExchangeService.computeMoneyExchange(budget, systemCurrency).getRate()*budget.getAmount());
			entity.setBudget(systemBudget);
		}
		
		request.unbind(entity, model, "code", "budget", "startDate", "endDate", "status");
		model.setAttribute("epicureUserName", entity.getEpicure().getUserAccount().getUsername());
	}

}
