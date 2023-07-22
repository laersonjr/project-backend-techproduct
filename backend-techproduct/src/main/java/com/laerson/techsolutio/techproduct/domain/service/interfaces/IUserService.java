package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.request.UserRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.UserResponseBody;

public interface IUserService {

    UserResponseBody createUser(UserRequestBody userRequestBody);

}
