package com.project.DAO;

import static ru.exxo.jutil.Printer.*;

import com.project.Models.User;
import com.project.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO implements UserDAOInterface {

    @Override
    public User create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, User newData) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User existingUser = session.find(User.class, id);
            existingUser.setName(newData.getName());
            existingUser.setEmail(newData.getEmail());
            existingUser.setPassword(newData.getPassword());
            existingUser.setAge(newData.getAge());

            println("User with id:" + id + " has been updated");

            session.merge(existingUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user == null) {
                println("User with id:" + id + " not found");
                return;
            } else {
                println("User with id:" + id + " has been deleted");
            }
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
