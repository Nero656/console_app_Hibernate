package com.project.Models;

import jakarta.persistence.*;

import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "create_at", nullable = false)
    private  java.time.LocalDateTime createAt;

    public User(String name, String email, String password, int age, java.time.LocalDateTime createAt){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.createAt = java.time.LocalDateTime.now();
    }

    public static String HashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
