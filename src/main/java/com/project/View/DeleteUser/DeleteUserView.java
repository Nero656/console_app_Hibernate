package com.project.View.DeleteUser;

import com.project.Controller.UserDAOController;

import static com.project.View.Validate.inputId;
public class DeleteUserView {

    public static void deleteUser() {
        long id = inputId();

        UserDAOController userDAOController = new UserDAOController();
        userDAOController.delete(id);
    }
}
