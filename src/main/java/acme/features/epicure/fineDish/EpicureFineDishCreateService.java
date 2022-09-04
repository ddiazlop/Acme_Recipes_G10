
package acme.features.epicure.fineDish;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.components.configuration.SystemConfiguration;
import acme.entities.fineDish.FineDish;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.entities.recipes.Kitchenware;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;
import acme.roles.Inventor;


@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish> {

	@Autowired
	public EpicureFineDishRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;

	@Autowired
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

		return fineDish.getEpicure().equals(epicure);
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
		
		final Chef chef = entity.getChef();
		model.setAttribute("chef.username", chef.getUserAccount().getUsername());
		model.setAttribute("chef.organisation", chef.getOrganisation());
		model.setAttribute("chef.assertion", chef.getAssertion());
		model.setAttribute("chef.link", chef.getLink());

	}
	
		
	
	@Override
	public FineDish instantiate(final Request<FineDish> request) {

		assert request != null;
				FineDish fineDish;
		Epicure epicure;
		Money budget;
		Date creationDate;
		Date endDate;
	
		
		int epicureId;
		epicureId = request.getPrincipal().getActiveRoleId();
		epicure = this.repository.findOneEpicureById(epicureId);
		budget = new Money();
		creationDate = new Date();
		endDate = new Date();
		
		
		fineDish = new FineDish();
		fineDish.setCode("");
		fineDish.setBudget(budget);
		fineDish.setInfo("");
		fineDish.setEpicure(epicure);
		fineDish.setCreationDate(creationDate);
		fineDish.setEndDate(endDate);
				
		return fineDish;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		if (!errors.hasErrors("code")) {
			final FineDish existing = this.repository.findOneFineDishByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}

		if (!errors.hasErrors("code")) {
			final Kitchenware existing = this.repository.findOneKitchenwareByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}
	}


	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
	
	
}
