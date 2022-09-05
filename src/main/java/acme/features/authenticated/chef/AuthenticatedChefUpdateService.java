package acme.features.authenticated.chef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import justenoughspam.detector.SpamDetector2;

@Service
public class AuthenticatedChefUpdateService implements AbstractUpdateService<Authenticated, Chef>{
	
	@Autowired
	protected AuthenticatedChefRepository repo;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Chef> request) {
		assert request != null;

		boolean res;

		res = request.getPrincipal().hasRole(Chef.class) && request.getPrincipal().isAuthenticated();

		return res;
	}

	@Override
	public void bind(final Request<Chef> request, final Chef entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "organisation", "assertion", "link");
		
	}

	@Override
	public void unbind(final Request<Chef> request, final Chef entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisation", "assertion", "link");
		
	}

	@Override
	public Chef findOne(final Request<Chef> request) {
		assert request != null;
		Chef chef;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		chef = this.repo.findOneChefByUserAccountId(userAccountId);
		
		return chef;
	}

	@Override
	public void validate(final Request<Chef> request, final Chef entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.systemConfigRepository.findSpamTuple(), this.systemConfigRepository.findSpamThreshold());
		
		if (!errors.hasErrors("organisation")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getOrganisation()), "organisation", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("assertion")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getAssertion()), "assertion", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("link")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getLink()), "link", "spamDetector.spamDetected");
		}
		
	}

	@Override
	public void update(final Request<Chef> request, final Chef entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
		
	}

	@Override
	public void onSuccess(final Request<Chef> request, final Response<Chef> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
