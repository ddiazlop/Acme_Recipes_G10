package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memoranda;
import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;


@Repository
public interface EpicureMemorandaRepository extends AbstractRepository {

	@Query("select m from Memoranda m where m.epicure.id = :id")
	Collection<Memoranda> findMemorandaByEpicure(int id);

	@Query("select m from Memoranda m where m.id = :id")
	Memoranda findOneMemorandumById(int id);
	
	@Query("select m.sequenceNumber from Memoranda m where m.fineDish.id = :fineDishId and m.epicure.id = :epicureId")
	Collection<String> findAllMemorandaCodesFromFineDishIdAndEpicureId(int fineDishId, int epicureId);
	
	@Query("select e from Epicure e where e.id = :id")
	Epicure findEpicureById(int id);
	
	@Query("select c from Chef c where c.userAccount.username = :name")
	Chef findChefByUsername(String name);
	
	@Query("select fd from FineDish fd where fd.code = :code")
	FineDish findFineDishByCode(String code);
}