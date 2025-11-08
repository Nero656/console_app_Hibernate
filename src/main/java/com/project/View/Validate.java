package com.project.View;

import java.util.Scanner;

import static ru.exxo.jutil.Printer.print;
import static ru.exxo.jutil.Printer.println;

public class Validate {
    static Scanner scanner = new Scanner(System.in);

    public static int inputId() {
        while (true) {
            print("Input id: ");
            String input = scanner.nextLine().trim();

            try {
                int id = Integer.parseInt(input);

                if (id < 0) {
                    println("Error: id must be a positive integer");
                    continue;
                }

                return id;
            } catch (NumberFormatException e) {
                println("Error: invalid id!");
            }
        }
    }

    public static String inputName() {
        while (true) {
            print("Input name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                println("Error: name is empty!");
                continue;
            }

            if (name.length() < 2) {
                println("Error: name is too short (more 2 characters!)!");
                continue;
            }

            return name;
        }
    }

    public static String inputEmail() {
        while (true) {
            print("Input email: ");
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                println("Error: Email cannot be empty!");
                continue;
            }

            if (!isValidEmail(email)) {
                println("Error: invalid email!");
                continue;
            }

            return email;
        }
    }

    public static String inputPassword() {
        while (true) {
            print("Input password: ");
            String password = scanner.nextLine().trim();

            if (password.isEmpty()) {
                println("Error: Password cannot be empty!");
                continue;
            }

            if (password.length() < 6) {
                println("Error: Password may be less than 6 characters!");
                continue;
            }


            print("Confirm password: ");
            String confirmPassword = scanner.nextLine().trim();

            if (!password.equals(confirmPassword)) {
                println("Error: passwords don't match!");
                continue;
            }

            return password;
        }
    }

    public static int inputAge() {
        while (true) {
            print("Input age: ");
            String input = scanner.nextLine().trim();

            try {
                int age = Integer.parseInt(input);

                if (age < 0 || age > 150) {
                    println("Error: age must be between 0 and 150!");
                    continue;
                }

                return age;
            } catch (NumberFormatException e) {
                println("Error: invalid age!");
            }
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
