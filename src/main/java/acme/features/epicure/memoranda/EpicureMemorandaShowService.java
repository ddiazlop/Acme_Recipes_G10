
package acme.features.epicure.memoranda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;


@Service
public class EpicureMemorandaShowService implements AbstractShowService<Epicure, Memoranda> {

	@Autowired
	protected EpicureMemorandaRepository repository;


	@Override
	public boolean authorise(final Request<Memoranda> request) {
		assert request != null;

		boolean result;
		int MemorandumId;
		Memoranda m;

		MemorandumId = request.getModel().getInteger("id");
		m = this.repository.findOneMemorandumById(MemorandumId);
		result = m.getEpicure().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Memoranda findOne(final Request<Memoranda> request) {
		assert request != null;

		Memoranda result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"moment","sequenceNumber","report","info");

	}

}
