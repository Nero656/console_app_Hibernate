package com.project.Unit.View;

import com.project.View.CreateUser.CreateUserView;
import com.project.View.DeleteUser.DeleteUserView;
import com.project.View.GetUser.ReadUserView;
import com.project.View.UpdateUser.UpdateUserView;
import com.project.View.Home;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import org.junit.jupiter.api.*;
import ru.exxo.jutil.Printer;

import static org.mockito.Mockito.*;


public class HomeTest {
    private Home home;

    @BeforeEach
    public void setUp() {
        home = new Home();
    }

    @Test
    @DisplayName("1: Create user")
    void testHandleMainChoice_CreateUser() {
        try (MockedStatic<CreateUserView> mock = mockStatic(CreateUserView.class)) {
            mock.when(CreateUserView::createUser).thenAnswer(invoke -> null);

            boolean result = invokeHandleMainChoice(home, 1);

            mock.verify(CreateUserView::createUser, times(1));
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("2: Find by user by id")
    void testHandleMainChoice_ReadUser() {
        try (MockedStatic<ReadUserView> mock = mockStatic(ReadUserView.class)) {
            mock.when(ReadUserView::readUser).thenAnswer(inv -> null);

            boolean result = invokeHandleMainChoice(home, 2);

            mock.verify(ReadUserView::readUser, times(1));
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("3: Update user")
    void testHandleMainChoice_UpdateUser() {
        try (MockedStatic<UpdateUserView> mock = mockStatic(UpdateUserView.class)) {
            mock.when(UpdateUserView::readUser).thenAnswer(inv -> null);

            boolean result = invokeHandleMainChoice(home, 3);

            mock.verify(UpdateUserView::readUser, times(1));
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("4: Delete user")
    void testHandleMainChoice_DeleteUser() {
        try (MockedStatic<DeleteUserView> mock = mockStatic(DeleteUserView.class)) {
            mock.when(DeleteUserView::deleteUser).thenAnswer(inv -> null);

            boolean result = invokeHandleMainChoice(home, 4);

            mock.verify(DeleteUserView::deleteUser, times(1));
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("5: Turn off app")
    void testHandleMainChoice_Exit() {
        try (MockedStatic<Printer> printerMock = mockStatic(Printer.class)) {
            boolean result = invokeHandleMainChoice(home, 5);

            printerMock.verify(() -> Printer.println("Goodbye!"), times(1));
            Assertions.assertFalse(result);
        }
    }

    @Test
    @DisplayName("6: Invalid choice")
    void testHandleMainChoice_InvalidOption() {
        try (MockedStatic<Printer> printerMock = mockStatic(Printer.class)) {
            boolean result = invokeHandleMainChoice(home, 99);

            printerMock.verify(() -> Printer.println("Invalid choice, try again!"), times(1));
            Assertions.assertTrue(result);
        }
    }

    private boolean invokeHandleMainChoice(Home home, int choice) {
        try {
            var method = Home.class.getDeclaredMethod("handleMainChoice", int.class);
            method.setAccessible(true);
            return (boolean) method.invoke(home, choice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
