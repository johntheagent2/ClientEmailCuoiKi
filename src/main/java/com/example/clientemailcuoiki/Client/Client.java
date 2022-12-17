package com.example.clientemailcuoiki.Client;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanasis
 */
public abstract class Client {

    private static Socket server = null;
    private static DataOutputStream out;
    private static DataInputStream in;
    private static ObjectInputStream inObject;
    public static boolean connectToServer() {
        try {
            server = new Socket("localhost", 5005);

            out = new DataOutputStream(server.getOutputStream());
            in = new DataInputStream(server.getInputStream());
            inObject = new ObjectInputStream(server.getInputStream());

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static boolean register(String email, String password, String name, String phoneNum){
        if (server != null) {
            try {

                out.writeUTF(Constants.REGISTER);
                out.writeUTF(email);
                out.writeUTF(password);
                out.writeUTF(name);
                out.writeUTF(phoneNum);

                String registerResult = in.readUTF();

                switch (registerResult){
                    case Constants.REGISTERED_SUCCESFULLY:
                        return true;
                    case Constants.EMAIL_ALREADY_EXISTS:
                        return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean login(String email, String password){
        if (server != null) {
            try {

                out.writeUTF(Constants.LOGIN);
                out.writeUTF(email);
                out.writeUTF(password);

                String loginResult = in.readUTF();

                switch (loginResult){
                    case Constants.LOGIN_CORRECT:
                        return true;
                    case Constants.LOGIN_WRONG:
                        return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean changePassword(String oldPass, String newPass){
        if (server != null) {
            try {

                out.writeUTF(Constants.CHANGE_PASSWORD);
                out.writeUTF(oldPass);
                out.writeUTF(newPass);

                String changePassResult = in.readUTF();

                System.out.println(changePassResult);

                switch (changePassResult){
                    case Constants.CHANGE_PASSWORD_SUCCESFULLY:
                        return true;
                    case Constants.CHANGE_PASSWORD_FAILED:
                        return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean sendEmail(String receiver, String subject, String mainBody, String date){
        if (server != null) {
            try {

                out.writeUTF(Constants.NEW_EMAIL);
                out.writeUTF(receiver);
                out.writeUTF(subject);
                out.writeUTF(mainBody);
                out.writeUTF(date);

                String loginResult = in.readUTF();

                switch (loginResult){
                    case Constants.EMAIL_SENT_SUCCESFULLY:
                        return true;
                    case Constants.RECEIVER_NOT_FOUND:
                        return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static ArrayList<Email> showEmails(){
        if (server != null) {
            try {
                out.writeUTF(Constants.SHOW_EMAILS);
                return (ArrayList<Email>) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static ArrayList<Email> showSentMails(){
        if (server != null) {
            try {
                out.writeUTF(Constants.SHOW_SENT_EMAILS);
                return (ArrayList<Email>) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static Email readEmail(int emailId){
        if (server != null) {
            try {

                out.writeUTF(Constants.READ_EMAIL);
                out.writeUTF(String.valueOf(emailId));
                return (Email) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static void deleteEmail(int emailId){
        if (server != null) {
            try {

                out.writeUTF(Constants.DELETE_EMAIL);
                out.writeUTF(String.valueOf(emailId));

            } catch (IOException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public static void closeConnection(){
        try {
            server.close();
            in.close();
            out.close();
            inObject.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String requestUserDetails(){
        if (server != null) {
            try {
                out.writeUTF(Constants.REQUEST_USER_DETAILS);
                return (String) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static String requestUserName(){
        if (server != null) {
            try {
                out.writeUTF(Constants.REQUEST_USER_DETAILS_SUCCESFULLY);
                return (String) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static String requestUserPhoneNum(){
        if (server != null) {
            try {
                out.writeUTF(Constants.REQUEST_USER_DETAILS_FAILED);
                return (String) inObject.readObject();

            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public static boolean blockUserEmail(String emailBlocked){
        if (server != null) {
            try {

                out.writeUTF(Constants.REQUEST_BLOCK_USER);
                out.writeUTF(emailBlocked);
                String blockResult = in.readUTF();

                switch (blockResult){
                    case Constants.REQUEST_BLOCK_USER_SUCCESFULLY:
                        return true;
                    case Constants.REQUEST_BLOCK_USER_FAILED:
                        return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void changeUserDetails(String name, String phoneNum){
        if (server != null) {
            try {
                out.writeUTF(Constants.REQUEST_UPDATE_USER_INFORMATIONS);
                out.writeUTF(name);
                out.writeUTF(phoneNum);
            } catch (IOException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}