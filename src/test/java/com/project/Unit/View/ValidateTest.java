package com.project.Unit.View;

import com.project.View.Validate;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import ru.exxo.jutil.Printer;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidateTest {
    private MockedStatic<Printer> printerMock;

    @BeforeEach
    void setUp() {
        printerMock = mockStatic(Printer.class);
    }

    @AfterEach
    void tearDown() {
        printerMock.close();
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        Validate.scanner = new java.util.Scanner(System.in);
    }

    @Test
    @DisplayName("Test: inputId")
    void testInputId_valid() {
        setInput("42\n");
        int id = Validate.inputId();
        assertEquals(42, id);
        printerMock.verify(() -> Printer.print("Input id: "), times(1));
    }

    @Test
    void testInputId_invalidThenValid() {
        setInput("abc\n10\n");
        int id = Validate.inputId();
        assertEquals(10, id);
        printerMock.verify(() -> Printer.println("Error: invalid id!"), times(1));
    }

    @Test
    @DisplayName("Test: inputName")
    void testInputName_valid() {
        setInput("Alice\n");
        String name = Validate.inputName();
        assertEquals("Alice", name);
    }

    @Test
    void testInputName_emptyThenShortThenValid() {
        setInput("\na\nBob\n");
        String name = Validate.inputName();
        assertEquals("Bob", name);
        printerMock.verify(() -> Printer.println("Error: name is empty!"), times(1));
        printerMock.verify(() -> Printer.println("Error: name is too short (more 2 characters!)!"), times(1));
    }

    @Test
    @DisplayName("Test: inputEmail")
    void testInputEmail_valid() {
        setInput("test@mail.com\n");
        String email = Validate.inputEmail();
        assertEquals("test@mail.com", email);
    }

    @Test
    void testInputEmail_invalidThenValid() {
        setInput("invalid\nvalid@mail.com\n");
        String email = Validate.inputEmail();
        assertEquals("valid@mail.com", email);
        printerMock.verify(() -> Printer.println("Error: invalid email!"), times(1));
    }

    @Test
    @DisplayName("Test: inputPassword")
    void testInputPassword_valid() {
        setInput("mypassword\nmypassword\n");
        String password = Validate.inputPassword();
        assertEquals("mypassword", password);
    }

    @Test
    void testInputPassword_emptyThenShortThenMismatchThenValid() {
        setInput("\n123\n123456\nwrong\nsecret123\nsecret123\n");
        String password = Validate.inputPassword();
        assertEquals("secret123", password);

        printerMock.verify(() -> Printer.println("Error: Password cannot be empty!"), times(1));
        printerMock.verify(() -> Printer.println("Error: Password may be less than 6 characters!"),
                times(1));
        printerMock.verify(() -> Printer.println("Error: passwords don't match!"), times(1));
    }

    @Test
    @DisplayName("Test: inputAge")
    void testInputAge_valid() {
        setInput("25\n");
        int age = Validate.inputAge();
        assertEquals(25, age);
    }

    @Test
    void testInputAge_invalidThenValid() {
        setInput("abc\n200\n30\n");
        int age = Validate.inputAge();
        assertEquals(30, age);

        printerMock.verify(() -> Printer.println("Error: invalid age!"), times(1));
        printerMock.verify(() -> Printer.println("Error: age must be between 0 and 150!"), times(1));
    }

    // === Тест isValidEmail() ===
    @Test
    void testIsValidEmail() {
        assertTrue(Validate.isValidEmail("user@example.com"));
        assertFalse(Validate.isValidEmail("bademail"));
        assertFalse(Validate.isValidEmail("user@com"));
        assertTrue(Validate.isValidEmail("x.y+z@mail.co"));
    }
}
