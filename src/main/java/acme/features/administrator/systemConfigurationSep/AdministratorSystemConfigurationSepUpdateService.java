package acme.features.administrator.systemConfigurationSep;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationSepUpdateService implements AbstractUpdateService<Administrator, SystemConfigurationSep>{

	@Autowired
	protected AdministratorSystemConfigurationSepRepository repository;
	
	@Override
	public boolean authorise(final Request<SystemConfigurationSep> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SystemConfigurationSep> request, final SystemConfigurationSep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "systemCurrency", "acceptedCurrencies","spamTuple","spamThreshold");
	}
	

	@Override
	public void unbind(final Request<SystemConfigurationSep> request, final SystemConfigurationSep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String[] acepCurrencies = this.repository.findAcceptedCurrncies().split(",");
		final List<String> accepted = new ArrayList<String>();
		
		for(int i = 0; i<acepCurrencies.length; i++) {
			final String money = acepCurrencies[i];
			accepted.add(money);
		}
		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "spamTuple", "spamThreshold");
		model.setAttribute("accepted", accepted);
	}

	@Override
	public SystemConfigurationSep findOne(final Request<SystemConfigurationSep> request) {
		assert request != null;
		SystemConfigurationSep result;

		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void validate(final Request<SystemConfigurationSep> request, final SystemConfigurationSep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<SystemConfigurationSep> request, final SystemConfigurationSep entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
