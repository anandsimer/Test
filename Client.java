/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anands@tcd.ie
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        ObjectOutputStream outgoing = null;
        ObjectInputStream incoming = null;
        String message;
        ArrayList<String> chat_group;

        try {
            while(true){
            Socket socket = new Socket("127.0.0.1", 7003);
            outgoing = new ObjectOutputStream(socket.getOutputStream());
            
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter your chatroom : ");
            System.out.println(" e.g. NDS");
            chat_group=new ArrayList<String>();
            chat_group.add(0, sc.nextLine());
            System.out.println("Enter your name : ");
            chat_group.add(1, sc.nextLine());
            outgoing.writeObject(chat_group);
            
            //BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            //String input = userInput.readLine();
            //outgoing.writeObject(input);
    
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = inFromServer.readLine();
            System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }

    }
}
