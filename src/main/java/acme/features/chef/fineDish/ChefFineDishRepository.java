package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefFineDishRepository extends AbstractRepository{

	
	@Query("Select fd From FineDish fd Where fd.id = :id")
	FineDish findFineDishById(int id);
	
	@Query("Select fd From FineDish fd Where fd.chef.id = :chefId")
	Collection<FineDish> findFineDishesByChefId(int chefId);
	
	@Query("Select fd From FineDish fd Where fd.chef.id = :chefId and fd.published = TRUE")
	Collection<FineDish> findFinePublishedDishesByChefId(int chefId);
	
	@Query("Select c From Chef c Where c.id = :id")
	Chef findChefById(int id);
}
