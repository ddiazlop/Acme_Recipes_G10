package acme.features.chef.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.Memoranda;
import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandaRepository extends AbstractRepository{
	
	@Query("Select m From Memoranda m Where m.id = :id")
	Memoranda findOneById(int id);
	
	@Query("Select m From Memoranda m Where m.chef.id = :id")
	Collection<Memoranda> findMemorandaByChefId(int id);
	
	@Query("Select m From Memoranda m Where m.fineDish.id = :id")
	Collection<Memoranda> findMemorandaByFineDishId(int id);
	
	@Query("Select fd From FineDish fd Where fd.id = :id")
	FineDish findFineDishById(int id);
	
	@Query("Select count(m) From Memoranda m")
	Integer countMemoranda();
	
	@Query("Select sc From SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfigurationSep();
	

}
