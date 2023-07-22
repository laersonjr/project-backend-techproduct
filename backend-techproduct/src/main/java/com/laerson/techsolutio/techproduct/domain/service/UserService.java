package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.request.UserRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.UserResponseBody;
import com.laerson.techsolutio.techproduct.core.modelmapper.interfaces.IModelMapperDTOConverter;
import com.laerson.techsolutio.techproduct.domain.entity.User;
import com.laerson.techsolutio.techproduct.domain.repository.UserRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IModelMapperDTOConverter dtoConverter;

    public UserService(UserRepository userRepository, IModelMapperDTOConverter dtoConverter) {
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public UserResponseBody createUser(UserRequestBody userRequestBody) {
        User newUser = dtoConverter.convertToEntity(userRequestBody, User.class);
        newUser.setId(UUID.randomUUID());
        userRepository.save(newUser);
        UserResponseBody createdUser = dtoConverter.convertToModelDTO(newUser, UserResponseBody.class);
        return createdUser;
    }
}
