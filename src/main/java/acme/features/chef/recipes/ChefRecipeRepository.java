package acme.features.chef.recipes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.entities.recipes.WareType;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface ChefRecipeRepository extends AbstractRepository{
	
	@Query("Select r From Recipe r")
	Collection<Recipe> findAllRecipes();
	
	@Query("Select r From Recipe r Where r.id = :id")
	Recipe findOneRecipeById(Integer id);
	
	
	@Query("Select r From Recipe r Where r.chef.id = :id")
	Collection<Recipe> findAllRecipesByChefId(Integer id);
	
	@Query("Select kw From Kitchenware kw Left Join KitchenwareRecipe kwr On kw.id=kwr.kitchenware.id Where kwr.recipe.id = :id")
	Collection<Kitchenware> getKitchenwaresOfARecipe(int id);
	
	@Query("Select kwr.quantity From KitchenwareRecipe kwr Where kwr.recipe.id = :recipeId And kwr.kitchenware.id = :kitchenwareId")
	Double getKitchenwareQuantityFromARecipe(int recipeId, int kitchenwareId);
	
	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfigurationSep();
	
	@Query("select sum(kitchenR.quantity * k.retailPrice.amount) from Kitchenware k left join KitchenwareRecipe kitchenR"
		+ " on k.id=kitchenR.kitchenware.id where kitchenR.recipe.id = :id and k.retailPrice.currency=:currency")
	Double getRecipePricesByIdAndCurrency(int id, String currency);
	
	@Query("select kwR.kitchenware from KitchenwareRecipe kwR where kwR.kitchenware.id=:id and kwR.kitchenware.wareType='INGREDIENT' ")
	Collection<Kitchenware> getIngredientsFromRecipe(int id);

	@Query("select kwR.kitchenware from KitchenwareRecipe kwR where kwR.kitchenware.id=:id and kwR.kitchenware.wareType='KITCHEN_UTENSIL'")
	Collection<Kitchenware> getUtensilsFromRecipe(int id);

	@Query("select kwr.kitchenware from KitchenwareRecipe kwr where kwr.kitchenware.wareType=:type and kwr.recipe.id=:id")
	Collection<Kitchenware> findKitchenwareByTypeFromRecipe(int id, WareType type);
	
	@Query("select kwr from KitchenwareRecipe kwr where kwr.recipe.id=:id")
	Collection<KitchenwareRecipe> getRecipeContentByRecipeId(int id);
		
	@Query("select c from Chef c where c.id=:id")
	Chef findChefById(int id);
	
	@Query("select r from Recipe r where r.code = :code")
	Recipe findOneRecipeByCode(String code);
	
	@Query("select kwr from Kitchenware kwr where kwr.code = :code")
	Kitchenware findOneKitchenwareByCode(String code);
	@Query("select r from Recipe r where r.id=:id and r.chef.id=:chefId")
	Recipe findOneRecipeByIdFromChef(int id, int chefId);

	
}
