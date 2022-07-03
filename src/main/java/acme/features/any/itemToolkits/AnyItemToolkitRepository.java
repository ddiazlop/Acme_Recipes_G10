
package acme.features.any.itemToolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.ItemToolkit;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemToolkitRepository extends AbstractRepository {

	@Query("select it from ItemToolkit it where it.toolkit.id=:id")
	Collection<ItemToolkit> findAllItemToolkitsByToolkitId(int id);

	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findToolkitById(int id);

}
