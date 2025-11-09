package com.project.Controller;

import com.project.Models.User;

public interface UserDAO {
    User create(User user);
    User findById(Long id);
    void update(Long id, User user);
    void delete(Long id);
}
