
package acme.features.epicure.fineDish;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.DishStatus;
import acme.entities.fineDish.FineDish;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;

	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;

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
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFineDishById(id);
		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info", "published");
		
		if(entity.getChef() == null) {
			model.setAttribute("chef.username", "");
			model.setAttribute("chef.organisation", "");
			model.setAttribute("chef.assertion", "");
			model.setAttribute("chef.link", "");
		}
		else {
			final Chef chef = entity.getChef();
			model.setAttribute("chef.organisation", chef.getOrganisation());
			model.setAttribute("chef.assertion", chef.getAssertion());
			model.setAttribute("chef.link", chef.getLink());
			model.setAttribute("chef.username", entity.getChef().getUserAccount().getUsername());
		}
		
		
		model.setAttribute("PROPOSED", DishStatus.PROPOSED);
		model.setAttribute("ACCEPTED", DishStatus.ACCEPTED);
		model.setAttribute("DENIED", DishStatus.DENIED);
		model.setAttribute("readOnly", true);

	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","request","budget","startDate", 
				 "endDate","info");
		
		final String chefUsername = String.valueOf(request.getModel().getAttribute("chef.username"));
		final Chef chef = this.repository.findOneChefByUsername(chefUsername);
		if(!errors.hasErrors("chef")) {
			errors.state(request, chef!=null, "*", "epicure.fine-dish.form.error.invalidChef");
		}
		entity.setChef(chef);
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "epicure.fine-dish.form.error.negative");
			
			final String entityCurrency = entity.getBudget().getCurrency();			
			final String[] acceptedCurrencies=this.administratorSystemConfigurationRepository.findAcceptedCurrencies().split(",");
			final List<String> currencies= Arrays.asList(acceptedCurrencies);			
			errors.state(request, currencies.contains(entityCurrency) , "budget", "epicure.fine-dish.form.error.noAcceptedCurrency");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			final Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunDate), "startDate", "epicure.fine-dish.form.error.too-close-start");
		}
		
		if(!errors.hasErrors("endDate")) {
			Calendar calendar;
			Date minimunDate;
			
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getStartDate());
			
			calendar.add(Calendar.MONTH, 1);
			minimunDate = calendar.getTime();
			
			errors.state(request, entity.getEndDate().after(minimunDate), "endDate", "epicure.fine-dish.form.error.too-short-periodOfTime");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
