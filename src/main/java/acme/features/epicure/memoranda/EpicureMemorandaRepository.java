package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memoranda;
import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface EpicureMemorandaRepository extends AbstractRepository {

	@Query("select m from Memoranda m where m.epicure.id = :id")
	Collection<Memoranda> findMemorandaByEpicure(int id);

	@Query("select m from Memoranda m where m.id = :id")
	Memoranda findOneMemorandumById(int id);
	
	/*@Query("select c from Chef c where c.userAccount.name = :name")
	Chef findChefByName(String name);
	
	@Query("select c from FineDish c where c.code = :code")
	FineDish findFineDishByName(String code);*/
}