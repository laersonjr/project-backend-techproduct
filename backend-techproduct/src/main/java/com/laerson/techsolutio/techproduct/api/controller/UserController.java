package com.laerson.techsolutio.techproduct.api.controller;

import com.laerson.techsolutio.techproduct.api.dto.request.UserRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.UserResponseBody;
import com.laerson.techsolutio.techproduct.domain.entity.User;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @PostMapping
    public ResponseEntity<UserResponseBody> createUser(@Valid @RequestBody UserRequestBody userRequestBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.createUser(userRequestBody));
    }

}
