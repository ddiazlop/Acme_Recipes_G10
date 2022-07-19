package acme.features.any.ingredient;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import acme.entities.Ingredient;
import acme.framework.repositories.AbstractRepository;

@Controller
public interface AnyIngredientRepository  extends AbstractRepository{
	
	@Query("select i from Ingredient i where i.published = TRUE")
	Collection<Ingredient> findPublishedIngredients();
	
	@Query("select i from Ingredient i where i.id =:id")
	Ingredient findOneIngredientById(int id);

}
