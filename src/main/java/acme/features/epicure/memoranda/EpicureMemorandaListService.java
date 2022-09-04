package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandaListService implements AbstractListService<Epicure, Memoranda> {

	@Autowired
	protected EpicureMemorandaRepository repository;


	@Override
	public boolean authorise(final Request<Memoranda> request) {
		assert request != null;
		return request.getPrincipal().hasRole(Epicure.class);
	}

	@Override
	public Collection<Memoranda> findMany(final Request<Memoranda> request) {
		assert request != null;

		Collection<Memoranda> result;
		int epicureId;

		epicureId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findMemorandaByEpicure(epicureId);

		return result;

	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"moment","sequenceNumber");

	}

}