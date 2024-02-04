package com.rangle.chatbase.controller;

import com.rangle.chatbase.entity.User;
import com.rangle.chatbase.repository.UserRepository;
import com.rangle.chatbase.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/friends")
public class FriendshipController {
    @Autowired
    private FriendShipService friendShipService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestParam String username, @RequestParam String friendUsername){
        User user = userRepository.findByUsername(username);
        User friend = userRepository.findByUsername(friendUsername);

        if(user != null && friend != null){
            friendShipService.addFriend(user, friend);
            return ResponseEntity.ok("Friend added");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getFriends(@RequestParam String username){
        User user = userRepository.findByUsername(username);

        if (user != null){
            List<User> friends = friendShipService.getFriends(user);
            return ResponseEntity.ok(friends);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
