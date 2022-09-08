package acme.features.epicure.dashboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfigurationSep;
import acme.datatypes.StatData;
import acme.entities.fineDish.DishStatus;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureDashboardRepository repository;

	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;
		
		final EpicureDashboard result;
		final Integer numDishesProposed;
		final Integer numDishesAccepted;
		final Integer numDishesDenied;
		final Map<Pair<DishStatus, String>, StatData> dishesBudgetData = new HashMap<>();
		final int epicureId = request.getPrincipal().getActiveRoleId();
				
		numDishesProposed = this.repository.numDishesByStatus(DishStatus.PROPOSED, epicureId);
		numDishesAccepted = this.repository.numDishesByStatus(DishStatus.ACCEPTED, epicureId);
		numDishesDenied = this.repository.numDishesByStatus(DishStatus.DENIED, epicureId);
		
		final SystemConfigurationSep sc = this.repository.findSystemConfiguration();
		final List<String> acceptedCurrencies = Arrays.asList(sc.getAcceptedCurrencies().trim().split(","));
		
		for (final DishStatus fds : DishStatus.values()) {
			acceptedCurrencies.forEach(x -> dishesBudgetData.put(Pair.of(fds, x), StatData.of(this.repository.getDishesBudgetDataByIdAndStatusAndCurrency(fds, x, epicureId), x)));
		}

		result = new EpicureDashboard();		
		result.setNumDishesProposed(numDishesProposed);
		result.setNumDishesAccepted(numDishesAccepted);
		result.setNumDishesDenied(numDishesDenied);
		result.setDishesBudgetData(dishesBudgetData);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Map<String, StatData> proposed=new HashMap<String, StatData>();
		final Map<String, StatData> accepteds=new HashMap<String, StatData>();
		final Map<String, StatData> denieds=new HashMap<String, StatData>();
		
		request.unbind(entity, model, "numDishesProposed", "numDishesAccepted", "numDishesDenied");

		for(final String curr: this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",")) {

			proposed.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.PROPOSED, curr)));
			model.setAttribute("proposeds", proposed);
			accepteds.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.ACCEPTED, curr)));
			model.setAttribute("accepteds", accepteds);
			denieds.put(curr, entity.getDishesBudgetData().get(Pair.of(DishStatus.DENIED, curr)));
			model.setAttribute("denieds", denieds);
		}
		
	}

}
