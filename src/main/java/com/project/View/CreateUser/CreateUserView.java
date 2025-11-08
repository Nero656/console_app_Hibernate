package com.project.View.CreateUser;

import com.project.Controller.UserDAOController;
import com.project.Models.User;
import java.util.Scanner;

import static com.project.View.Validate.*;
import static ru.exxo.jutil.Printer.*;

public class CreateUserView {

    public static void createUser() {

        String name = inputName();
        String email = inputEmail();
        String password = inputPassword();
        String hashedPassword = User.HashPassword(password);
        int age = inputAge();

        UserDAOController userDAOController = new UserDAOController();

        printf("User created successfully with id: %s! \n",
                userDAOController.create(
                        new User(
                                name,
                                email,
                                hashedPassword,
                                age,
                                java.time.LocalDateTime.now())
                ).getId());
    }
}
