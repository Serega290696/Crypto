import data_base.data_access_objects.NotesDAO;
import data_base.data_access_objects.UsersDAO;
import data_base.entities.Note;
import data_base.entities.User;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Serega on 19.07.2015.
 */
public class DBTest extends TestCase{


    private final UsersDAO usersDao = new UsersDAO();
    private final NotesDAO notesDao = new NotesDAO();

    private final User user;
    private final Note note;

    {
        user = new User();
        note = new Note();
    }

    @Test
    public void testAddUser() {
        usersDao.addUser(user);
    }
    @Test
    public void testGetUser() {
        usersDao.getUser(user);
    }
    @Test
    public void testGetUserForId() {
        usersDao.getUser(user.getId());
    }
    @Test
    public void testRemoveUser() {
        usersDao.removeUser(user);
    }
    @Test
    public void testUpdateUser() {
        usersDao.updateUser(user);
    }
    @Test
    public void testGetAllUsers() {
        usersDao.getAll()
                .stream()
                .forEach(System.out::println);
    }



    @Test
    public void testAddNote() {
        notesDao.addNote(note);
    }
    @Test
    public void testGetNote() {
        notesDao.getNote(note);
    }
    @Test
    public void testGetNoteForId() {
        notesDao.getNote(note.getId());
    }
    @Test
    public void testRemoveNote() {
        notesDao.removeNote(note);
    }
    @Test
    public void testUpdateNote() {
        notesDao.updateNote(note);
    }
    @Test
    public void testGetAllNotes() {
        notesDao.getAll()
                .stream()
                .forEach(System.out::println);
    }

}
