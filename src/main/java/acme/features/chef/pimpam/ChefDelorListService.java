package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefDelorListService implements AbstractListService<Chef, Delor>{

	@Autowired
	protected ChefDelorRepository 	repository;
	
	/*@Autowired
	protected AuthenticatedMoneyExchangeSepPerformService moneyExchange;
	
	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository config;*/
	
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public Collection<Delor> findMany(final Request<Delor> request) {
		assert request != null;
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<Delor> delor;
		delor = this.repository.findDelorByChefId(chefId);
		
		return delor;
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert entity != null;
		assert request != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "budget", "instantiationMoment");
		model.setAttribute("kitchenwareCode", entity.getKitchenware().getCode());
		
	}
	
}
