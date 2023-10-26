package com.wttch.sdh.sdhserver.component;

import com.wttch.sdh.sdhserver.dao.UserMapper;
import com.wttch.sdh.sdhserver.entity.User;
import jakarta.annotation.Resource;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SdhUserDetailsService implements UserDetailsService {

  @Resource private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        Optional.ofNullable(userMapper.selectByUserName(username))
            .orElseThrow(() -> new UsernameNotFoundException(username));
    return new SdhUserDetails(user);
  }
}
