
package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishListService implements AbstractListService<Epicure,FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;

		Collection<FineDish> result;
		int fineDishId;

		fineDishId = request.getModel().getInteger("fineDishId");
		result = this.repository.findFineDishByFineDish(fineDishId);

		return result;

	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "status", "code","request","budget","creationDate","startDate", 
				 "endDate","info");

	}

}
