package com.project.integration;

import com.project.DAO.UserDAO;
import com.project.Models.User;
import com.project.Util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserDAOTest {

    @Container
    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17.1-alpine")
                    .withDatabaseName("users_db")
                    .withUsername("Nero")
                    .withPassword("0907");

    private static SessionFactory sessionFactory;
    private UserDAO userDAO;

    @BeforeAll
    static void beforeAll() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.setProperty("hibernate.show_sql", "true");

        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
        HibernateUtil.overrideSessionFactory(sessionFactory);
    }

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @AfterEach
    void tearDown() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        }
    }

    private User createUser(String name, String email, String password, int age) {
        return new User(name, email, User.HashPassword(password), age, java.time.LocalDateTime.now());
    }

    @Test
    @DisplayName("1: Create user")
    void shouldCreateUserSuccessfully() {
        User user = createUser(
                "John Doe",
                "john@example.com",
                "password123",
                30
        );
        User createdUser = userDAO.create(user);

        assertAll(
                () -> assertNotNull(createdUser),
                () -> assertTrue(createdUser.getId() > 0),
                () -> assertEquals("John Doe", createdUser.getName()),
                () -> assertEquals("john@example.com", createdUser.getEmail()),
                () -> assertTrue(User.checkPassword("password123", createdUser.getPassword())),
                () -> assertEquals(30, createdUser.getAge()),
                () -> assertNotNull(createdUser.getCreateAt())
        );
    }


    @Test
    @DisplayName("2: Find by ID")
    void shouldFindUserById() {
        User user = userDAO.create(createUser("Jane", "jane@example.com", "pass1", 25));
        User found = userDAO.findById(user.getId());

        assertNotNull(found);
        assertEquals("Jane", found.getName());
    }

    @Test
    @DisplayName("3: Update user data")
    void shouldUpdateUserSuccessfully() {
        User saved = userDAO.create(createUser("Old", "old@example.com", "oldpass", 40));
        User newData = createUser("New", "new@example.com", "newpass", 35);

        userDAO.update(saved.getId(), newData);
        User updated = userDAO.findById(saved.getId());

        assertAll(
                () -> assertEquals("New", updated.getName()),
                () -> assertEquals("new@example.com", updated.getEmail()),
                () -> assertTrue(User.checkPassword("newpass", updated.getPassword())),
                () -> assertEquals(35, updated.getAge())
        );
    }

    @Test
    @DisplayName("4: Delete user")
    void shouldDeleteUserSuccessfully() {
        User saved = userDAO.create(createUser("ToDelete", "delete@example.com", "pass", 50));
        assertNotNull(userDAO.findById(saved.getId()));

        userDAO.delete(saved.getId());
        assertNull(userDAO.findById(saved.getId()));
    }

    @Test
    @DisplayName("5: Attempt to create a user with a duplicate email")
    void shouldRollbackOnDuplicateEmail() {
        User user1 = createUser("Name_A", "same@example.com", "pass_1", 20);
        User user2 = createUser("Name_B", "same@example.com", "pass_2", 22);

        userDAO.create(user1);
        assertThrows(Exception.class, () -> userDAO.create(user2));

        assertNotNull(userDAO.findById(user1.getId()));
    }

    @AfterAll
    static void afterAll() {
        sessionFactory.close();
    }
}