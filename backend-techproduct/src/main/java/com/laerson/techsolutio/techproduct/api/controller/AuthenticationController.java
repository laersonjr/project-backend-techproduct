package com.laerson.techsolutio.techproduct.api.controller;

import com.laerson.techsolutio.techproduct.api.dto.UserAuthentication;
import com.laerson.techsolutio.techproduct.api.dto.response.TokenResponseBody;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;

    public AuthenticationController(IAuthenticationService iAuthenticationService) {
        this.iAuthenticationService = iAuthenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseBody> authenticateUser(@RequestBody UserAuthentication login) {
        return ResponseEntity.ok(iAuthenticationService.authenticateUser(login));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        iAuthenticationService.logout(request);
        return ResponseEntity.noContent().build();
    }

}
