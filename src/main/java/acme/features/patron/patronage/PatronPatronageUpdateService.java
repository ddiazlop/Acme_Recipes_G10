
package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.Patronage;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;
import notenoughspam.detector.SpamDetector;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;



	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int masterId;
		Patronage patronage;
		Patron patron;

		masterId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(masterId);
		patron = patronage.getPatron();
		result = !patronage.isPublished() && request.isPrincipal(patron);

		return result;

	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");


	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "published");
		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("inventorId", entity.getInventor().getId());
		
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorInfo", entity.getInventor().getInfo());
		model.setAttribute("patron", this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId()));

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
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		if (!errors.hasErrors("startDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunStartDate;
			calendar.setTime(entity.getCreationDate());
			calendar.add(Calendar.MONTH, 1);

			minimunStartDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunStartDate), "startDate", "patron.patronage.form.error.start-date");

		}

		if (!errors.hasErrors("endDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunEndDate;
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, 1);

			minimunEndDate = calendar.getTime();
			errors.state(request, entity.getEndDate().after(minimunEndDate), "endDate", "patron.patronage.form.error.end-date");
		}
		
		if (!errors.hasErrors("budget")) {
			final Money b = entity.getBudget();
			
			errors.state(request,b.getAmount()>0.0, "budget", "patron.patronage.form.error.budget-negative");
			
			final String c = b.getCurrency();
			errors.state(request, this.repository.findAcceptedCurrencies().contains(c), "budget", "patron.patronage.form.error.budget-acceptedcurrencies");
		}
		
		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if (!errors.hasErrors("title")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getLegalStuff()), "legalStuff", "spam.detector.error.message");
		}

	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
