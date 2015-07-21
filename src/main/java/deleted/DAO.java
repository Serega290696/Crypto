package deleted;

import data_base.entities.Note;
import data_base.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class DAO {

    final static EntityManager manager = Persistence.createEntityManagerFactory("DBWORKER").createEntityManager();

    public void addUser(User user) {
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }
//    public void addNote(Note2 note) {
//        new DAO.NoteDAO().addNote(note);
//    }

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



    public void addNote(User user) {
        Note note = new Note();
        manager.getTransaction().begin();
        manager.merge(note);
        manager.getTransaction().commit();
    }



//    public static class NoteDAO {
//
//        public void addNote(Note2 note) {
//            manager.getTransaction().begin();
//            manager.merge(note);
//            manager.getTransaction().commit();
//        }
//
//        public void getNote(Note2 note) {
//            manager.find(note.getClass(), note.getId());
//        }
//        public Note2 getNote(long id) {
//            return manager.find(Note2.class, id);
//        }
//
//        public void removeNote(Note2 note) {
//            manager.getTransaction().begin();
//            manager.remove(note);
//            manager.getTransaction().commit();
//        }
//
//        public void updateNote(Note2 note) {
//            manager.getTransaction().begin();
//            manager.merge(note);
//            manager.getTransaction().commit();
//        }
//
//        public ArrayList<User.Note> getAll(){
//            TypedQuery<User.Note> namedQuery = manager.createNamedQuery("Note.getAll", User.Note.class);
//            return (ArrayList<User.Note>) namedQuery.getResultList();
//        }
//    }
}
