package acme.features.chef.memoranda;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefMemorandaCreateService implements AbstractCreateService<Chef, Memoranda>{

	@Autowired
	protected ChefMemorandaRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memoranda> request) {
		
		assert request != null;
		boolean result;
		
		final int fineDishId = request.getModel().getInteger("fineDishId");
		final FineDish fineDish = this.repository.findFineDishById(fineDishId);
		
		result = fineDish != null && request.isPrincipal(fineDish.getChef());
		return result;
	}

	@Override
	public void bind(final Request<Memoranda> request, final Memoranda entity, final Errors errors) {
		
		assert request != null;
		assert errors != null;
		assert entity != null;
		
		request.bind(entity, errors, "moment", "sequenceNumber", "report", "info");	
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {
		assert request != null;
		assert model != null;
		assert entity != null;
		
		request.unbind(entity, model, "moment", "sequenceNumber", "report", "info");
		model.setAttribute("fineDishId", entity.getFineDish().getId());
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		model.setAttribute("confirmation", false);
		
	}

	@Override
	public Memoranda instantiate(final Request<Memoranda> request) {
		
		assert request != null;
		final Memoranda result;
		FineDish fineDish;
		Date moment;
		final Calendar calendar;
		int fineDishId;
		
		fineDishId = request.getModel().getInteger("fineDishId");
		fineDish = this.repository.findFineDishById(fineDishId);

		String code;
				
		code = fineDish.getCode();
				

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new Memoranda();
		result.setInfo("");
		result.setReport("");
		result.setFineDish(fineDish);
		result.setEpicure(fineDish.getEpicure());
		
		
		
		final DecimalFormat decimalFormat = new DecimalFormat("0000");
		final Integer serial = this.repository.countMemoranda()+1;
		final String serialString = decimalFormat.format(serial);
		
		result.setSequenceNumber(code+":"+serialString);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Memoranda> request, final Memoranda entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		/*
		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if(!errors.hasErrors("memorandum")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getMemorandum()), "memorandum", "spam.detector.error.message");
		}
		
		if (!errors.hasErrors("info")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getInfo()), "info", "spam.detector.error.message");
		}*/
		
	}

	@Override
	public void create(final Request<Memoranda> request, final Memoranda entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
