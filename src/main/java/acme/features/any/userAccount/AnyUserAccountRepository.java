
package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount finOneUserAccountById(int id);

	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findAllUserAccounts();

	@Query("select ua from UserAccount ua join fetch ua.roles r where ua.enabled=1 and Administrator not in (select type(r2) from ua.roles r2)")
	Collection<UserAccount> findSystemUserAccounts();

}
