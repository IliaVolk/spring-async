package com.springapp.mvc;


import javax.persistence.*;

@Entity
@Table(name = "clinic_emails")
public class MessageEntity {


    @Column(name = "clinic_id")
    private int id;

    @Id
    @Column(name = "email")
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
