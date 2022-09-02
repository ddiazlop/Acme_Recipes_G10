package acme.features.chef.kitchenware;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipes.Kitchenware;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefKitchenwareRepository extends AbstractRepository{
	
	@Query("select k from Kitchenware k where k.chef.id =:chefId")
	Collection<Kitchenware> findManyChefKitchenwares(int chefId);
	
	@Query("select k from Kitchenware k where k.id =:id")
	Kitchenware findOneKitchenwareById(int id);
	
	@Query("select c from Chef c where c.id =:id")
	Chef findOneChefById(int id); 

}
