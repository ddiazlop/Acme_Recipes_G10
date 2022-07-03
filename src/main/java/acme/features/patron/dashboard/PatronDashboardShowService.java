package acme.features.patron.dashboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.datatypes.StatData;
import acme.entities.patronages.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		final PatronDashboard result;
		final Integer numPatronageProposed;
		final Integer numPatronageAccepted;
		final Integer numPatronageDenied;
		final Map<Pair<PatronageStatus, String>, StatData> patronageBudgetData = new HashMap<>();
		final int patronId = request.getPrincipal().getActiveRoleId();
				
		numPatronageProposed = this.repository.numPatronagesByStatus(PatronageStatus.PROPOSED, patronId);
		numPatronageAccepted = this.repository.numPatronagesByStatus(PatronageStatus.ACCEPTED, patronId);
		numPatronageDenied = this.repository.numPatronagesByStatus(PatronageStatus.DENIED, patronId);
		
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		final List<String> acceptedCurrencies = Arrays.asList(sc.getAcceptedCurrencies().trim().split(","));
		
		for (final PatronageStatus ps : PatronageStatus.values()) {
			acceptedCurrencies.forEach(x -> patronageBudgetData.put(Pair.of(ps, x), StatData.of(this.repository.getPatronageBudgetDataByIdAndStatusAndCurrency(ps, x, patronId), x)));
		}

		result = new PatronDashboard();		
		result.setNumPatronageProposed(numPatronageProposed);
		result.setNumPatronageAccepted(numPatronageAccepted);
		result.setNumPatronageDenied(numPatronageDenied);
		result.setPatronageBudgetData(patronageBudgetData);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Map<String, StatData> proposed=new HashMap<String, StatData>();
		final Map<String, StatData> accepteds=new HashMap<String, StatData>();
		final Map<String, StatData> denieds=new HashMap<String, StatData>();
		
		request.unbind(entity, model, "numPatronageProposed", "numPatronageAccepted", "numPatronageDenied");

		for(final String curr: this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",")) {

			proposed.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.PROPOSED, curr)));
			model.setAttribute("proposeds", proposed);
			accepteds.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.ACCEPTED, curr)));
			model.setAttribute("accepteds", accepteds);
			denieds.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.DENIED, curr)));
			model.setAttribute("denieds", denieds);
		}
		
	}

}
