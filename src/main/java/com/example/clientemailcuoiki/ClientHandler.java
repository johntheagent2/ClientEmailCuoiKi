package com.example.clientemailcuoiki;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public static HashMap<String, Socket> client = new HashMap<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    private String receiveUser;

    private String clientIpAddress;

    public ClientHandler(Socket socket) {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            this.clientIpAddress = socket.getInetAddress().toString();
            client.put(clientUsername, socket);
            System.out.println(client);
            clientHandlers.add(this);
            broadcastMessage("SERVER " + "[" + clientUsername+ "-" + clientIpAddress + "]" +" has entered the chat!");
        }catch (IOException e){
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    @Override
    public void run() {
        String messeageFromClient;

        while(socket.isConnected()){
            try{
                messeageFromClient = bufferedReader.readLine();
                broadcastMessage(messeageFromClient);
            }catch (IOException e){
                closeEverything(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try{
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER:" + "[" + clientUsername+ "-" + clientIpAddress + "]" + " has left the chat");
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){

        removeClientHandler();
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
}
