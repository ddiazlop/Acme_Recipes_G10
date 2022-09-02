
package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memoranda;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface EpicureMemorandaRepository extends AbstractRepository {

	@Query("select m from Memoranda m where m.epicure.id = :id")
	Collection<Memoranda> findMemorandaByEpicure(int id);

	@Query("select m from Memoranda m where m.id = :id")
	Memoranda findOneMemorandumById(int id);
	
	
}
