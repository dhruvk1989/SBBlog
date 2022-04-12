package com.dhruv.blogapp.security;

import com.dhruv.blogapp.model.Role;
import com.dhruv.blogapp.model.User;
import com.dhruv.blogapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Username or email " + usernameOrEmail + " does not exist."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRoleToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthority(Set<Role> roles){
        Iterator itr = roles.iterator();
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        while(itr.hasNext()){
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(((Role) itr.next()).getName());
            simpleGrantedAuthorities.add(grantedAuthority);
        }
        return simpleGrantedAuthorities;
    }
}
