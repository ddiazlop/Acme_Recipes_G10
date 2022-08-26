
package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select fd from FineDish fd where fd.epicure.id = :id")
	Collection<FineDish> findFineDishByEpicure(int id);

	@Query("select fd from FineDish fd where fd.id = :id")
	FineDish findOneFineDishById(int id);

}
