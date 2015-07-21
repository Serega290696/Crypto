package deleted;

import deleted.DAO;
import data_base.entities.User;
import org.junit.Test;

/**
 * Created by Serega on 21.07.2015.
 */
public class DAOTest {


    private final DAO dao = new DAO();

    private final User user;
//    private final User.Note note;

    {
        user = new User();
//        note = user.new Note();
    }

    @Test
    public void testAdd() {
        dao.addUser(user);
    }
    @Test
    public void testGet() {
        dao.getUser(user);
    }
//    @Test
//    public void testGet() {
//        usersDao.getUser(user.getId());
//    }
    @Test
    public void testRemoveUser() {
        dao.removeUser(user);
    }
    @Test
    public void testUpdateUser() {
        dao.updateUser(user);
    }
    @Test
    public void testGetAllUsers() {
        dao.getAll()
                .stream()
                .forEach(System.out::println);
    }


}
