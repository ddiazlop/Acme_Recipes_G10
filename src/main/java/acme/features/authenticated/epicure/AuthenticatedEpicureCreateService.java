package acme.features.authenticated.epicure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class AuthenticatedEpicureCreateService implements AbstractCreateService<Authenticated, Epicure>{
	
	@Autowired
	protected AuthenticatedEpicureRepository repo;

	@Override
	public boolean authorise(final Request<Epicure> request) {
		assert request != null;

		boolean res;

		res = !request.getPrincipal().hasRole(Epicure.class) && request.getPrincipal().isAuthenticated();

		return res;
	}

	@Override
	public void bind(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "organisation", "assertion", "link");
		
	}

	@Override
	public void unbind(final Request<Epicure> request, final Epicure entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisation", "assertion", "link");
		
	}

	@Override
	public Epicure instantiate(final Request<Epicure> request) {
		assert request != null;

		Epicure epicure;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repo.findOneUserAccountById(userAccountId);

		epicure = new Epicure();
		epicure.setUserAccount(userAccount);

		return epicure;
	}

	@Override
	public void validate(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Epicure> request, final Epicure entity) {
		assert request != null;
		assert entity != null;
		this.repo.save(entity);
		
	}
	
	@Override
	public void onSuccess(final Request<Epicure> request, final Response<Epicure> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

	

}
