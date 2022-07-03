
package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.patronages.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AuthenticatedMoneyExchangePerformService moneyExchangePerformService;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int masterId;
		Patronage patronage;

		masterId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(masterId);
		result = patronage != null && patronage.getPatron().getId() == request.getPrincipal().getActiveRoleId();

		return result;
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
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "status", "published");

		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		final String systemCurrency = sc.getSystemCurrency();
		final Money budget = entity.getBudget();
		final boolean budgetIsInSystemCurrency = systemCurrency.equals(budget.getCurrency());
		model.setAttribute("budgetIsInSystemCurrency", budgetIsInSystemCurrency);
		if (!budgetIsInSystemCurrency) {
			final Money budgetChanged;

			budgetChanged = this.moneyExchangePerformService.computeMoneyExchange(budget, systemCurrency).getChange();

			model.setAttribute("budgetChanged", budgetChanged);

		}

		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("inventorId", entity.getInventor().getId());

		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorStatement", entity.getInventor().getStatement());
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getIdentity().getEmail());
		model.setAttribute("inventorInfo", entity.getInventor().getInfo());
	}

}
