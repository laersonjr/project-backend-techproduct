package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;
import com.laerson.techsolutio.techproduct.core.security.interfaces.ITokenProvide;
import com.laerson.techsolutio.techproduct.domain.entity.User;
import com.laerson.techsolutio.techproduct.domain.exception.IncorrectPasswordException;
import com.laerson.techsolutio.techproduct.domain.exception.UserNotFoundException;
import com.laerson.techsolutio.techproduct.domain.repository.UserRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IAuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final ITokenProvide iTokenProvide;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(ITokenProvide iTokenProvide, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.iTokenProvide = iTokenProvide;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String authenticateUser(UserAuthentication login) {
        User userFound = userRepository.findByName(login.getName());

        if (validateUserExists(userFound)) {
            throw new UserNotFoundException();
        }


        if (!passwordEncoder.matches(login.getPassword(), userFound.getPassword())) {
            throw new IncorrectPasswordException();
        }

        String token = iTokenProvide.generateToken(login.getName());

        return token;
    }

    private boolean validateUserExists(User userFound) {
        return userFound == null;
    }

}
