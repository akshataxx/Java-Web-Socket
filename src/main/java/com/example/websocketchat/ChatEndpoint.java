package com.example.websocketchat;

//necessary imports
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//marks this as a server endpoint
//chat - path for websocket connections
@ServerEndpoint(value = "/chat")
    public class ChatEndpoint {
        private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

        /*The onOpen method is annotated with @OnOpen, which means it will be called when a new WebSocket
        connection is established. The method adds the new Session to the sessions set. */
        @OnOpen
        public void onOpen(Session session) {
            sessions.add(session);
        }

        /*The onMessage method is annotated with @OnMessage, which means it will be called when a message is
        received from a client. The method iterates through all active sessions, filters out the sender's session,
        and sends the received message to all other connected clients.*/

        @OnMessage
        public void onMessage(String message, Session session) {
            sessions.stream().filter(s -> !s.equals(session)).forEach(s -> {
                try {
                    s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        /*The onClose method is annotated with @OnClose, which means it will be called when a WebSocket connection
        is closed. The method removes the closed Session from the sessions set.*/

        @OnClose
        public void onClose(Session session) {
            sessions.remove(session);
        }

        /*The onError method is annotated with @OnError, which means it will be called when an error occurs
        during the WebSocket communication. In this simple example, the method just prints the stack trace of
        the error.*/

        @OnError
        public void onError(Throwable error) {
            error.printStackTrace();
        }
    }