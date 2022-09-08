package acme.features.authenticated.bulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBulletinRepository extends AbstractRepository{
	
	@Query("select b from Bulletin b where b.moment > :deadline")
	Collection<Bulletin> findManyBulletinsByDeadline(Date deadline);
	
	@Query("select b from Bulletin b where b.id =:id")
	Bulletin findOneBulletinById(int id);

}
