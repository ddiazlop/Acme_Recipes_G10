package acme.features.chef.ingredient;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Ingredient;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefIngredientRepository extends AbstractRepository{
	
	@Query("select i from Ingredient i where i.chef.id =:chefId")
	Collection<Ingredient> findManyChefIngredients(int chefId);
	
	@Query("select i from Ingredient i where i.id =:id")
	Ingredient findOneIngredientById(int id);
	
	@Query("select c from Chef c where c.id =:id")
	Chef findOneChefById(int id); 
	
	@Query("select i from Ingredient i where i.code =:code")
	Ingredient findOneIngredientByCode(String code); 

}
