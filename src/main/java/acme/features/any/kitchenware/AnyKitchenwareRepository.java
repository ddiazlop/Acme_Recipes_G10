package acme.features.any.kitchenware;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Kitchenware;
import acme.framework.repositories.AbstractRepository;

@Controller
public interface AnyKitchenwareRepository  extends AbstractRepository{
	
	@Query("select o from Kitchenware o where o.published = TRUE and o.wareType = 'INGREDIENT'")
	Collection<Kitchenware> findPublishedIngredients();
	
	@Query("select o from Kitchenware o where o.id =:id")
	Kitchenware findOneKitchenwareById(int id);
	
	@Query("select c from Kitchenware c where c.wareType = 'KITCHEN_UTENSIL' and c.published = TRUE")
	Collection<Kitchenware> findAllPublishedUtensils();

}
