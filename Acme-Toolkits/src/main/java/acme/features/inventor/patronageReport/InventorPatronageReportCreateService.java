
package acme.features.inventor.patronageReport;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import notenoughspam.detector.SpamDetector;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	@Autowired
	public InventorPatronageReportRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;


	@Override
	public boolean authorise(final Request<PatronageReport> request) {

		assert request != null;

		boolean result;
		int patronageId;
		Patronage p;

		patronageId = request.getModel().getInteger("patronageId");
		p = this.repository.findOnePatronageById(patronageId);
		result = p != null && request.isPrincipal(p.getInventor());

		return result;

	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		int patronageId;
		Patronage p;
		
		patronageId = request.getModel().getInteger("patronageId");
		p = this.repository.findOnePatronageById(patronageId);
		
		entity.setPatronage(p);
		request.bind(entity, errors, "moment", "memorandum", "info", "sequenceNumber");

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		int patronageId;
		
		patronageId = request.getModel().getInteger("patronageId");
		
		request.unbind(entity, model, "moment", "memorandum", "info", "sequenceNumber");
		
		model.setAttribute("patronageId", patronageId);
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);	
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {

		assert request != null;
		final PatronageReport result;
		Patronage patronage;
		Date moment;
		final Calendar calendar;
		
		int patronageId;
		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findOnePatronageById(patronageId);

		String code;
				
		code = patronage.getCode();
				

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new PatronageReport();
		result.setInfo("");
		result.setMemorandum("");
		
		
		final DecimalFormat decimalFormat = new DecimalFormat("0000");
		final Integer serial = this.repository.countPatronageReport()+1;
		final String serialString = decimalFormat.format(serial);
		
		result.setSequenceNumber(code+":"+serialString);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if(!errors.hasErrors("memorandum")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getMemorandum()), "memorandum", "spam.detector.error.message");
		}
		
		if (!errors.hasErrors("info")) {
			errors.state(request, spamDetector.stringHasNoSpam(entity.getInfo()), "info", "spam.detector.error.message");
		}
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
