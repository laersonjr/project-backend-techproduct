package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.request.UserRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.UserResponseBody;
import com.laerson.techsolutio.techproduct.core.modelmapper.interfaces.IModelMapperDTOConverter;
import com.laerson.techsolutio.techproduct.domain.entity.User;
import com.laerson.techsolutio.techproduct.domain.exception.UserAlreadyExistsException;
import com.laerson.techsolutio.techproduct.domain.exception.UserNotFoundException;
import com.laerson.techsolutio.techproduct.domain.repository.UserRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IModelMapperDTOConverter dtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, IModelMapperDTOConverter dtoConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponseBody createUser(UserRequestBody userRequestBody) {
        User newUser = dtoConverter.convertToEntity(userRequestBody, User.class);
        User userFound = userRepository.findByName(newUser.getName());
        if (!(userFound==null)) {
            throw new UserAlreadyExistsException();
        }
        newUser.setRandomId();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        UserResponseBody createdUser = dtoConverter.convertToModelDTO(newUser, UserResponseBody.class);
        return createdUser;
    }
}
