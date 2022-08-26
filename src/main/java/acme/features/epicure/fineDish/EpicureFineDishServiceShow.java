
package acme.features.epicure.fineDish;

import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishServiceShow implements AbstractShowService<Epicure, FineDish> {

	protected EpicureFineDishRepository repository;


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int FineDishId;
		FineDish fd;

		FineDishId = request.getModel().getInteger("id");
		fd = this.repository.findOneFineDishById(FineDishId);
		result = fd.getEpicure().getId() == request.getPrincipal().getActiveRoleId();

		return result;
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
				 "endDate","info");
		model.setAttribute("fineDishId", entity.getId());

	}

}
