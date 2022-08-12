package acme.features.chef.recipes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefRecipeRepository extends AbstractRepository{
	
	@Query("Select r From Recipe r")
	Collection<Recipe> findAllRecipes();
	
	@Query("Select r From Recipe r Where r.id = :id")
	Recipe findOneRecipeById(Integer id);
	
	@Query("Select r From Recipe r Where r.chef.id = :id")
	Collection<Recipe> findRecipesByChefId(Integer id);
	
	@Query("select sum(kwr.quantity * kw.retailPrice.amount) from Kitchenware kw Left Join KitchenwareRecipe kwr On kw.id=kwr.kitchenware.id Where kwr.recipe.id = :id")
	Double getRecipePriceById(Integer id);
	
	@Query("select sum(kwr.quantity * kw.retailPrice.amount) from Kitchenware kw Left Join KitchewareRecipe kwr On kw.id=kwr.kitchenware.id Where kwr.recipe.id = :id And kw.retailPrice.currency = :currency")
	Double getRecipePricesByIdAndCurrency(int id, String currency);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
}
