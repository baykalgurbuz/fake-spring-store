package com.spring.fakestore.fakestore.service;

import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserService  implements UserDetailsService {

    private UsersRepository userRepository;

    public UserService(UsersRepository Repository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.err.println("DB 'ye bakılıyor");
        // kendi user sınıfımı spring security 'nin istediği user sınıfına dönüştürmek için
        Users myUser = userRepository.getByUsername(username);

        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(myUser.getUsername());
        builder.password(myUser.getPassword());
        List<GrantedAuthority> userRoles = userRepository.getUserRoles(myUser.getUsername());
        builder.authorities(userRoles);
        return builder.build();
    }
}
