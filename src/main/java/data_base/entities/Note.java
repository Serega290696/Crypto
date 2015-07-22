package data_base.entities;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Serega on 21.07.2015.
 */
@Entity
@Table(name = "notes")
@NamedQuery(name = "Note.getAll", query = "SELECT n FROM Note n")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private String value;

    @Column(name = "id_user")
    private long idUser;

    @Column(name = "date")
    private Date date;

    public Note() {
        this.title = "Note #" + id;
        this.value = "Random text: " + Math.round(Math.random()*1000000);
        idUser = 14;
//        UsersDAO usersDAO = new UsersDAO();
//        List<User> allUsers = usersDAO.getAll();
//        this.idUser =
//                allUsers.get((int) Math.round(Math.random() * allUsers.size())).getId();
    }

    public Note(String title, String value, long idUser, Date date) {
        this.title = title;
        this.value = value;
        this.idUser = idUser;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public long getIdUser() {
        return idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long idNote) {
        this.id = idNote;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
