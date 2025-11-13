package com.project.View.DeleteUser;

import com.project.DAO.UserDAO;

import static com.project.View.Validate.inputId;

public class DeleteUserView {

    public static void deleteUser() {
        long id = inputId();

        UserDAO userDAOController = new UserDAO();
        userDAOController.delete(id);
    }
}
