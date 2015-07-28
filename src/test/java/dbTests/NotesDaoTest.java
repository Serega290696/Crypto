package dbTests;

import com.data_base.HibernateUtil;
import com.data_base.data_access_objects.NotesDAO;
import com.data_base.entities.Note;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Serega on 28.07.2015.
 */
public class NotesDaoTest extends Assert{


    private final NotesDAO notesDao = new NotesDAO();
    private final Note note = new Note("Test note", 1);

    @Test
    public void testAddNote() {
        int tmp = notesDao.getAll().size();
        if (notesDao.add(note))
            assertEquals("Note was not added!", tmp+1, notesDao.getAll().size());
    }

    @Test
    public void testAddNotes() {
        for (int i = 0; i < 10; i++) {
            int tmp = notesDao.getAll().size();
            if (notesDao.add(new Note()))
                tmp++;
            assertEquals("Note was not added!", tmp, notesDao.getAll().size());
        }
    }

    @Test
    public void testGetNote() {
        System.out.println(
                notesDao.get(new Note("Note", 1))
        );
        assertNotNull("Note didn't found! Test error!", notesDao.get(new Note("Note", 1)));
    }

    @Test
    public void testGetNoteForId() {
        notesDao.get(note.getId());
        assertNotNull("Note didn't found! Test error!", notesDao.get(note));
    }

    @Test
    public void testRemoveNote() {
        int tmp = notesDao.getAll().size();
        notesDao.remove(note);
        tmp--;
        assertEquals("Note was not deleted!", tmp, notesDao.getAll().size());
    }

    @Test
    public void testUpdateNote() {
        int tmp = notesDao.getAll().size();
        notesDao.update(note);
        assertEquals("Extra note was added!", tmp, notesDao.getAll().size());
    }

    @Test
    public void testGetAllNotes() {
        int amount = notesDao.getAll().size();
        System.out.println("Number of notes: " + amount + ".");
        assertNotEquals("Don't found any users!", amount, 0);
    }
    @Test
    public void testGetAllNotesAndShow() {
        notesDao.getAll()
                .stream()
                .forEach(System.out::println);
    }
    @After
    public void shutdown() {
        System.out.println("\t\tshutdown. . .");
        HibernateUtil.shutdown();
    }

}
