package com.example.websocketchat;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;


public class ChatClient {
    public static void main (String[]args){
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI uri = URI.create("ws://localhost:8080/websocket/chat");
            ChatClientEndpoint chatClientEndpoint = new ChatClientEndpoint();
            chatClientEndpoint.addMessageHandler(System.out::println);
            Session session = container.connectToServer(chatClientEndpoint, uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = reader.readLine()) != null &&  !input.equals("exit")) {
                session.getBasicRemote().sendText(input);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}