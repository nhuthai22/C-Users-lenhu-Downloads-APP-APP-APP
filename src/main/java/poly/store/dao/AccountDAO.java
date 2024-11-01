package poly.store.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Account;
import poly.store.entity.Trip;

public interface AccountDAO extends JpaRepository<Account, String>{
	@Query("select o from Account o where o.username = ?1")
    Optional<Account> FindByUserName(String username);

	Optional<Trip> findByUsername(String name);
}
