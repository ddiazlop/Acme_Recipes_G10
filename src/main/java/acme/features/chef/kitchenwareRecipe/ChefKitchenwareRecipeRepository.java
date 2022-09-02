package acme.features.chef.kitchenwareRecipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefKitchenwareRecipeRepository extends AbstractRepository{
	
	@Query("Select kwr From KitchenwareRecipe kwr Where kwr.id = :id")
	KitchenwareRecipe findOneById(int id);
	
	@Query("Select kwr From KitchenwareRecipe kwr Where kwr.recipe.id = :id")
	Collection<KitchenwareRecipe> findKitchenwareRecipesByRecipeId(int id);
	
	@Query("Select r From Recipe r Where r.id = :id")
	Recipe findRecipeById(int id);
}
