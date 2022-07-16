
package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.components.configuration.SystemConfiguration;
import acme.entities.Chirp;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request !=null;
		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "author", "body", "email");
		entity.setMoment(moment);		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);

		
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		
		Chirp result;
		result = new Chirp();
		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if (!errors.hasErrors("title")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getTitle()), "title", "spam.detector.error.message");
		}
		
		if(!errors.hasErrors("author")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getAuthor()), "author", "spam.detector.error.message");
		}
		
		if (!errors.hasErrors("body")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getBody()), "body", "spam.detector.error.message");
		}

	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {

		assert request != null;
		assert request !=null;
		
		this.repository.save(entity);
	}

}
