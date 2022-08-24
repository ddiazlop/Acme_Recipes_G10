
package acme.features.any.recipes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfigurationSep;
import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {

	@Query("select t from Recipe t where t.published = true")
	Collection<Recipe> findAllPublishedRecipes();

	@Query("select t from Recipe t where t.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select sc from SystemConfigurationSep sc")
	SystemConfigurationSep findSystemConfiguration();

	@Query("select sum(it.quantity * i.retailPrice.amount) from Kitchenware i left join KitchenwareRecipe it on i.id=it.kitchenware.id where it.recipe.id = :id")
	Double getRecipePriceById(int id);

	@Query("select sum(it.quantity * i.retailPrice.amount) from Kitchenware i left join KitchenwareRecipe it on i.id=it.kitchenware.id where it.recipe.id = :id and i.retailPrice.currency=:currency")
	Double getRecipePricesByIdAndCurrency(int id, String currency);

	@Query("select it.kitchenware from KitchenwareRecipe it where it.recipe.id=:id and it.kitchenware.wareType='INGREDIENT'")
	Collection<Kitchenware> getIngredientsFromRecipe(int id);

	@Query("select it.kitchenware from KitchenwareRecipe it where it.recipe.id=:id and it.kitchenware.wareType='KITCHEN_UTENSIL'")
	Collection<Kitchenware> getKitchenUtensilsFromRecipe(int id);
	
	@Query("select it.kitchenware from KitchenwareRecipe it where it.recipe.id=:id")
	Collection<Kitchenware> getKitchenwaresFromRecipe(int id);

}
