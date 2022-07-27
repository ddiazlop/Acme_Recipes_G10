package acme.features.chef.object.ingredient;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Object;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefObjectIngredientRepository extends AbstractRepository{
	
	@Query("select o from Object o where o.chef.id =:chefId and o.objectType = 'INGREDIENT'")
	Collection<Object> findManyChefIngredients(int chefId);
	
	@Query("select o from Object o where o.id =:id")
	Object findOneIngredientById(int id);
	
	@Query("select c from Chef c where c.id =:id")
	Chef findOneChefById(int id); 

}
