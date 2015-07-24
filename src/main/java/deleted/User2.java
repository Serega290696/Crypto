package deleted;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQuery(name = "User2.getAll", query = "SELECT u from User u")
public class User2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="car_id", length = 6, nullable = false)
    private long id;

    @Column(name = "login", length = 32)
    private String login;      //Название авто

    public User2(String login) {
        this.login = login;
    }

    public User2() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
