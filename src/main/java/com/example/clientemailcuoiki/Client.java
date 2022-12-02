package com.example.clientemailcuoiki;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String ipAddress;

    public Client(Socket socket, String username, String ipAddress) {
        try{
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
            this.ipAddress = socket.getRemoteSocketAddress().toString();
        }catch (IOException e){
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void sendMessage(String messageToSend){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc = new Scanner(System.in);

            while(socket.isConnected()){
//                String messageToSend = sc.nextLine();
                bufferedWriter.write("[" + username+ "-" + ipAddress + "]" + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (IOException e){
            closeEverything(socket,bufferedWriter, bufferedReader);
        }
    }

//    public void listenForMessage(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String messageFromGrpChat;
//
//                while(socket.isConnected()){
//                    try{
//                        messageFromGrpChat = bufferedReader.readLine();
//                        System.out.println(messageFromGrpChat);
//                    }catch (IOException e){
//                        closeEverything(socket,bufferedWriter, bufferedReader);
//                    }
//                }
//            }
//        }).start();
//    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//
//    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your username: ");
//        String username = sc.nextLine();
//        Socket socket = new Socket("localhost", 1234);
//        Client client = new Client(socket, username, socket.getRemoteSocketAddress().toString());
//        client.listenForMessage();
//
//        client.sendMessage();
//    }
}
