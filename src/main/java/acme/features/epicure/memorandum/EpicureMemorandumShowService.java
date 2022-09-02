
package acme.features.epicure.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;


@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum> {

	@Autowired
	protected EpicureMemorandumRepository repository;


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int MemorandumId;
		Memorandum m;

		MemorandumId = request.getModel().getInteger("id");
		m = this.repository.findOneMemorandumById(MemorandumId);
		result = m.getEpicure().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"moment","sequenceNumber","report","info");

	}

}
