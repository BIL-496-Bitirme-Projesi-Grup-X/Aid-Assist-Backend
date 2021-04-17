package com.aidassist.controller;

import com.aidassist.bl.UserService;
import com.aidassist.jwt.JwtUtil;
import com.aidassist.model.User;
import com.aidassist.model.UserDTO;
import com.aidassist.model.UserResponse;
import com.aidassist.model.UserResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/signUp", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(@RequestBody User user) {
        UserResponse userResponse = new UserResponse();
        // Check user exists
        Optional<User> userOptional = userService.findUserById(user.getNationalIdentityNo());
        if (userOptional.isPresent()) {
            userResponse.setError(new UserResponseError("ID_EXISTS"));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            userResponse.setError(new UserResponseError(""));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        final UserDetails userDetails = userService.loadUserByUsername(user.getNationalIdentityNo());
        final String jwt = jwtUtil.generateToken(userDetails);
        userResponse.setIdToken(jwt);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/signIn", produces = "application/json")
    public ResponseEntity<UserResponse> signIn(@RequestBody User user) throws Exception {
        UserResponse userResponse = new UserResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getNationalIdentityNo(), user.getPassword()));
        } catch (InternalAuthenticationServiceException e) {
            userResponse.setError(new UserResponseError("ID_NOT_FOUND"));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            userResponse.setError(new UserResponseError("INVALID_PASSWORD"));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        final UserDetails userDetails = userService.loadUserByUsername(user.getNationalIdentityNo());
        final String jwt = jwtUtil.generateToken(userDetails);
        userResponse.setIdToken(jwt);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<String> update(@RequestBody User user) {
        Optional<User> optionalUser = userService.findUserById(user.getNationalIdentityNo());
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User updatedUser = optionalUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());

        userService.save(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        Optional<User> userOptional = userService.findUserById(id);

        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(userOptional.get()), HttpStatus.OK);
    }

}
