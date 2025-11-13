package com.project.View;

import static ru.exxo.jutil.Printer.*;

import com.project.View.CreateUser.CreateUserView;
import com.project.View.DeleteUser.DeleteUserView;
import com.project.View.GetUser.ReadUserView;
import com.project.View.UpdateUser.UpdateUserView;
import ru.exxo.jutil.Printer;

import java.util.List;
import java.util.Scanner;

public class Home {
    Scanner input = new Scanner(System.in);

    public void homeUI() {
        boolean runing = true;
        while (runing) {
            MainMenu();
            int choice = readInt("\nChose action: ");
            runing = handleMainChoice(choice);
        }
    }

    private void MainMenu(){
        List<String> menu = List.of(
                "\nSelect an action",
                "1: Create user",
                "2: Find user by ID",
                "3: Update user by id",
                "4: Remove user by id",
                "5: Exit"
        );

        menu.forEach(Printer::println);
    }

    private boolean handleMainChoice(int choice) {
        switch (choice) {
            case 1 -> CreateUserView.createUser();

            case 2 -> ReadUserView.readUser();

            case 3 -> UpdateUserView.readUser();

            case 4 -> DeleteUserView.deleteUser();

            case 5 -> {
                exit();
                return false;
            }

            default -> println("Invalid choice, try again!");
        }
        return true;
    }

    private void exit() {
        println("Goodbye!");
    }

    private int readInt(String prompt) {
        print(prompt);
        while (!input.hasNextInt()) {
            input.next();
            println("Invalid input!");
        }
        int inputValue = input.nextInt();
        input.nextLine();
        return inputValue;
    }
}
