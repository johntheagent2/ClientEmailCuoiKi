package com.example.clientemailcuoiki.Client;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Constants;
import com.example.clientemailcuoiki.MailServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {

    private MailServer server;
    private final Socket socket;
    private ObjectOutputStream outObject;
    private DataOutputStream out;
    private DataInputStream in;
    private Account loggedInAccount;

    public ClientHandler(MailServer server, Socket socket) {
        this.server = server;
        this.socket = socket;

        try {
            this.outObject = new ObjectOutputStream(socket.getOutputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {

            int emailId;
            Account acc;
            Email requestedEmail;
            String requestType, email, password, name, phoneNum;

            while (true) {

                requestType = this.in.readUTF();

                switch (requestType) {

                    case Constants.LOGIN:

                        email = in.readUTF();
                        password = in.readUTF();

                        acc = server.checkAccount(email, password);

                        if (acc != null) { // Account was found
                            loggedInAccount = acc;
                            out.writeUTF(Constants.LOGIN_CORRECT);
                        } else {
                            out.writeUTF(Constants.LOGIN_WRONG);
                        }

                        break;

                    case Constants.REGISTER:

                        email = in.readUTF();
                        password = in.readUTF();
                        name = in.readUTF();
                        phoneNum = in.readUTF();

                        acc = server.tryAddAccount(email, password, name, phoneNum);

                        if (acc != null) { // Account was created
                            loggedInAccount = acc;
                            out.writeUTF(Constants.REGISTERED_SUCCESFULLY);
                        } else {
                            out.writeUTF(Constants.EMAIL_ALREADY_EXISTS);
                        }

                        break;

                    case Constants.NEW_EMAIL:

                        String sender = loggedInAccount.getEmail();
                        String receiver = in.readUTF();
                        String subject = in.readUTF();
                        String mainBody = in.readUTF();
                        String date = in.readUTF();

                        boolean emailSent = server.newEmail(sender, receiver, subject, mainBody, date);

                        if (emailSent) {
                            out.writeUTF(Constants.EMAIL_SENT_SUCCESFULLY);
                        } else {
                            out.writeUTF(Constants.RECEIVER_NOT_FOUND);
                        }

                        break;

                    case Constants.ADD_LABEL:

                        email = loggedInAccount.getEmail();
                        String labelAdd = in.readUTF();

                        boolean labelAdded = server.addLabelToAccount(email, labelAdd);

                        if (labelAdded) {
                            out.writeUTF(Constants.ADD_LABEL_SUCCESSFULLY);
                        } else {
                            out.writeUTF(Constants.ADD_LABEL_FAILED);
                        }

                        break;

                    case Constants.REMOVE_LABEL:

                        email = loggedInAccount.getEmail();
                        String labelRemove = in.readUTF();

                        boolean labelRemoved = server.removeLabelFromAccount(email, labelRemove);

                        if (labelRemoved) {
                            out.writeUTF(Constants.REMOVE_LABEL_SUCCESSFULLY);
                        } else {
                            out.writeUTF(Constants.REMOVE_LABEL_FAILED);
                        }

                        break;

                    case Constants.ADD_LABEL_TO_MAIL:
                        emailId = Integer.parseInt(in.readUTF());
                        sender = server.getEmailInfo(loggedInAccount.getEmail(), emailId);
                        outObject.reset();
                        outObject.writeObject(sender);
                        outObject.flush();
                        break;

                    case Constants.REMOVE_LABEL_TO_MAIL:
                        emailId = Integer.parseInt(in.readUTF());
                        String label = in.readUTF();
                        boolean isAdded = server.addLabelToMail(loggedInAccount.getEmail(), emailId, label);
                        outObject.reset();
                        outObject.writeObject(isAdded);
                        outObject.flush();
                        break;

                    case Constants.GET_ACCOUNT_LABEL:

                        List<String> labelGet = server.getAccountLabel(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(labelGet);
                        outObject.flush();


                        break;

                    case Constants.SHOW_EMAILS:

                        List<Email> emails = server.getEmails(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(emails);
                        outObject.flush();

                        break;


                    case Constants.SHOW_SENT_EMAILS:

                        List<Email> sentEmails = server.getSentMails(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(sentEmails);
                        outObject.flush();
                        break;

                    case Constants.READ_EMAIL:
                        emailId = Integer.parseInt(in.readUTF());
                        sender = server.getEmailInfo(loggedInAccount.getEmail(), emailId);
                        outObject.reset();
                        outObject.writeObject(sender);
                        outObject.flush();
                        break;

                    case Constants.DELETE_EMAIL:

                        emailId = Integer.parseInt(in.readUTF());
                        server.deleteEmail(loggedInAccount.getEmail(), emailId);

                        break;

                    case Constants.CHANGE_PASSWORD:
                        email = loggedInAccount.getEmail();
                        password = in.readUTF();
                        String newPassword = in.readUTF();

                        acc = server.changePassword(email, password, newPassword);
                        if (acc != null) { // Account was found
                            loggedInAccount = acc;
                            out.writeUTF(Constants.CHANGE_PASSWORD_SUCCESFULLY);
                        } else {
                            out.writeUTF(Constants.CHANGE_PASSWORD_FAILED);
                        }
                        break;
                    case Constants.REQUEST_USER_DETAILS:
                        String account = server.getEmailAccount(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(account);
                        outObject.flush();
                        break;
                    case Constants.REQUEST_USER_DETAILS_SUCCESSFULLY:
                        name = server.getnameAccount(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(name);
                        outObject.flush();
                        break;
                    case Constants.REQUEST_USER_DETAILS_FAILED:
                        phoneNum = server.getPhoneNumAccount(loggedInAccount.getEmail());
                        outObject.reset();
                        outObject.writeObject(phoneNum);
                        outObject.flush();
                        break;
                    case Constants.REQUEST_BLOCK_USER:
                        email = loggedInAccount.getEmail();
                        String blockedEmail = in.readUTF();
                        boolean isBlocked = server.blockUser(email, blockedEmail);
                        if (isBlocked) { // Account was found
                            out.writeUTF(Constants.REQUEST_BLOCK_USER_SUCCESFULLY);
                        } else {
                            out.writeUTF(Constants.REQUEST_BLOCK_USER_FAILED);
                        }
                        break;
                    case Constants.REQUEST_UPDATE_USER_INFORMATIONS:
                        email = loggedInAccount.getEmail();
                        name = in.readUTF();
                        phoneNum = in.readUTF();
                        server.changeUserDetails(email, name, phoneNum);
                        break;
                }
            }

        } catch (Exception e) {
            server.unplugClientHandler(this);

            try {
                socket.close();
                in.close();
                out.close();
                outObject.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}