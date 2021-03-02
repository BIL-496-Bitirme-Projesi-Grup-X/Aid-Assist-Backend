package com.aidassist.controller;

import com.aidassist.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    // TODO this will be deleted after db configurations
    Map<String, User> userMap = new HashMap<String, User>();

    @CrossOrigin
    @PostMapping(value = "/signUp", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody User user) {

        // Check user exists
        if (userMap.containsKey(user.getNationalIdentityNo())) {
            return new ResponseEntity<String>("User already registered!",HttpStatus.CONFLICT);
        }
        userMap.put(user.getNationalIdentityNo(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/signIn", produces = "application/json")
    public ResponseEntity<String> signIn(@RequestParam("id") String id, @RequestParam("password") String password) {
        if (userMap.containsKey(id) && userMap.get(id).getPassword().equals(password)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @CrossOrigin
    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<String> update(@RequestBody User user) {
        if (!userMap.containsKey(user.getNationalIdentityNo())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userMap.put(user.getNationalIdentityNo(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        if (!userMap.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userMap.get(id), HttpStatus.OK);
    }

}
