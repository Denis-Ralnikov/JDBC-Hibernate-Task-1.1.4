package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.session()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),lastName VARCHAR(50), age TINYINT)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.session()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.session()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.session()) {
            session.beginTransaction();
            session.delete(session.get(User.class,id));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.session()){
            session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.session()){
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
