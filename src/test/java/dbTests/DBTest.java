package dbTests;

import com.data_base.HibernateUtil;
import com.data_base.data_access_objects.NotesDAO;
import com.data_base.data_access_objects.UsersDAO;
import com.data_base.entities.Note;
import com.data_base.entities.User;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Serega on 19.07.2015.
 */
public class DBTest extends Assert {


    private final NotesDAO notesDao = new NotesDAO();
    private final UsersDAO usersDao = new UsersDAO();

    Session session = HibernateUtil.getSessionFactory().openSession();
    private final User user;
    private final User user2 = new User();
    private final Note note;

    {
        user = new User();
        user.setPassword("123456");
        user.setName("user");
        user.setLastVisitDate(new Date());
        user.setMaxNoteLength(100);
        user.setMaxNotes(100);
        user.setRegistrationDate(new Date());

        user2.setLogin("Login4");
        user2.setMail("Mail4");
        user2.setPassword("123456");
        user2.setName("user");
        user2.setLastVisitDate(new Date());
        user2.setMaxNoteLength(100);
        user2.setMaxNotes(100);
        user2.setRegistrationDate(new Date());
        note = new Note();
    }


    @Test(timeout = 4000)
    public void testAddUser() {
        System.out.println("begin");
        while(true) {
            if(usersDao.add(user2))
                break;
        }
    }
//    @Ignore
//    @Test
//    public void testAddUsers() {
//        for (int i = 0; i < 100; i++) {
//            usersDao.add(new User());
//        }
//    }
    @Test
    public void testRemoveUser() {
        usersDao.remove(user);
    }
    @Test
    public void testUpdateUser() {
        usersDao.update(user);
    }
    @Test
    public void testGetAllUsers() {
        System.out.println(usersDao.getAll());
    }
    @Test
    public void testGetUser() {
        System.out.println(usersDao.get(new User("Serega", "qwerty123")));
    }
    @Test
    public void testGetAllNotes() {
        System.out.println(notesDao.getAll());
    }

    //    @Test
//    public void testGetUser() {
//        User userTmp = new UsersDAO().get(user);
//        System.out.println(userTmp);
//        Assert.assertNotNull(userTmp);
//    }
//
//    @Test
//    public void testAddUser() {
//        usersDao.add(user);
//    }
////    @Test
////    public void testGetUser() {
////        usersDao.get(user);
////    }
//    @Test
//    public void testGetUserForId() {
//        usersDao.get(user.getId());
//    }
//    @Test
//    public void testRemoveUser() {
//        usersDao.remove(user);
//    }
//    @Test
//    public void testUpdateUser() {
//        usersDao.update(user);
//    }
//    @Test
//    public void testGetAllUsers() {
//        usersDao.getAll()
//                .stream()
//                .forEach(System.out::println);
//    }
//
//
//
//    @Test
//    public void testAddNote() {
//        notesDao.add(note);
//    }
//    @Test
//    public void testGetNote() {
//        notesDao.get(note);
//    }
//    @Test
//    public void testGetNoteForId() {
//        notesDao.get(note.getId());
//    }
//    @Test
//    public void testRemoveNote() {
//        notesDao.remove(note);
//    }
//    @Test
//    public void testUpdateNote() {
//        notesDao.update(note);
//    }
//    @Test
//    public void testGetAllNotes() {
//        notesDao.getAll()
//                .stream()
//                .forEach(System.out::println);
//    }

}
