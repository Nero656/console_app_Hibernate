package com.project.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
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

    public User() {}
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

    // Проверка пароля
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public LocalDateTime getCreateAt() {return createAt;}
    public void setCreateAt( java.time.LocalDateTime createAt) {this.createAt = createAt;}
}
