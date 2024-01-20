package com.management.taskifypro.service.implementation.securityservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.management.taskifypro.exception.GeneralException;
import com.management.taskifypro.model.dto.UserDto;
import com.management.taskifypro.model.entity.User;
import com.management.taskifypro.model.request.LoginRequest;
import com.management.taskifypro.model.request.SignupRequest;
import com.management.taskifypro.model.response.JwtResponse;
import com.management.taskifypro.util.constants.GeneralConstants;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            JwtService jwtService,
            UserService userService,
            AuthenticationManager authenticationManager
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

     public ResponseEntity<?> AuthenticateUser(@RequestBody LoginRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new GeneralException(HttpStatus.UNAUTHORIZED.value(), "Las credenciales son incorrectas"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(GeneralConstants.TOKEN_PREFIX, token, authRequest.getEmail()));
    }

    public ResponseEntity<?> RegisterNewUser(@RequestBody SignupRequest signupRequestDto) {
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getConfirmPassword())) {
            return new ResponseEntity<>(new GeneralException(HttpStatus.BAD_REQUEST.value(), "Las credenciales no coinciden"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(signupRequestDto.getEmail()).isPresent()) {
            return new ResponseEntity<>(new GeneralException(HttpStatus.BAD_REQUEST.value(), "Ya existe un usuario con el email especificado"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(signupRequestDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail(), user.getEmail()));
    }
}
