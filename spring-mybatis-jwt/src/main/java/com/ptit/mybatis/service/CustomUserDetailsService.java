package com.ptit.mybatis.service;

import com.ptit.mybatis.domain.TblUser;
import com.ptit.mybatis.repository.TblUserInforRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private TblUserInforRepository tblUserInforRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        TblUser user = tblUserInforRepository.findTblUserByLoginName(username);
        if (user != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(user.getRule()));
            return new User(user.getLoginName(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }

}
