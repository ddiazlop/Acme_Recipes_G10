package acme.features.chef.kitchenwareRecipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefKitchenwareRecipeRepository extends AbstractRepository{
	
	@Query("Select kwr From KitchenwareRecipe kwr Where kwr.id = :kitchenwareRecipeId")
	KitchenwareRecipe findKitchenwareRecipeById(int kitchenwareRecipeId);
	
	@Query("Select kwr From KitchenwareRecipe kwr Where kwr.recipe.id = :recipeId")
	Collection<KitchenwareRecipe> findAllKitchenwareRecipesByRecipeId(int recipeId);
	
	@Query("Select r From Recipe r Where r.id = :id")
	Recipe findRecipeById(int id);
	
	@Query ("Select kw From Kitchenware kw Where kw.id= :kitchenwareId")
	Kitchenware findKitchenwareById(int kitchenwareId);
	
	@Query ("Select kw From Kitchenware kw Where kw.code= :code")
	Kitchenware findKitchenwareByCode(String code);
	
	@Query("Select kw From Kitchenware kw Where kw.published = TRUE")
	Collection<Kitchenware> findAllPublishedKitchenwares();
	
	@Query("Select kw From Kitchenware kw Where kw.published = TRUE and kw.wareType = 'KITCHEN_UTENSIL'")
	Collection<Kitchenware> findAllPublishedKitchenUtensils();
	
	@Query("Select kw From Kitchenware kw Where kw.published = TRUE and kw.wareType = 'INGREDIENT'")
	Collection<Kitchenware> findAllPublishedIngredients();
	
	@Query("Select kwr.kitchenware From KitchenwareRecipe kwr Where kwr.kitchenware.id= :kitchenwareId and kwr.recipe.id = :recipeId")
	Kitchenware findKitchenwareByIdInRecipe(int kitchenwareId, int recipeId);

	
	
	
	
	
	
}

