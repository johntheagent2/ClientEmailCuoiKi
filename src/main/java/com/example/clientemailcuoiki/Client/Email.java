package com.example.clientemailcuoiki.Client;

import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class Email implements Serializable {

    private boolean isNew;
    @ManyToOne
    private String sender;
    private String receiver;
    private String subject;
    private String mainBody;
    private String dateSent;

    public Email(String sender, String receiver, String subject, String mainBody, String dateSent) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.mainBody = mainBody;
        this.dateSent = dateSent;
        isNew = true;
    }
    public Email(String sender, String receiver, String mainBody, String dateSent) {
        this(sender, receiver, "", mainBody, dateSent);
    }

    public boolean isNew(){
        return isNew;
    }
    public String getSender(){
        return sender;
    }
    public String getReceiver(){
        return receiver;
    }
    public String getSubject(){
        return subject;
    }
    public String getDateSent(){ return dateSent;}
    public String getMainBody(){
        return mainBody;
    }
    public boolean getIsNew(){
        return isNew;
    }
    public void read(){
        this.isNew = false;
    }

}
