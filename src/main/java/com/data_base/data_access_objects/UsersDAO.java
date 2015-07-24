package com.data_base.data_access_objects;

import com.data_base.HibernateUtil;
import com.data_base.entities.User;
import com.exceptions.DataBaseException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Serega on 19.07.2015.
 */
public class UsersDAO implements DAOClass<User> {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean add(User newUser) {
        boolean userAlreadyExist =
                getAll()
                        .stream()
                        .filter((u) ->
                                (u.getLogin().equals(newUser.getLogin()))
                                        || (u.getMail().equals(newUser.getMail())))
                        .count() > 0 ? true : false;

        if (userAlreadyExist) {
//            throw new DataBaseException("User with such email or login already exist!\n" +
//                    "Please, try to enter another data.");
            System.err.println("User with such email or login already exist!\n" +
                    "Please, try to enter another data.");
            return false;
        } else {
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
            return true;
        }
    }

    public User get(User user) {
        User returnedUser = null;
        for (User u : getAll()) {
            if (u.getLogin().equals(user.getLogin()))
                if (u.getPassword().equals(user.getPassword())) {
                    returnedUser = u;
                    break;
                } else {
                    returnedUser = null;
                    break;
                }
        }
        return returnedUser;
    }

    public User get(long id) {
        session.beginTransaction();
        Query q = session.createQuery("from User as u where u.id=" + id);
        User user = (User) q.uniqueResult();
        return user;
    }


    public void remove(User user) {
        session.beginTransaction();
        session.delete(user);
        session.flush();
        session.getTransaction().commit();
    }

    public void update(User user) {
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
    }

    public List<User> getAll() {
        List<User> result = session.createQuery("from User order by id").list();
        return result;
    }


}
