
package acme.features.epicure.fineDish;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
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
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;


@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish> {

	@Autowired
	public EpicureFineDishRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Epicure.class);
	}
	
	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status", "code","request","budget","creationDate",
			"startDate", "endDate","info");
		final String chefUsername = String.valueOf(request.getModel().getAttribute("chefUsername"));
		final Chef chef = this.repository.findOneChefByUsername(chefUsername);
		if(!errors.hasErrors("chef")) {
			errors.state(request, chef!=null, "*", "epicure.fine-dish.form.error.invalidChef");
		}
		entity.setChef(chef);

	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info", "published");
		
		if(entity.getChef() == null) {
			model.setAttribute("chefUsername", "");
		}
		else {
			model.setAttribute("chefUsername", entity.getChef().getUserAccount().getUsername());
		}
		
		final Collection<Chef> chefs = this.repository.findAllChefs();
		model.setAttribute("chefs", chefs);
		model.setAttribute("PROPOSED", DishStatus.PROPOSED);
		model.setAttribute("ACCEPTED", DishStatus.ACCEPTED);
		model.setAttribute("DENIED", DishStatus.DENIED);
		model.setAttribute("readOnly", false);

	}
	
		
	
	@Override
	public FineDish instantiate(final Request<FineDish> request) {

		assert request != null;
		FineDish fineDish;
		Epicure epicure;
		Date creationDate;
		final Calendar calendar;
		Date startDate;
		Date endDate;
	
		
		int epicureId;
		epicureId = request.getPrincipal().getActiveRoleId();
		epicure = this.repository.findOneEpicureById(epicureId);
		calendar = Calendar.getInstance();
		creationDate = calendar.getTime();
		calendar.setTime(creationDate);
		calendar.add(Calendar.SECOND, -1);
		startDate = new Date();
		endDate = new Date();
		
		
		fineDish = new FineDish();
		fineDish.setCode("");
		fineDish.setStatus(DishStatus.PROPOSED);
		fineDish.setInfo("");
		fineDish.setEpicure(epicure);
		fineDish.setCreationDate(creationDate);
		fineDish.setStartDate(startDate);
		fineDish.setEndDate(endDate);
		fineDish.setPublished(false);
				
		return fineDish;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			final FineDish existing = this.repository.findOneFineDishByCode(entity.getCode());
			errors.state(request, existing == null, "code", "epicure.fine-dish.form.error.duplicated-code");
		}
		
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
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
	
	
}
