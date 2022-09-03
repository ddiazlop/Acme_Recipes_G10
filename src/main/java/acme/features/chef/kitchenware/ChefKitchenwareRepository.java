package acme.features.chef.kitchenware;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefKitchenwareRepository extends AbstractRepository{
	
	@Query("select k from Kitchenware k where k.chef.id =:chefId")
	Collection<Kitchenware> findManyChefKitchenwares(int chefId);
	
	@Query("select k from Kitchenware k where k.id =:id")
	Kitchenware findOneKitchenwareById(int id);
	
	@Query("select k from Kitchenware k where k.code =:code")
	Kitchenware findOneKitchenwareByCode(String code);
	
	@Query("select c from Chef c where c.id =:id")
	Chef findOneChefById(int id); 
	
	@Query("select kr.recipe from KitchenwareRecipe kr where kr.kitchenware.id =:id")
	Collection<Recipe> findManyRecipesByKitchenwareId(int id);
	
	@Query("select kr from KitchenwareRecipe kr where kr.kitchenware.id =:id")
	Collection<KitchenwareRecipe> findManyKitchenwareRecipesByKitchenwareId(int id);
}
