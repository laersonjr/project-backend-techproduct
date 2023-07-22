package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;

public interface IAuthenticationService {

    String authenticateUser(UserAuthentication login);

}
