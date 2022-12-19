package com.example.clientemailcuoiki;

import com.example.clientemailcuoiki.Account.Account;
import com.example.clientemailcuoiki.Client.Email;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emailsimulator", "root", "");
//    private final Statement stmt = conn.createStatement();

    public DatabaseController() throws SQLException {
    }

    public ResultSet getTable(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement pstm = conn.prepareStatement(sql);
        return pstm.executeQuery();
    }


    public void addAccountToDatabase(Account acc) throws SQLException {
        if(acc != null){
            String sql = "INSERT IGNORE INTO `accounts`(`email`, `name`, `phoneNum`, `password`) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, acc.getEmail());
            pstm.setString(2, acc.getName());
            pstm.setString(3, acc.getPhoneNum());
            pstm.setString(4, acc.getPassword());
            pstm.execute();
        }
    }

    public void addMailToDatabase(Email email) throws SQLException {
        String sql = "INSERT INTO `emails`(`sender`, `receiver`, `subject`, `mainBody`, `dateSent`, `isNew`) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email.getSender());
        pstm.setString(2, email.getReceiver());
        pstm.setString(3, email.getSubject());
        pstm.setString(4, email.getMainBody());
        pstm.setString(5, email.getDateSent());
        pstm.setBoolean(6, email.getIsNew());
        pstm.execute();
    }

    public void updatePasswordFromDatabase(String email, String password) throws SQLException {
        String sql = "UPDATE `accounts` SET `password`='"+password+"' WHERE email = '"+email+"'";;
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.execute();
    }

    public void updateUserInformationFromDatabase(String email, String name, String phoneNum) throws SQLException {
        String sql = "UPDATE `accounts` SET `name`='"+name+"', `phoneNum`='"+phoneNum+"' WHERE email = '"+email+"'";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.execute();
    }


    public void deleteContentFromTable(String playlist, ArrayList<String> songs) throws SQLException {
            String sql="DELETE FROM "+ playlist + " WHERE NAME = ''";
            PreparedStatement something = conn.prepareStatement(sql);
            something.execute();
    }

    public void deleteTable(String playlist) throws SQLException {
        String sql="DROP TABLE "+ playlist;
        PreparedStatement something = conn.prepareStatement(sql);
        something.execute();
    }

    public void closeDatabase() throws SQLException {
        conn.close();
    }


}
