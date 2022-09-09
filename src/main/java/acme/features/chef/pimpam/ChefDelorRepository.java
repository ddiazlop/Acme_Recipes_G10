package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.Delor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefDelorRepository extends AbstractRepository{
	
	@Query("Select p From Delor p Where p.id = :id")
	Delor findOneDelorById(int id);
	
	@Query("Select p From Delor p Where p.chef.id = :id")
	Collection<Delor> findDelorByChefId(int id);
	
	@Query("Select p From Delor p Where p.kitchenware.id = :id")
	Delor findOneDelorByKitchenwareId(int id);
	
	@Query("Select sc From SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfigurationSep();

}
