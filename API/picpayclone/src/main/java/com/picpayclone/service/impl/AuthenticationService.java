package com.picpayclone.service.impl;

import com.picpayclone.constants.ValidationMessage;
import com.picpayclone.model.User;
import com.picpayclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    // Attribute
    @Autowired
    private IUserService userService;

    // Methods
    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userService.entityConsult(login);

        if (!userValidation(user)) {
            throw new UsernameNotFoundException(ValidationMessage.ERROR_USER_NOT_ALLOWED);
        }

        return (UserDetails) user;
    }

    private boolean userValidation(User user) {
        boolean validUser = false;

        if (user != null && user.getPermission() != null && user.getActive()) {
            validUser = true;
        }

        return validUser;
    }
}
