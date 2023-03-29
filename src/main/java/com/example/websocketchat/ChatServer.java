package com.example.websocketchat;

/* It creates an instance of the Server class from the Tyrus library, which is the reference
 implementation of the Java API for WebSocket (JSR 356).*/
import org.glassfish.tyrus.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;


//server class
public class ChatServer {
    public static void main (String[] args) {

        Server server = new Server("localhost", 8080, "/websocket", ChatEndpoint.class);
        try {
            server.start(); //starts the method - listen for incoming connections
            System.out.println("server is starting");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }

}
