package acme.features.inventor.itemToolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();

	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findToolkitById(int	id);

	@Query("select it from ItemToolkit it where it.item.id=:itemId and it.toolkit.id=:toolkitId")
	ItemToolkit findItemToolkitByItemAndToolkit(int itemId, int toolkitId);
	
	@Query("select i from Item i where i.id=:itemId")
	Item findItemById(int itemId);

	@Query("select i from Item i where i.published=true")
	Collection<Item> findAllPublishedItems();

	@Query("select it.item from ItemToolkit it where it.item.id=:itemId and it.toolkit.id=:toolkitId")
	Item findItemByIdInToolkit(int itemId, int toolkitId);

	@Query("select it from ItemToolkit it where it.toolkit.id=:id")
	Collection<ItemToolkit> findAllItemToolkitsByToolkitId(int id);

	@Query("select it from ItemToolkit it where it.id=:id")
	ItemToolkit findItemToolkitById(int id);

	@Query("select it.item from ItemToolkit it where it.item.itemType='TOOL' and it.toolkit.id=:id")
	Item findToolInToolkit(int id);
	
}
