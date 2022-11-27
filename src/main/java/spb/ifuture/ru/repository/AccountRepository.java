package spb.ifuture.ru.repository;

import spb.ifuture.ru.persistance.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {}
