package acme.features.any.object.ingredient;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import acme.entities.Object;
import acme.framework.repositories.AbstractRepository;

@Controller
public interface AnyObjectIngredientRepository  extends AbstractRepository{
	
	@Query("select o from Object o where o.published = TRUE and o.objectType = 'INGREDIENT'")
	Collection<Object> findPublishedIngredients();
	
	@Query("select o from Object o where o.id =:id")
	Object findOneIngredientById(int id);

}
