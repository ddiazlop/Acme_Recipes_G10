
package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.ItemType;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);

	@Query("select sum(it.quantity * i.retailPrice.amount) from Item i left join ItemToolkit it on i.id=it.item.id where it.toolkit.id = :id")
	Double getToolkitPriceById(int id);

	@Query("select it.item from ItemToolkit it where it.toolkit.id=:id and it.item.itemType='COMPONENT'")
	Collection<Item> getComponentsFromToolkit(int id);

	@Query("select it.item from ItemToolkit it where it.toolkit.id=:id and it.item.itemType='TOOL'")
	Collection<Item> getToolsFromToolkit(int id);

	@Query("select t from Toolkit t where t.id=:id and t.inventor.id=:inventorId")
	Toolkit findOneToolkitByIdFromInventor(int id, int inventorId);

	@Query("select t from Toolkit t where t.inventor.id=:inventorId")
	Collection<Toolkit> findAllToolkitsByInventor(int inventorId);

	@Query("select sum(it.quantity * i.retailPrice.amount) from Item i left join ItemToolkit it on i.id=it.item.id where it.toolkit.id = :id and i.retailPrice.currency=:currency")
	Double getToolkitPricesByIdAndCurrency(int id, String currency);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select it from ItemToolkit it where it.toolkit.id=:id")
	Collection<ItemToolkit> getToolkitContentByToolkitId(int id);

	@Query("select it.item from ItemToolkit it where it.item.itemType=:type and it.toolkit.id=:id")
	Collection<Item> findItemByTypeFromToolkit(int id, ItemType type);

	@Query("select i from Inventor i where i.id=:id")
	Inventor findInventorById(int id);

	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findOneToolkitByCode(String code);

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);

}
