package com.rangle.chatbase.service;

import com.rangle.chatbase.entity.User;
import com.rangle.chatbase.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class UserService {
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(@RequestParam String firebaseIdToken){
        try{

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseIdToken);
            String uid = decodedToken.getUid();
            String displayName = decodedToken.getName();
            String email = decodedToken.getEmail();
            String avatar = decodedToken.getPicture();

            User savedUser = userRepository.findByFirebaseUid(uid);
            if (savedUser == null){
                User user = new User();
                user.setFirebaseUid(uid);
                user.setUsername(displayName);
                user.setEmail(email);
                user.setAvatar(avatar);

                // Save user in Database
                return userRepository.save(user);
            }else {
                logger.warn("User is already registered");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
