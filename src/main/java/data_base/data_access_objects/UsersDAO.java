package data_base.data_access_objects;

import data_base.entities.Note;
import data_base.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class UsersDAO {

    EntityManager manager = Persistence.createEntityManagerFactory("DBWORKER").createEntityManager();

    public void addUser(User user) {
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public void getUser(User user) {
        manager.find(user.getClass(), user.getId());
    }
    public User getUser(long id) {
        return manager.find(User.class, id);
    }

    public void removeUser(User user) {
        manager.getTransaction().begin();
        manager.remove(user);
        manager.getTransaction().commit();
    }

    public void updateUser(User user) {
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public List<User> getAll(){
        TypedQuery<User> namedQuery = manager.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }



}
