package data_base.data_access_objects;

import data_base.entities.Note;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class NotesDAO {

    EntityManager manager = Persistence.createEntityManagerFactory("DBWORKER").createEntityManager();

    public void addNote(Note note) {
        manager.getTransaction().begin();
        manager.merge(note);
        manager.getTransaction().commit();
    }

    public void getNote(Note note) {
        manager.find(note.getClass(), note.getId());
    }
    public Note getNote(long id) {
        return manager.find(Note.class, id);
    }

    public void removeNote(Note note) {
        manager.getTransaction().begin();
        manager.remove(note);
        manager.getTransaction().commit();
    }

    public void updateNote(Note note) {
        manager.getTransaction().begin();
        manager.merge(note);
        manager.getTransaction().commit();
    }

    public List<Note> getAll(){
        TypedQuery<Note> namedQuery = manager.createNamedQuery("Note.getAll", Note.class);
        return namedQuery.getResultList();
    }
}
