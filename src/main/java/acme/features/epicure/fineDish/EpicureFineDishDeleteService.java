
package acme.features.epicure.fineDish;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.components.configuration.SystemConfiguration;
import acme.entities.fineDish.FineDish;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;
import acme.roles.Epicure;
import acme.roles.Inventor;

@Service
public class EpicureFineDishDeleteService implements AbstractDeleteService<Epicure, FineDish> {

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
	}


	
	@Override
	public void delete(Request<FineDish> request, FineDish entity) {
		assert request != null;
		assert entity != null;
		
		Collection<FineDish> recipeContent;

		this.repository.delete(entity);


	}
	
	
}
