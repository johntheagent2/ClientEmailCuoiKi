package com.example.clientemailcuoiki.Client;

import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Email implements Serializable {

    private boolean isNew;
    @ManyToOne
    private String sender;
    private String receiver;
    private String subject;
    private String mainBody;
    private String dateSent;
    private List<String> label;

    public Email(String sender, String receiver, String subject, String mainBody, String dateSent) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.mainBody = mainBody;
        this.dateSent = dateSent;
        label = new ArrayList<>();
        isNew = true;
    }
    public Email(String sender, String receiver, String mainBody, String dateSent) {
        this(sender, receiver, "", mainBody, dateSent);
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public void addLabel(String newLabel){
        label.add(newLabel);
    }
    public void removeLabel(String removelabel){
        for(int i =0; i < label.size(); i++){
            if(label.get(i).equals(removelabel)){
                label.remove(i);
                return;
            }
        }
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
