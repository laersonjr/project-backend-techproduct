package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;
import com.laerson.techsolutio.techproduct.api.dto.response.TokenResponseBody;
import com.laerson.techsolutio.techproduct.core.security.exception.TokenNotFoundException;
import com.laerson.techsolutio.techproduct.core.security.interfaces.ITokenProvide;
import com.laerson.techsolutio.techproduct.domain.entity.User;
import com.laerson.techsolutio.techproduct.domain.exception.IncorrectPasswordException;
import com.laerson.techsolutio.techproduct.domain.exception.UserNotFoundException;
import com.laerson.techsolutio.techproduct.domain.repository.UserRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public TokenResponseBody authenticateUser(UserAuthentication login) {
        User userFound = userRepository.findByName(login.getName());

        if (validateUserExists(userFound)) {
            throw new UserNotFoundException();
        }


        if (!passwordEncoder.matches(login.getPassword(), userFound.getPassword())) {
            throw new IncorrectPasswordException();
        }

        String token = iTokenProvide.generateToken(login.getName());
        int expiresIn = iTokenProvide.getJwtExpirationMs();
        Date expirationDate = iTokenProvide.getExpiryDateFromToken(token);


        return new TokenResponseBody(token, expiresIn, expirationDate);
    }

    @Override
    public boolean validadTokenByRequest(HttpServletRequest request) {
        return iTokenProvide.validateToken(getTokenByRequest(request));
    }

    private boolean validateUserExists(User userFound) {
        return userFound == null;
    }

    private String getTokenByRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new TokenNotFoundException();
        }
        String token = header.split(" ")[1];
        iTokenProvide.validateToken(token);
        return token;
    }

}
