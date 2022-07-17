package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.components.configuration.SystemConfiguration;
import acme.entities.Announcement;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement>{

	@Autowired
	protected AdministratorAnnouncementRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "body", "critical", "link");
		entity.setMoment(moment);
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		boolean isAdmin;
		isAdmin = request.getPrincipal().hasRole(Administrator.class);

		request.unbind(entity, model,  "title", "body", "critical", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		model.setAttribute("isAdmin", isAdmin);
		
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;		
			
		result = new Announcement();
		result.setTitle("");
		result.setBody("");
		result.setCritical(false);
		result.setLink("");

		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "c"
			+ "onfirmation", "javax.validation.constraints.AssertTrue.message");
		
		
		
		
		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if (!errors.hasErrors("title")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getTitle()), "title", "spam.detector.error.message");
		}
		
		if(!errors.hasErrors("body")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getBody()), "body", "spam.detector.error.message");
		}
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
