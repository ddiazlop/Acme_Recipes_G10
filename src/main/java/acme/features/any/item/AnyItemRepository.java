
package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select c from Item c where c.itemType = 'COMPONENT' and c.published = TRUE")
	Collection<Item> findAllComponents();

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);

	@Query("select c from Item c where c.itemType = 'TOOL' and c.published = TRUE")
	Collection<Item> findAllTools();

}
