package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.fineDish.FineDish;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish>{

	@Autowired
	protected ChefFineDishRepository						repository;

	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository	systemConfigurationRepository;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService		moneyExchangeService;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		
		assert request != null;
		boolean result;
		
		final int fineDishId  = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findFineDishById(fineDishId);

		final int chefId = request.getPrincipal().getActiveRoleId();
		
		result = fineDish.getChef().getId() == chefId;
		result = result && fineDish.isPublished();
		return result;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		final int fineDishId  = request.getModel().getInteger("id");
		return this.repository.findFineDishById(fineDishId);
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final SystemConfigurationSep sc = this.systemConfigurationRepository.findSystemConfiguration();
		final Money budget = entity.getBudget();
		final String systemCurrency = sc.getSystemCurrency();
		
		if (!budget.getCurrency().equals(systemCurrency)) {
			final Money systemBudget = new Money();
			systemBudget.setCurrency(systemCurrency);
			
			systemBudget.setAmount(this.moneyExchangeService.computeMoneyExchange(budget, systemCurrency).getRate()*budget.getAmount());
			entity.setBudget(systemBudget);
		}
		
		request.unbind(entity, model, "code", "request", "budget", "creationDate", "startDate", "endDate", "info", "status");
		
		model.setAttribute("fineDishId", entity.getId());
		model.setAttribute("epicureName", entity.getEpicure().getUserAccount().getIdentity().getFullName());
		model.setAttribute("epicureUserName", entity.getEpicure().getUserAccount().getUsername());
		model.setAttribute("epicureOrganisation", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureAssertion", entity.getEpicure().getAssertion());
		model.setAttribute("epicureLink", entity.getEpicure().getLink());
		
	}

}
