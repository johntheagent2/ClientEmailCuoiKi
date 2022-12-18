package com.example.clientemailcuoiki.Account;
import com.example.clientemailcuoiki.Client.Email;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String email;
    private String name;
    private String phoneNum;
    private String password;
    private List<Email> mailBox;
    private List<Email> sentMailBox;
    private List<Account> blockedUser;

    public Account(){}

    public Account(String email, String name, String phoneNum, String password) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        mailBox = new ArrayList<>();
        sentMailBox = new ArrayList<>();
        blockedUser = new ArrayList<>();
    }

    public List<Account> getBlockedUser() {
        return blockedUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMailBox(List<Email> mailBox) {
        this.mailBox = mailBox;
    }

    public void setSentMailBox(List<Email> sentMailBox) {
        this.sentMailBox = sentMailBox;
    }

    public void setBlockedUser(List<Account> blockedUser) {
        this.blockedUser = blockedUser;
    }

    public void addEmail(Email email) {
        mailBox.add(email);
    }

    public void addSentMail(Email email) {
        sentMailBox.add(email);
    }

    public void addBlockedUser(Account account) {
        blockedUser.add(account);
    }

    public String getEmail() {
        return email;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public String getPassword(){return password;}

    public List<Email> getMailBox() {
        return mailBox;
    }

    public List<Email> getSentMailBox(){
        return sentMailBox;
    }
    public List<Account> getBlockedUsers() {
        return blockedUser;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
