package com.example.websocketchat;

//imports
import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.function.Consumer;

//websocket client chat endpoint
@ClientEndpoint
public class ChatClientEndpoint {
    private Consumer<String> messageHandler;

    /*The onOpen method is annotated with @OnOpen, which means it will be called when a WebSocket
    connection is established with the server. In this case, it simply prints a message to the console
    indicating the connection is established.*/
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server.");
    }

    /*The onMessage method is annotated with @OnMessage, which means it will be called when a message is
    received from the server. If the messageHandler is not null, the method passes the received message to
    the messageHandler by calling its accept method.*/
    @OnMessage
    public void onMessage(String message) {
        if (messageHandler != null) {
            messageHandler.accept(message);
        }
    }

    /*The onClose method is annotated with @OnClose, which means it will be called when the WebSocket
    connection to the server is closed. It simply prints a message to the console indicating its disconnected.*/

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected from server.");
    }

    /*The onError method is annotated with @OnError, which means it will be called when an error occurs
    during the WebSocket communication. Here, method just prints the stack trace of the error.*/
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /*The addMessageHandler method allows you to set a custom message handler (a Consumer<String>)
    that will be called when a message is received from the server. This method is used in the
    ChatClient.java class to set the message handler that prints the received messages to the console.*/

    public void addMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

}
