package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedBulletinListService implements AbstractListService<Authenticated, Bulletin>{

	@Autowired
	protected AuthenticatedBulletinRepository repo;
	
	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		return request.getPrincipal().isAuthenticated();
	}

	@Override
	public Collection<Bulletin> findMany(final Request<Bulletin> request) {
		assert request != null;
		Collection<Bulletin> res;
		Calendar calendar;
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date deadline;
		deadline = calendar.getTime();
		res = this.repo.findManyBulletinsByDeadline(deadline);
		
		return res;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "moment");
		
	}

	

}
