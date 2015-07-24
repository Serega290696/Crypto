package com.data_base.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serega on 19.07.2015.
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login", length = 45)
    private String login;

    @Column(name = "mail", length = 45)
    private String mail;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "max_notes")
    private long maxNotes;

    @Column(name = "max_note_length")
    private long maxNoteLength;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "last_visit_date")
    private Date lastVisitDate;


    public User() {
        this.login = "exam" + Math.round(Math.random()*100_000L);
        this.mail =  "mail" + Math.round(Math.random()*100_000L) + "@gmail.com";
        this.name = "user";
        this.password = "qwerty";
        this.maxNotes = 50;
        this.maxNoteLength = 100;
        this.registrationDate = new Date();
        this.lastVisitDate = new Date();
    }
    public User(String login, String password) {
        this.login = login;
        this.mail =  "mailT";
        this.name = "user";
        this.password = password;
        this.maxNotes = 50;
        this.maxNoteLength = 100;
        this.registrationDate = new Date();
        this.lastVisitDate = new Date();
    }

//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }
    public User(String login, String mail, String name, String password, long maxNotes, long maxNoteLength, Date registrationDate, Date lastVisitDate) {
        this.login = login;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.maxNotes = maxNotes;
        this.maxNoteLength = maxNoteLength;
        this.registrationDate = registrationDate;
        this.lastVisitDate = lastVisitDate;
    }


    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public long getMaxNotes() {
        return maxNotes;
    }

    public long getMaxNoteLength() {
        return maxNoteLength;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaxNotes(long maxNotes) {
        this.maxNotes = maxNotes;
    }

    public void setMaxNoteLength(long maxNoteLength) {
        this.maxNoteLength = maxNoteLength;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", maxNotes=" + maxNotes +
                ", maxNoteLength=" + maxNoteLength +
                ", registration=" + registrationDate +
                ", lastVisit=" + lastVisitDate +
                '}';
    }


}
