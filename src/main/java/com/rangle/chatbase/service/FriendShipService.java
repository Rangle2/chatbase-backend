package com.rangle.chatbase.service;

import com.rangle.chatbase.entity.Friendship;
import com.rangle.chatbase.entity.User;
import com.rangle.chatbase.repository.FriendShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendShipService {

    @Autowired
    private FriendShipRepository friendshipRepository;

    public void addFriend(User user, User friend){
        Friendship friendship = new Friendship();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendshipRepository.save(friendship);
    }

    public List<User> getFriends(User user){
        List<Friendship> friendships = friendshipRepository.findByUserOrFriend(user, user);
        List<User> friends = new ArrayList<>();
        for(Friendship friendship : friendships){
            if (!friendship.getUser().equals(user)){
                friends.add(friendship.getUser());
            }else {
                friends.add(friendship.getFriend());
            }
        }
        return friends;
    }
}
