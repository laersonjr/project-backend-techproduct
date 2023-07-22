package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;
import com.laerson.techsolutio.techproduct.api.dto.response.TokenResponseBody;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

    TokenResponseBody authenticateUser(UserAuthentication login);
    boolean validadTokenByRequest(HttpServletRequest request);

}
