package com.example.devops_demo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.devops_demo.tweet.Tweet;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Tweet> tweets;
}
