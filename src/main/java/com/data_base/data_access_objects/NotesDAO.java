package com.data_base.data_access_objects;

import com.data_base.HibernateUtil;
import com.data_base.entities.Note;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class NotesDAO implements DAOInterface<Note> {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean add(Note newNote) {
        boolean noteAlreadyExist =
                getAll()
                        .stream()
                        .filter((n) -> (n.getIdUser() == (newNote.getIdUser())))
                        .filter((n) -> (n.getTitle().equals(newNote.getTitle())))
                        .count() > 0 ? true : false;

        if (noteAlreadyExist) {
//            throw new DataBaseException("User with such email or login already exist!\n" +
//                    "Please, try to enter another data.");
            System.err.println("Note with such title already exist!\n" +
                    "Please, try to enter another title.");
            return false;
        } else {
            session.beginTransaction();
            session.save(newNote);
            session.getTransaction().commit();
            return true;
        }
    }

    public Note get(Note note) {
        for (Note n : getAll()) {
            if (n.getIdUser() == (note.getIdUser()) && n.getTitle().equals(note.getTitle())) {
                return n;
            }
            else System.out.println(n.getIdUser() +"   "+ (note.getIdUser()) +"   "+ n.getTitle()+"   "+(note.getTitle()));
        }
        return null;
    }

    @Override
    public Note get(long id) {
        Note returnedNote = null;
        for (Note n : getAll()) {
            if (n.getIdUser() == id)
                return returnedNote;
        }
        return null;
    }


    public void remove(Note note) {
        session.beginTransaction();
        session.delete(note);
        session.flush();
        session.getTransaction().commit();
    }

    public void update(Note note) {
        session.beginTransaction();
        session.merge(note);
        session.getTransaction().commit();
    }

    public List<Note> getAll() {
        List<Note> result = session.createQuery("from Note order by id").list();
        return result;
    }
}
