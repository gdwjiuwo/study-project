package com.example.service;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements UserDetailsService {
    @Resource
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Account account = userMapper.findAccountByUsernameOrEmail(username);
       if (account == null) {
           throw new UsernameNotFoundException("用户名或密码错误");
       }
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }
}
