package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageChangeStatusService implements AbstractUpdateService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = patronage != null && patronage.getInventor().getId() == request.getPrincipal().getActiveRoleId();
		result = result && patronage.isPublished();
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		request.bind(entity, errors, "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "status");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		request.unbind(entity, model, "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "status");
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		if (entity.getStatus().equals(PatronageStatus.ACCEPTED) || entity.getStatus().equals(PatronageStatus.DENIED)) {
			errors.state(request, entity.getStatus().equals(PatronageStatus.PROPOSED), "status", "inventor.patronage.errors.already-stated");
		}
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		final String operation = request.getModel().getString("operation");
		if (operation.equals("accept")) {
			entity.setStatus(PatronageStatus.ACCEPTED);
		}else if(operation.equals("deny")){
			entity.setStatus(PatronageStatus.DENIED);
		}
		this.repository.save(entity);
	}

}
