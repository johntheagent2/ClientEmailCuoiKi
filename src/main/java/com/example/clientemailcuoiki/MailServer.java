package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Account.AccountsList;
import com.example.clientemailcuoiki.Client.ClientHandler;
import com.example.clientemailcuoiki.Client.Email;

import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailServer extends Thread {

    private static final int DEFAULT_PORT = 5005;

    private final ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clientHandlers;
    private final AccountsList accounts;

    private DatabaseController conn;


    public MailServer(int port) throws SQLException {

        clientHandlers = new ArrayList<>();
        accounts = new AccountsList();
        serverSocket = startServer(port);

    }

    private ServerSocket startServer(int port) throws SQLException {

        ServerSocket serverSocket = null;
        conn = new DatabaseController();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Started server on port " + port);

            conn = new DatabaseController();
            ResultSet rs = conn.getTable("accounts");
            while(rs.next()){
                tryAddAccount(rs.getString(1), rs.getString(4),rs.getString(2),rs.getString(3));
            }conn.closeDatabase();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return serverSocket;
    }


    public Account tryAddAccount(String email, String password, String name, String phoneNum) throws SQLException {
        return accounts.tryAddAccount(email, password, name, phoneNum);
    }

    public Account checkAccount(String email, String password){
        return accounts.checkAccount(email, password);
    }

    public Account changePassword(String email, String oldPassword, String newPassword) throws SQLException {
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

    public boolean newEmail(String sender, String receiver, String subject, String mainBody, String date) throws SQLException {
        return accounts.newEmail(sender, receiver, subject, mainBody, date);
    }

    public boolean addLabelToAccount(String email, String label){
        return accounts.addLabelToAccount(email, label);
    }

    public boolean removeLabelFromAccount(String email, String label){
        return accounts.removeLabelFromAccount(email, label);
    }

    public boolean addLabelToMail(String email, int emailId, String label){
        return accounts.addLabelToMail(email, emailId, label);
    }

    public void removeLabelToMail(Email email, String label){
        accounts.removeLabelToMail(email, label);
    }

    public List<String> getAccountLabel(String email){
        return accounts.getAccountLabel(email);
    }

    public List<Email> getEmails(String email){
        return accounts.getEmails(email);
    }

    public List<Email> getSentMails(String email){
        return accounts.getSentMails(email);
    }

    public String getEmailInfo(String account, int emailId){
        return accounts.readEmailById(account, emailId);
    }

    public void changeUserDetails(String email, String name, String phoneNum) throws SQLException {
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

    public static void main(String[] args) throws Exception {
        Thread server = new MailServer(DEFAULT_PORT);
        server.start();
    }

}