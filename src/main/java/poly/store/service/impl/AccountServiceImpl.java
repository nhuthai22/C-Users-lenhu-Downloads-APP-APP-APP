package poly.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDAO;
import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
    private AccountDAO adao;

    @Override
    public Account findById(String username) {
        Optional<Account> optionalAccount = adao.findById(username);
        return optionalAccount.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
    }

    @Override
    public List<Account> findAll() {
        return adao.findAll();
    }

    @Override
    public Account create(Account account) {
        return adao.save(account);
    }

    @Override
    public Account update(Account account) {
        // Ensure the account exists before updating
        if (!adao.existsById(account.getUsername())) {
            throw new UsernameNotFoundException(account.getUsername() + " not found!");
        }
        return adao.save(account);
    }

    @Override
    public void delete(String username) {
        // Ensure the account exists before deleting
        if (!adao.existsById(username)) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        adao.deleteById(username);
    }

    @Override
    public Account getInfoAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new IllegalStateException("No authenticated user found!");
        }
        return adao.FindByUserName(auth.getName())
                   .orElseThrow(() -> new UsernameNotFoundException(auth.getName() + " not found!"));
    }
}
