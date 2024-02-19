package com.example.userservice.model;

import com.example.userservice.security.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
