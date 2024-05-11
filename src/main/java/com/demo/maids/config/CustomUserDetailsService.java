package com.demo.maids.config;


import com.demo.maids.model.Account;
import com.demo.maids.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = null;
        List<GrantedAuthority> authorities = null;
        Account account = this.accountRepository.findUserByUsername(username);
        if(account == null)
            throw new UsernameNotFoundException("User Details are not found for "+username);
        else {

            password = account.getPassword();
            authorities = new ArrayList<>();

        }
        return new User(username, password, authorities);
    }

}
