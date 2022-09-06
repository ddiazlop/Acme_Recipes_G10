package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.DishStatus;
import acme.entities.fineDish.FineDish;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;
import justenoughspam.detector.SpamDetector2;

@Service
public class EpicureFineDishPublishService implements AbstractUpdateService<Epicure, FineDish>{

	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository administratorSystemConfigurationRepository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		int epicureId;
		int fineDishId;
		Epicure epicure;
		FineDish fineDish;
		
		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishById(fineDishId);
		epicureId = request.getPrincipal().getActiveRoleId();
		epicure = this.repository.findOneEpicureById(epicureId);

		return fineDish.getEpicure().equals(epicure) && !fineDish.isPublished();
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"status", "code","request","budget","creationDate","startDate", 
			 "endDate","info");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"status", "code","request","budget","creationDate","startDate", 
			 "endDate","info");
		model.setAttribute("PROPOSED", DishStatus.PROPOSED);
		model.setAttribute("ACCEPTED", DishStatus.ACCEPTED);
		model.setAttribute("DENIED", DishStatus.DENIED);
		model.setAttribute("readOnly", true);
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFineDishById(id);
		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.administratorSystemConfigurationRepository.findSpamTuple(), 
			this.administratorSystemConfigurationRepository.findSpamThreshold());
	
		
		if (!errors.hasErrors("request")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getRequest()), "request", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("info")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getInfo()), "info", "spamDetector.spamDetected");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

	

}
