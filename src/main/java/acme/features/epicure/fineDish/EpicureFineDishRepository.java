
package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.Memoranda;
import acme.entities.recipes.Kitchenware;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select fd from FineDish fd where fd.epicure.id = :id")
	Collection<FineDish> findFineDishByEpicure(int id);

	@Query("select fd from FineDish fd where fd.id = :id")
	FineDish findOneFineDishById(int id);
	
	@Query("select ep from Epicure ep where ep.id = :id")
	Epicure findOneEpicureById(int id);
	
	@Query("select r from FineDish r where r.code = :code")
	FineDish findOneFineDishByCode(String code);

	@Query("select kwr from Kitchenware kwr where kwr.code = :code")
	Kitchenware findOneKitchenwareByCode(String code);
	
	@Query("select chef from Chef chef")
	Collection<Chef> findAllChefs();
	
	@Query("select c.userAccount.username from Chef c ")
	Collection<String> findAllChefUsernames();
	
	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefById(int id);
	
	@Query("select c from Chef c where c.userAccount.username = :username")
	Chef findOneChefByUsername(String username);
	
	@Query("select m from Memoranda m where m.fineDish.id = :id")
	Collection<Memoranda> findManyMemorandaByFineDishId(int id);
}
