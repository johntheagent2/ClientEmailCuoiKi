package com.example.clientemailcuoiki.Account;

import com.example.clientemailcuoiki.Client.Email;
import com.example.clientemailcuoiki.DatabaseController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsList {

    private final List<Account> accounts;

    private DatabaseController conn;


    public AccountsList(){
        accounts = new ArrayList<>();
    }

    // Sends the new email if the receiver exists
    public boolean newEmail(String sender, String receiver, String subject, String mainBody, String date){
        Account receiverAccount = getAccountByEmail(receiver);
        Account senderAccount = getAccountByEmail(sender);
        if (receiverAccount != null){
            if(isBlocked(senderAccount, receiverAccount) || isBlocked(receiverAccount, senderAccount)){
                return false;
            }else{
                receiverAccount.addEmail(new Email(sender, receiver, subject, mainBody, date));
                senderAccount.addSentMail(new Email(sender, receiver, subject, mainBody, date));
                return true;
            }
        }
        return false;
    }

    public boolean isBlocked(Account sender, Account receiver){
        for(Account i : sender.getBlockedUsers()){
            if(i.getEmail().equals(receiver.getEmail()))
                return true;
        }
        return false;
    }

    // Returns all emails for the account with the given email
    public List<Email> getEmails(String email){
        Account account = getAccountByEmail(email);
        if (account != null){
            return account.getMailBox();
        }
        return null;
    }

    public List<Email> getSentMails(String email){
        Account account = getAccountByEmail(email);
        if (account != null){
            return account.getSentMailBox();
        }
        return null;
    }


    // Returns the email with the given emailId for the account with the given email
    public String readEmailById(String email, int emailId){
        Account account = getAccountByEmail(email);
        if (account != null){
            account.getMailBox().get(emailId).read(); // Mark as read (-1 because id starts from 0)
            return account.getMailBox().get(emailId).getSender();
        }
        return null;
    }

    // Deletes the email with the given emailId for the account with the given email
    public void deleteEmailById(String email, int emailId){
        Account account = getAccountByEmail(email);
        if (account != null){
            account.getMailBox().remove(emailId-1); // -1 because id starts from 0
        }
    }


    // Adds account if email not already exists
    public Account tryAddAccount(String email, String password, String name, String phoneNum) throws SQLException {
        if (!accountExists(email)){
            Account newAccount = new Account(email, name, phoneNum, password);
            conn = new DatabaseController();
            conn.addAccountToDatabase(new Account(email, name, phoneNum, password));
            accounts.add(newAccount);
            return newAccount;
        }
        return null;
    }

    public Account checkAccount(String email, String password){
        for (Account account : accounts){
            if (account.getEmail().equals(email) && account.checkPassword(password)){
                return account;
            }
        }
        return null;
    }

    public Account changePassword(String email, String oldPassword,String newPassword) throws SQLException {
        for(Account account: accounts){
            if (account.getEmail().equals(email) && account.checkPassword(oldPassword) && account.changePassword(oldPassword, newPassword)){
                conn = new DatabaseController();
                conn.updatePasswordFromDatabase(email, newPassword);
                return account;
            }
        }
        return null;
    }

    public void updateUserDetails(String email, String name, String phoneNum) throws SQLException {
        Account account = getAccountByEmail(email);
        if (account != null){
            conn = new DatabaseController();
            conn.updateUserInformationFromDatabase(email, name, phoneNum);
            account.setName(name);
            account.setPhoneNum(phoneNum);
        }
    }

    // Checks if email is used by any account
    public boolean accountExists(String emailToCheck){
        for (Account account : accounts){
            if (emailToCheck.equals(account.getEmail())){
                return true;
            }
        }
        return false;
    }

    // Returns the account object with the email given, or null if not found
    public Account getAccountByEmail(String email){
        for (Account account : accounts){
            if (email.equals(account.getEmail())){
                return account;
            }
        }
        return null;
    }

    public String getAccountByEmailString(String email){
        for (Account account : accounts){
            if (email.equals(account.getEmail())){
                return account.getEmail();
            }
        }
        return null;
    }

    public String getAccountNameByString(String email){
        for (Account account : accounts){
            if (email.equals(account.getEmail())){
                return account.getName();
            }
        }
        return null;
    }

    public String getAccountPhoneNumByString(String email){
        for (Account account : accounts){
            if (email.equals(account.getEmail())){
                return account.getPhoneNum();
            }
        }
        return null;
    }

    public boolean blockUserEmail(String email, String blockEmail){
        Account emailUser = getAccountByEmail(email);
        Account blockedAccount = getAccountByEmail(blockEmail);
        if (blockedAccount != null && !emailUser.getEmail().equals(blockedAccount.getEmail())){
            emailUser.addBlockedUser(blockedAccount);
            return true;
        }
        return false;
    }

    public boolean addLabelToAccount(String email, String label){
        Account acc = getAccountByEmail(email);
        if(acc != null && !acc.getLabel().contains(label)){
            acc.addLabel(label);
            return true;
        }return false;
    }

    public boolean removeLabelFromAccount(String email, String label){
        Account acc = getAccountByEmail(email);
        if(acc != null){
            acc.removeLabel(label);
            return true;
        }return false;
    }

    public boolean addLabelToMail(String email, int emailId, String label){
        Account account = getAccountByEmail(email);
        if (account != null){
            account.getMailBox().get(emailId).addLabel(label);
            return true;
        }
        return false;
    }

    public void removeLabelToMail(Email email, String label){
        email.removeLabel(label);
    }

    public List<String> getAccountLabel(String email){
        Account acc = getAccountByEmail(email);
        if(acc != null){
            return acc.getLabel();
        }return null;
    }

}