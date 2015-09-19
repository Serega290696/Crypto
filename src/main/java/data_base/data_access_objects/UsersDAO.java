package data_base.data_access_objects;

import data_base.HibernateUtil;
import data_base.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Serega on 19.07.2015.
 */
public class UsersDAO implements DAOInterface<User> {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private static final Logger loggerDBManipulation = Logger.getLogger("logDBManipulation"); // dbManipulation.log

//    static {
//        loggerDBManipulation.setLevel(Level.INFO);
//    }

    public boolean add(User newUser) {
        boolean userAlreadyExist =
                getAll()
                        .stream()
                        .filter((u) ->
                                (u.getLogin().equals(newUser.getLogin()))
                                        || (u.getMail().equals(newUser.getMail())))
                        .count() > 0 ? true : false;

        if (userAlreadyExist) {
//            throw new DataBaseException("User with such email or login already exist!\n" +
//                    "Please, try to enter another data.");
            loggerDBManipulation.error("During adding new User. User already exist!\nTry to added user: " + newUser);
            System.err.println("User with such email or login already exist!\n" +
                    "Please, try to enter another data.");
            return false;
        } else {
            session.beginTransaction();
            long idT = (long) session.save(newUser);
            session.getTransaction().commit();
            if (loggerDBManipulation.isDebugEnabled())
                loggingTrace("Add user: " + newUser);
            else
                loggingDebug("Add user.");
            return true;
        }
    }

    public User get(User user) {
        User returnedUser = null;
        for (User u : getAll()) {
            if (u.getLogin().equals(user.getLogin())) {
//                System.out.println(u);
//                System.out.println(u.getPassword());
//                System.out.println(user);
//                System.out.println(user.getPassword());
                if (u.getPassword().equals(user.getPassword())) {
                    returnedUser = u;
                    break;
                } else {
                    returnedUser = null;
                    break;
                }
            }
        }
        if (loggerDBManipulation.isDebugEnabled())
            loggingTrace("Get user: " + user);
        else
            loggingDebug("Get user.");
        return returnedUser;
    }

    public User get(long id) {
        session.beginTransaction();
        Query q = session.createQuery("from User as u where u.id=" + id);
        User user = (User) q.uniqueResult();
        if (loggerDBManipulation.isDebugEnabled())
            loggingTrace("Get user by id: " + user);
        else
            loggingDebug("Get user.");
        return user;
    }


    public void remove(User user) {
        final User userT = user;
        user = getAll().stream().filter((u)->(u.compareTo(userT) == 0)).findFirst().get();
        session.beginTransaction();
        session.delete(user);
        session.flush();
        session.getTransaction().commit();
        if (loggerDBManipulation.isDebugEnabled())
            loggingTrace("Delete user: " + user);
        else
            loggingDebug("Delete user.");
    }

    public void update(User user) {
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        if (loggerDBManipulation.isDebugEnabled())
            loggingTrace("Update user: " + user);
        else
            loggingDebug("Update user.");
    }

    public List<User> getAll() {
        List<User> result = session.createQuery("from User order by id").list();
        if (loggerDBManipulation.isDebugEnabled())
            loggingTrace("Get all users. Users amount: " + result.size());
        else
            loggingDebug("Get all users.");
        return result;
    }

    private static void loggingDebug(String message) {
        loggerDBManipulation.debug(message);
    }

    private static void loggingTrace(String message) {
        loggerDBManipulation.trace(message);
    }
}
