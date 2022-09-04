package acme.features.chef.kitchenware;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.WareType;
import acme.features.administrator.systemConfigurationSep.AdministratorSystemConfigurationSepRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import justenoughspam.detector.SpamDetector2;

@Service
public class ChefKitchenwareCreateService implements AbstractCreateService<Chef, Kitchenware>{
	
	@Autowired
	protected ChefKitchenwareRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationSepRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public void bind(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "wareType", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info");
		model.setAttribute("IngredientType", WareType.INGREDIENT);
		model.setAttribute("KitchenUtensilType", WareType.KITCHEN_UTENSIL);
		model.setAttribute("readOnly", false);
		
	}

	@Override
	public Kitchenware instantiate(final Request<Kitchenware> request) {
		assert request != null;
		Kitchenware kitchenware;
		Chef chef;
		int chefId;
		chefId = request.getPrincipal().getActiveRoleId();
		chef = this.repository.findOneChefById(chefId);
		
		kitchenware = new Kitchenware();
		kitchenware.setCode("");
		kitchenware.setDescription("");
		kitchenware.setName("");
		kitchenware.setInfo("");
		kitchenware.setWareType(WareType.INGREDIENT);
		kitchenware.setPublished(false);
		kitchenware.setChef(chef);
		
		
		return kitchenware;
	}

	@Override
	public void validate(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		
		if(!errors.hasErrors("code")) {
			Kitchenware existing;
			String code;
			code = entity.getCode();
			existing = this.repository.findOneKitchenwareByCode(code);
			
			errors.state(request, existing == null ||  entity.getId() == existing.getId(), "code", "chef.kitchenware.error.duplicated");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final String[] acceptedCurrencies=this.systemConfigRepository.findAcceptedCurrencies().split(",");
			final List<String> currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entity.getRetailPrice().getCurrency()), 
				"retailPrice", "chef.kitchenware.form.error.retailPrice.currency-not-supported");
			errors.state(request, entity.getRetailPrice().getAmount()>0., "retailPrice", "chef.kitchenware.form.error.retailPrice.negativeOrZero");
		}
		
		final SpamDetector2 spamDetector = new SpamDetector2(this.systemConfigRepository.findSpamTuple(), this.systemConfigRepository.findSpamThreshold());
		
		if (!errors.hasErrors("name")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getName()), "name", "spamDetector.spamDetected");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, !spamDetector.stringHasManySpam(entity.getDescription()), "description", "spamDetector.spamDetected");
		}
		
	}

	@Override
	public void create(final Request<Kitchenware> request, final Kitchenware entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

	

}
