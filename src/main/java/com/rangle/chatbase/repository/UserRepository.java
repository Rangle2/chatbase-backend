package com.rangle.chatbase.repository;

import com.rangle.chatbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirebaseUid(String firebaseUid);
}
