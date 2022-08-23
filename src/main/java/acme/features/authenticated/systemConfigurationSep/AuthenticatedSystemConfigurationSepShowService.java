
package acme.features.authenticated.systemConfigurationSep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.components.configuration.SystemConfigurationSep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationSepShowService implements AbstractShowService<Authenticated, SystemConfigurationSep> {

	@Autowired
	protected AuthenticatedSystemConfigurationSepRepository repository;


	@Override
	public boolean authorise(final Request<SystemConfigurationSep> request) {
		assert request != null;
		return true;
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

		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies");
	}

}
