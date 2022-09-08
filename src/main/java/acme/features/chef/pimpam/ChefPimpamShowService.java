package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.features.authenticated.moneyExchangeSep.AuthenticatedMoneyExchangeSepPerformService;
import acme.features.authenticated.systemConfigurationSep.AuthenticatedSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamRepository 					repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;
	
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int pimpamId = request.getModel().getInteger("id");
		
		final Pimpam pimpam = this.repository.findOnePIMPAMById(pimpamId);
		
		return pimpam.getChef().getId() == chefId;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		
		assert request != null;
		Pimpam result;
		final int pimpamId = request.getModel().getInteger("id");
		
		result = this.repository.findOnePIMPAMById(pimpamId);
		return result;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert model != null;
		assert entity != null;
		
		request.unbind(entity, model, "code", "title", "description", "instantiationMoment", "startDate", "finishDate", "budget", "info");
		model.setAttribute("kitchenwareCode", entity.getKitchenware().getCode());
		//this.unbindConvertedMoney(entity, model);
	}
	/*
	private void unbindConvertedMoney(final Pimpam entity, final Model model) {
		final SystemConfigurationSep sc = this.config.findSystemConfiguration();
		final Money money = this.moneyExchange.computeMoneyExchange(entity.getBudget(), sc.getSystemCurrency()).getChange();
		model.setAttribute("budgetConverted", money);
	}*/

}
