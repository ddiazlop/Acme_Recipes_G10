package acme.features.administrator.systemConfigurationSep;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSystemConfigurationSepSystemCurrencyShowService implements AbstractShowService<Administrator, SystemConfigurationSep>{
	@Autowired
	protected AdministratorSystemConfigurationSepRepository repository;


	@Override
	public boolean authorise(final Request<SystemConfigurationSep> request) {
		assert request != null;
		return request.getPrincipal().hasRole(Administrator.class);
	}

	@Override
	public SystemConfigurationSep findOne(final Request<SystemConfigurationSep> request) {
		assert request != null;
		SystemConfigurationSep result;

		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void unbind(final Request<SystemConfigurationSep> request, final SystemConfigurationSep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String[] acepCurrencies = this.repository.findAcceptedCurrencies().split(",");
		final List<String> accepted = new ArrayList<String>();
		
		for(int i = 0; i<acepCurrencies.length; i++) {
			final String money = acepCurrencies[i];
			accepted.add(money);
		}
		request.unbind(entity, model, "systemCurrency");
		model.setAttribute("accepted", accepted);
	}
}
