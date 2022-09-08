package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefPimpamListService implements AbstractListService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository 	repository;
	
	/*@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;*/
	
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public Collection<Pimpam> findMany(final Request<Pimpam> request) {
		assert request != null;
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<Pimpam> pimpam;
		pimpam = this.repository.findPIMPAMByChefId(chefId);
		
		return pimpam;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert entity != null;
		assert request != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "budget", "instantiationMoment");
		model.setAttribute("kitchenwareCode", entity.getKitchenware().getCode());
		
	}
	
}
