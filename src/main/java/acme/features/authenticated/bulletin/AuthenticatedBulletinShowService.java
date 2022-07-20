package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin>{

	@Autowired
	protected AuthenticatedBulletinRepository repo;
	
	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		int id;
		Bulletin bulletin;
		boolean res;
		
		Calendar calendar;
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date deadline;
		deadline = calendar.getTime();
		
		id = request.getModel().getInteger("id");
		bulletin = this.repo.findOneBulletinById(id);
		res = bulletin.getMoment().after(deadline) && request.getPrincipal().isAuthenticated();
		
		return res;
	}

	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request != null;
		int id;
		Bulletin bulletin;
		
		id = request.getModel().getInteger("id");
		bulletin = this.repo.findOneBulletinById(id);
		
		return bulletin;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "moment", "text", "critical", "link");
		
	}

	

}
