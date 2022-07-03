
package acme.features.administrator.dashboard;

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
import acme.entities.toolkits.ItemType;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdminDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request != null;
		final AdminDashboard result;

		Integer numComponents;
		Integer numTools;
		Integer numPatronageRequested;
		Integer numPatronageAccepted;
		Integer numPatronageDenied;

		numComponents = this.repository.getNumComps();
		numTools = this.repository.getNumTools();
		numPatronageRequested = this.repository.getNumPatronage(PatronageStatus.PROPOSED);
		numPatronageAccepted = this.repository.getNumPatronage(PatronageStatus.ACCEPTED);
		numPatronageDenied = this.repository.getNumPatronage(PatronageStatus.DENIED);

		final Map<String, StatData> componentsDataByCurrency = new HashMap<>();
		final Map<Pair<String, String>, StatData> componentsDataByTechnology = new HashMap<>();
		final Map<String, StatData> toolsDataByCurrency = new HashMap<>();
		final Map<Pair<String, String>, StatData> toolsDataByTechnology = new HashMap<>();
		final Map<Pair<PatronageStatus, String>, StatData> patronageBudgetData = new HashMap<>();

		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		final List<String> acceptedCurrencies = Arrays.asList(sc.getAcceptedCurrencies().trim().split(","));

		//---------Components and Tools Data By Currency--------------

		acceptedCurrencies.forEach(x -> componentsDataByCurrency.put(x, StatData.of(this.repository.getItemDataByCurr(ItemType.COMPONENT, x), x)));
		acceptedCurrencies.forEach(x -> toolsDataByCurrency.put(x, StatData.of(this.repository.getItemDataByCurr(ItemType.TOOL, x), x)));

		//---------Components and Tools Data By Currency and Technology--------------

		final List<String> technologiesAvaliables = this.repository.getTechnologies();
		for (final String technology : technologiesAvaliables) {
			acceptedCurrencies.forEach(x -> componentsDataByTechnology.put(Pair.of(technology, x), StatData.of(this.repository.getItemDataByTechnologyAndCurrency(ItemType.COMPONENT, technology, x), x)));
		}

		for (final String technology : technologiesAvaliables) {
			acceptedCurrencies.forEach(x -> toolsDataByTechnology.put(Pair.of(technology, x), StatData.of(this.repository.getItemDataByTechnologyAndCurrency(ItemType.TOOL, technology, x), x)));
		}

		//---------Patronage Data By Status and Currency-----------------------

		for (final PatronageStatus status : PatronageStatus.values()) {
			acceptedCurrencies.forEach(x -> patronageBudgetData.put(Pair.of(status, x), StatData.of(this.repository.getPatronageBudgetDataByStatusAndCurrency(status, x), x)));
		}

		result = new AdminDashboard();

		result.setNumComponents(numComponents);
		result.setNumTools(numTools);
		result.setNumPatronageRequested(numPatronageRequested);
		result.setNumPatronageAccepted(numPatronageAccepted);
		result.setNumPatronageDenied(numPatronageDenied);
		result.setComponentsDataByCurrency(componentsDataByCurrency);
		result.setComponentsDataByTechnology(componentsDataByTechnology);
		result.setToolsDataByTechnology(toolsDataByTechnology);
		result.setToolsDataByCurrency(toolsDataByCurrency);
		result.setPatronageBudgetData(patronageBudgetData);

		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numComponents", "numTools", "numPatronageRequested", "numPatronageAccepted", "numPatronageDenied", "componentsDataByTechnology", "toolsDataByTechnology");
		final Map<String, StatData> accepteds=new HashMap<String, StatData>();
		final Map<String, StatData> denieds=new HashMap<String, StatData>();
		final Map<String, StatData> pendings=new HashMap<String, StatData>();
		final Map<String, StatData> tools=new HashMap<String, StatData>();
		final Map<String, StatData> comps=new HashMap<String, StatData>();
		
		for(final String curr:this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",")) {
			model.setAttribute("dataComp"+curr,  entity.getComponentsDataByCurrency().get(curr));
			model.setAttribute("dataTool"+curr, entity.getToolsDataByCurrency().get(curr));

			accepteds.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.ACCEPTED, curr)));
			model.setAttribute("accepteds", accepteds);
			denieds.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.DENIED, curr)));
			model.setAttribute("denieds", denieds);
			pendings.put(curr, entity.getPatronageBudgetData().get(Pair.of(PatronageStatus.PROPOSED, curr)));
			model.setAttribute("pendings", pendings);
			
			tools.put(curr, entity.getToolsDataByCurrency().get(curr));
			model.setAttribute("tools", tools);
			comps.put(curr, entity.getComponentsDataByCurrency().get(curr));
			model.setAttribute("comps",comps);
		};
}

}
