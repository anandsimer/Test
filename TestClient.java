/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anands@tcd.ie
 */
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestService {

    Hashtable outputStreams = new Hashtable();
    ObjectOutputStream outgoing;
    
    public static void main(String args[]) throws Exception {
        new Service().runServer();
    }

    public void runServer() throws IOException {
        ServerSocket ss = new ServerSocket(7003);

        while (true) {
            Socket sock = ss.accept();
            System.out.println("Listening");
            outgoing = new ObjectOutputStream(sock.getOutputStream());
            outputStreams.put(sock,outgoing);
            new ServerThread(sock);
        }
    }
        Enumeration getOutputStreams(){
                  return outputStreams.elements();
        }

        void sendToAll(String message) {
         synchronized (outputStreams) {
                for (Enumeration e = getOutputStreams(); e.hasMoreElements();) {
                    ObjectOutputStream outgoing = (ObjectOutputStream)e.nextElement();
                    try{
                    outgoing.writeObject(message);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
