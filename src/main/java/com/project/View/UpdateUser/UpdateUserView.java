package com.project.View.UpdateUser;

import com.project.Controller.UserDAOController;
import com.project.Models.User;
import static ru.exxo.jutil.Printer.*;
import static com.project.View.Validate.*;

public class UpdateUserView {
    public static void readUser() {
        long id = inputId();

        String newName = inputName();
        String newEmail = inputEmail();
        String newPassword = inputPassword();
        String hashedPassword = User.HashPassword(newPassword);
        int newAge = inputAge();

        UserDAOController userDAOController = new UserDAOController();

        if (userDAOController.findById(id) != null) {
            println("User with id:"+userDAOController.findById(id).getId()+" is exist");
        }else {
            println("User with id:"+id+" is not exist");
        }

        User user = new User(
                newName,
                newEmail,
                hashedPassword,
                newAge,
                userDAOController.findById(id).getCreateAt());
        userDAOController.update(id, user);
    }
}
