
package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportServiceShow implements AbstractShowService<Inventor, PatronageReport> {

	@Autowired
	protected InventorPatronageReportRepository repository;


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int patronageReportId;
		PatronageReport pr;

		patronageReportId = request.getModel().getInteger("id");
		pr = this.repository.findOnePatronageReportById(patronageReportId);
		result = pr.getPatronage().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "memorandum", "info", "sequenceNumber");
		model.setAttribute("patronageId", entity.getPatronage().getId());


	}

}
