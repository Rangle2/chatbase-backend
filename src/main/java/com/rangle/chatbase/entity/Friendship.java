package com.rangle.chatbase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

}
