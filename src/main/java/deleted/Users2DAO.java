package deleted;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Users2DAO {

    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public User2 add(User2 user){
        em.getTransaction().begin();
        User2 carFromDB = em.merge(user);
        em.getTransaction().commit();
        return carFromDB;
    }
//
//    public void delete(long id){
//        em.remove(get(id));
//    }
//
//    public Car get(long id){
//        return em.find(Car.class, id);
//    }
//
//    public void update(Car car){
//        em.getTransaction().begin();
//        em.merge(car);
//        em.getTransaction().commit();
//    }

    public List<User2> getAll(){
        TypedQuery<User2> namedQuery = em.createNamedQuery("User2.getAll", User2.class);
        return namedQuery.getResultList();
    }

}
