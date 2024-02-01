package com.rangle.chatbase.controller;
import com.rangle.chatbase.entity.User;
import com.rangle.chatbase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/user/create")
    public ResponseEntity<User>createUser(@RequestBody String firebaseIdToken){
        User createdUser = userService.createUser(firebaseIdToken);

        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
