package com.noures.usersclient.service;

import com.noures.usersclient.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface  UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);

}
