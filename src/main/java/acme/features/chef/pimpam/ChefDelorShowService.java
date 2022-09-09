package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Delor;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefDelorShowService implements AbstractShowService<Chef, Delor>{
	
	@Autowired
	protected ChefDelorRepository 					repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;
	
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int delorId = request.getModel().getInteger("id");
		
		final Delor delor = this.repository.findOneDelorById(delorId);
		
		return delor.getChef().getId() == chefId;
	}

	@Override
	public Delor findOne(final Request<Delor> request) {
		
		assert request != null;
		Delor result;
		final int delorId = request.getModel().getInteger("id");
		
		result = this.repository.findOneDelorById(delorId);
		return result;
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {

		assert request != null;
		assert model != null;
		assert entity != null;
		
		request.unbind(entity, model, "code", "title", "description", "instantiationMoment", "startDate", "finishDate", "budget", "info");
		model.setAttribute("kitchenwareCode", entity.getKitchenware().getCode());
		//this.unbindConvertedMoney(entity, model);
	}
	/*
	private void unbindConvertedMoney(final Delor entity, final Model model) {
		final SystemConfigurationSep sc = this.config.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getBudget(), sc.getSystemCurrency()).getChange();
		model.setAttribute("budgetConverted", money);
	}*/

}
