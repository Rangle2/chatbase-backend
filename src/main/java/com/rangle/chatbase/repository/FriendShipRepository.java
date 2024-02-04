package com.rangle.chatbase.repository;

import com.rangle.chatbase.entity.Friendship;
import com.rangle.chatbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendShipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByUserOrFriend(User user, User friend);
}
