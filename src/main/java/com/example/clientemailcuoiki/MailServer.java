package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Account.AccountsList;
import com.example.clientemailcuoiki.Client.ClientHandler;
import com.example.clientemailcuoiki.Client.Email;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MailServer extends Thread {

    private static final int DEFAULT_PORT = 5005;

    private final ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clientHandlers;
    private final AccountsList accounts;


    public MailServer(int port) throws SQLException {

        clientHandlers = new ArrayList<>();
        accounts = new AccountsList();
        serverSocket = startServer(port);

    }

    private ServerSocket startServer(int port) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Started server on port " + port);

            // Adding three test accounts
            tryAddAccount("test1@socketmail.gr", "pass1", "Cao Duc Phat", "0707854816");
            tryAddAccount("test2@socketmail.gr", "pass2", "Cao Van Son", "0703001286");
            tryAddAccount("test3@socketmail.gr", "pass3", "Lam Thinh Phat", "0903981120");

            // Test emails
            newEmail("test1@socketmail.gr", "test1@socketmail.gr", "Test subject (1)", "This is a test email (1)", "17/04/2022");
            newEmail("test2@socketmail.gr", "test1@socketmail.gr", "Test subject (2)", "This is a test email (2)", "15/05/2022");
            newEmail("test3@socketmail.gr", "test1@socketmail.gr", "Test subject (3)", "This is a test email (3)", "16/06/2022");
            newEmail("test1@socketmail.gr", "test2@socketmail.gr", "Test subject (1)", "This is a test email (1)", "17/07/2022");
            newEmail("test2@socketmail.gr", "test2@socketmail.gr", "Test subject (2)", "This is a test email (2)", "18/08/2022");
            newEmail("test3@socketmail.gr", "test2@socketmail.gr", "Test subject (3)", "This is a test email (3)", "19/09/2022");
            newEmail("test1@socketmail.gr", "test3@socketmail.gr", "Test subject (1)", "This is a test email (1)", "20/10/2022");
            newEmail("test2@socketmail.gr", "test3@socketmail.gr", "Test subject (2)", "This is a test email (2)", "21/11/2022");
            newEmail("test3@socketmail.gr", "test3@socketmail.gr", "Test subject (3)", "This is a test email (3)", "22/12/2022");


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return serverSocket;
    }


    public Account tryAddAccount(String email, String password, String name, String phoneNum){
        return accounts.tryAddAccount(email, password, name, phoneNum);
    }

    public Account checkAccount(String email, String password){
        return accounts.checkAccount(email, password);
    }

    public Account changePassword(String email, String oldPassword, String newPassword){
        return accounts.changePassword(email, oldPassword, newPassword);
    }

    public String getEmailAccount(String email){
        return accounts.getAccountByEmailString(email);
    }
    public String getnameAccount(String email){
        return accounts.getAccountNameByString(email);
    }
    public String getPhoneNumAccount(String email){
        return accounts.getAccountPhoneNumByString(email);
    }

    public boolean blockUser(String email, String blockedEmail){
        return accounts.blockUserEmail(email, blockedEmail);
    }
    public boolean newEmail(String sender, String receiver, String subject, String mainBody, String date){
        return accounts.newEmail(sender, receiver, subject, mainBody, date);
    }

    public List<Email> getEmails(String email){
        return accounts.getEmails(email);
    }
    public List<Email> getSentMails(String email){
        return accounts.getSentMails(email);
    }

    public Email getEmail(String account, int emailId){
        return accounts.readEmailById(account, emailId);
    }

    public void changeUserDetails(String email, String name, String phoneNum){
        accounts.updateUserDetails(email, name, phoneNum);
    }

    public void deleteEmail(String account, int emailId){
        accounts.deleteEmailById(account, emailId);
    }

    public void unplugClientHandler(ClientHandler clientHandler) {

        clientHandlers.remove(clientHandler);

    }

    @Override
    public void run() {

        System.out.println("Server is on");
        System.out.println("Waiting for clients...");
        System.out.println("");

        while (true) {

            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + " connected");

                ClientHandler ch = new ClientHandler(this, socket);
                new Thread(ch).start();
                clientHandlers.add(ch);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        Thread server = new MailServer(DEFAULT_PORT);
        server.start();
    }

}