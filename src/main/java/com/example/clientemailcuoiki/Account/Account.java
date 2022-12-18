package com.example.clientemailcuoiki.Account;
import com.example.clientemailcuoiki.Client.Email;

import java.util.ArrayList;
import java.util.List;
public class Account {

    private final String email;
    private String name;
    private String phoneNum;
    private String password;
    private final List<Email> mailBox;

    private final List<Account> blockedUser;

    public Account(String email, String name, String phoneNum, String password) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        mailBox = new ArrayList<>();
        blockedUser = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void addEmail(Email email) {
        mailBox.add(email);
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
