package dbTests;

import com.data_base.data_access_objects.UsersDAO;
import com.data_base.entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Serega on 19.07.2015.
 */
public class UsersDaoTest extends Assert {


    private final UsersDAO usersDao = new UsersDAO();

    //    Session session = HibernateUtil.getSessionFactory().openSession();
    private final User user;
    private final User user2 = new User();

    {
        user = new User();
        user.setPasswordToMD5("123456");
        user.setName("user");
        user.setLastVisitDate(new Date());
        user.setMaxNoteLength(100);
        user.setMaxNotes(100);
        user.setRegistrationDate(new Date());

        user2.setLogin("Bob");
        user2.setMail("mailboba@gmail.com");
        user2.setPasswordToMD5("qwerty123");
        user2.setName("Bob");
//        user2.setLastVisitDate(new Date());
        user2.setMaxNoteLength(200);
        user2.setMaxNotes(50);
//        user2.setRegistrationDate(new Date());
    }


    @Test(timeout = 1000)
    public void testAddUser() {
        int tmp = usersDao.getAll().size();
        if (usersDao.add(user2))
            assertEquals("Users was not added!", tmp+1, usersDao.getAll().size());
    }

    @Test(timeout = 2000)
    public void testAddUsers() {
        for (int i = 0; i < 10; i++) {
            int tmp = usersDao.getAll().size();
            if (usersDao.add(new User()))
                tmp++;
            assertEquals("Users was not added!", tmp, usersDao.getAll().size());
        }
    }

    @Test
    public void testRemoveUser() {
        int tmp = usersDao.getAll().size();
        usersDao.remove(user2);
        tmp--;
        assertEquals("Users was not deleted!", tmp, usersDao.getAll().size());
    }

    @Test
    public void testUpdateUser() {
        int tmp = usersDao.getAll().size();
        usersDao.update(user);
        assertEquals("Extra user was added!", tmp, usersDao.getAll().size());
    }

    @Test
    public void testGetAllUsers() {
        int amount = usersDao.getAll().size();
        System.out.println("Number of users: " + amount + ".");
        assertNotEquals("Don't found any users!", amount, 0);
    }

    @Test
    public void testGetUser() {
        assertNotNull("User not found", usersDao.get(user2));
    }


}
