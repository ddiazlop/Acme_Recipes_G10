
package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.inventor.id = :inventorId and i.itemType = 'COMPONENT'")
	Collection<Item> findAllComponentsFromInventor(int inventorId);

	@Query("select i from Item i where i.itemType = 'TOOL' and i.inventor.id = :inventorId")
	Collection<Item> findAllToolsFromInventor(int inventorId);

	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	@Query("select i from Inventor i where i.id=:id")
	Inventor findOneInventorById(int id);
	
	@Query("select t from Item t where t.code=:code")
	Item getItemByCode(String code);
	
	@Query("select it from ItemToolkit it where it.item.id=:id")
    Collection<ItemToolkit> findItemToolkitByItemId(int id);
}
