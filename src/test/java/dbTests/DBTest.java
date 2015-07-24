package dbTests;

import deleted.Users2DAO;
import deleted.User2;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Serega on 19.07.2015.
 */
public class DBTest extends TestCase{


//    private final NotesDAO notesDao = new NotesDAO();
//    private final UsersDAO usersDao = new UsersDAO();

//    private final User user;
//    private final User user2;
//    private final Note note;

//    {
//        user = new User();
//        user2 = new User("Serega", "qwerty123");
//        note = new Note();
//    }

    Users2DAO users2Dao= new Users2DAO();

    @Test
    public void testSaveRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        User2 user2 = new User2();
        user2.setLogin("BMW --- === ***");
        //Записали в БД
        User2 user2T = users2Dao.add(user2);

        //Вывели записанную в БД запись
        System.out.println(user2T);
    }
//    @Test
//    public void testGetUser() {
//        User userTmp = new UsersDAO().getUser(user2);
//        System.out.println(userTmp);
//        Assert.assertNotNull(userTmp);
//    }
//
//    @Test
//    public void testAddUser() {
//        usersDao.addUser(user);
//    }
////    @Test
////    public void testGetUser() {
////        usersDao.getUser(user);
////    }
//    @Test
//    public void testGetUserForId() {
//        usersDao.getUser(user.getId());
//    }
//    @Test
//    public void testRemoveUser() {
//        usersDao.removeUser(user);
//    }
//    @Test
//    public void testUpdateUser() {
//        usersDao.updateUser(user);
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
//        notesDao.addNote(note);
//    }
//    @Test
//    public void testGetNote() {
//        notesDao.getNote(note);
//    }
//    @Test
//    public void testGetNoteForId() {
//        notesDao.getNote(note.getId());
//    }
//    @Test
//    public void testRemoveNote() {
//        notesDao.removeNote(note);
//    }
//    @Test
//    public void testUpdateNote() {
//        notesDao.updateNote(note);
//    }
//    @Test
//    public void testGetAllNotes() {
//        notesDao.getAll()
//                .stream()
//                .forEach(System.out::println);
//    }

}
