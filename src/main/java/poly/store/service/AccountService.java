package poly.store.service;

import java.util.List;

import poly.store.entity.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> findAll();
	
	Account create(Account account);

	Account update(Account account);

	void delete(String username);
	
	Account getInfoAuth();
}
