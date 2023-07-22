package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;
import com.laerson.techsolutio.techproduct.api.dto.response.TokenResponseBody;

public interface IAuthenticationService {

    TokenResponseBody authenticateUser(UserAuthentication login);

}
