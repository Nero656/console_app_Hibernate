package com.project.DAO;

import com.project.Models.User;

public interface UserDAOInterface {
    User create(User user);
    User findById(Long id);
    void update(Long id, User user);
    void delete(Long id);
}
