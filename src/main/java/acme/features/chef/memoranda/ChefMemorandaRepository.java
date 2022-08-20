package acme.features.chef.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandaRepository extends AbstractRepository{
	
	@Query("Select m From Memorandum m Where m.id = :id")
	Memorandum findOneById(int id);
	
	@Query("Select m From Memorandum m Where m.chef.id = :id")
	Collection<Memorandum> findMemorandaByChefId(int id);
	
	@Query("Select m From Memorandum m Where m.fineDish.id = :id")
	Collection<Memorandum> findMemorandumByFineDishId(int id);
	

}
