package com.project.View.GetUser;

import com.project.Controller.UserDAOController;

import static com.project.View.Validate.inputId;
import static ru.exxo.jutil.Printer.*;

public class ReadUserView {
    public static void readUser() {
        long id = inputId();

        UserDAOController userDAOController = new UserDAOController();

        if (userDAOController.findById(id) != null) {
            println("User with id:"+userDAOController.findById(id).getId()+" is exist");
        }else {
            println("User with id:"+id+" is not exist");
        }
    }
}
