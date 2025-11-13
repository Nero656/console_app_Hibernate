package com.project.View;

import java.util.Scanner;

import static ru.exxo.jutil.Printer.print;
import static ru.exxo.jutil.Printer.println;

public class Validate {
    public static Scanner scanner = new Scanner(System.in);

    public static int inputId() {
        boolean isValid = false;
        while (!isValid) {
            print("Input id: ");
            String input = scanner.nextLine().trim();

            try {
                int id = Integer.parseInt(input);

                if (id < 0) {
                    println("Error: id must be a positive integer");
                    continue;
                }

                isValid = true;
                return id;
            } catch (NumberFormatException e) {
                println("Error: invalid id!");
            }
        }

        throw new IllegalStateException("Unexpected error in inputName");
    }

    public static String inputName() {
        boolean isValid = false;
        while (!isValid) {
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

            isValid = true;
            return name;
        }

        throw new IllegalStateException("Unexpected error in inputName");
    }

    public static String inputEmail() {
        boolean isValid = false;
        while (!isValid) {
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

            isValid = true;
            return email;
        }

        throw new IllegalStateException("Unexpected error in inputName");
    }

    public static String inputPassword() {
        boolean isValid = false;

        while (!isValid) {
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

            isValid = true;
            return password;
        }

        throw new IllegalStateException("Unexpected error in inputName");
    }

    public static int inputAge() {
        boolean isValid = false;

        while (!isValid) {
            print("Input age: ");
            String input = scanner.nextLine().trim();

            try {
                int age = Integer.parseInt(input);

                if (age < 0 || age > 150) {
                    println("Error: age must be between 0 and 150!");
                    continue;
                }

                isValid = true;
                return age;
            } catch (NumberFormatException e) {
                println("Error: invalid age!");
            }
        }

        throw new IllegalStateException("Unexpected error in inputName");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
