package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.Pimpam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPimpamRepository extends AbstractRepository{
	
	@Query("Select p From Pimpam p Where p.id = :id")
	Pimpam findOnePIMPAMById(int id);
	
	@Query("Select p From Pimpam p Where p.chef.id = :id")
	Collection<Pimpam> findPIMPAMByChefId(int id);
	
	@Query("Select p From Pimpam p Where p.kitchenware.id = :id")
	Pimpam findOnePIMPAMByKitchenwareId(int id);
	
	@Query("Select sc From SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfigurationSep();

}
