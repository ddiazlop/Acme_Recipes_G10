package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Peep;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import justenoughspam.detector.SpamDetector2;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep>{
	
	@Autowired
	protected AnyPeepRepository repo;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "heading", "writer", "text", "email");
		entity.setMoment(moment);	
		
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "writer", "text", "email");
		
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		Peep peep;
		peep = new Peep();
		return peep;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.systemConfigRepository.findSpamTuple(), this.systemConfigRepository.findSpamThreshold());
		
		if (!errors.hasErrors("heading")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getHeading()), "heading", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("writer")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getWriter()), "writer", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("text")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getText()), "text", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("email")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getEmail()), "email", "spamDetector.spamDetected");
		}
		
		
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		this.repo.save(entity);
		
	}

	

}
