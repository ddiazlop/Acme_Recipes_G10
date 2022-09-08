package acme.features.epicure.memoranda;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.Memoranda;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;
import justenoughspam.detector.SpamDetector2;


@Service
public class EpicureMemorandaCreateService implements AbstractCreateService<Epicure, Memoranda> {

	@Autowired
	protected EpicureMemorandaRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository sysconfig;


	@Override
	public boolean authorise(final Request<Memoranda> request) {
		assert request != null;
		return request.getPrincipal().hasRole(Epicure.class);
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"moment","sequenceNumber","report","info");
		
		if (entity.getChef() == null) {
			model.setAttribute("chef.userAccount.username", "");
		}else {
			model.setAttribute("chef.userAccount.username", entity.getChef().getUserAccount().getUsername());
		}
		
		if (entity.getFineDish() == null) {
			model.setAttribute("fineDish.code", "");
		}else {
			model.setAttribute("fineDish.code", entity.getFineDish().getCode());
			
		}
		model.setAttribute("readOnly", false);
		model.setAttribute("confirmed", false);
	}

	@Override
	public void bind(final Request<Memoranda> request, final Memoranda entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"moment","report","info");
		
		final String fineDishCode = request.getModel().getString("fineDish.code");
		final FineDish finedish = this.repository.findFineDishByCode(fineDishCode);
		Integer sequencePosition = this.repository.countMemoranda(finedish.getId());
		
		final StringBuilder secuenceNumberBuilder = new StringBuilder();
		sequencePosition = sequencePosition +1;
		
		secuenceNumberBuilder.append(fineDishCode + ":");
		for (int i = 0; sequencePosition.toString().length() + i < 4; i++) {
			secuenceNumberBuilder.append(0);
		}
		secuenceNumberBuilder.append(sequencePosition);
		
		entity.setSequenceNumber(secuenceNumberBuilder.toString());
		entity.setChef(finedish.getChef());
		entity.setFineDish(finedish);
		
		
	}

	@Override
	public Memoranda instantiate(final Request<Memoranda> request) {
		Memoranda memoranda;
		memoranda = new Memoranda();
		final Calendar calendar;
		
		calendar = Calendar.getInstance();
		final Date creationDate = calendar.getTime();
		
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final Epicure epicure = this.repository.findEpicureById(epicureId);
		
		memoranda.setMoment(creationDate);
		memoranda.setSequenceNumber("");
		memoranda.setReport("");
		memoranda.setInfo("");
		memoranda.setEpicure(epicure);
		
		
		
		return memoranda;
	}

	@Override
	public void validate(final Request<Memoranda> request, final Memoranda entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.sysconfig.findSpamTuple(), this.sysconfig.findSpamThreshold());
		
		if (!errors.hasErrors("report")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getReport()), "report", "spamDetector.spamDetected");
		}
		
		
		if (!errors.hasErrors()) {
			errors.state(request, request.getModel().getBoolean("confirmed"), "confirmed", "epicure.memoranda.form.create.confirmed-warning");
		}
		
	}
		


	@Override
	public void create(final Request<Memoranda> request, final Memoranda entity) {
			this.repository.save(entity);
	}

}