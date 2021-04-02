package org.interscity.simpinterscity.service;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService extends WebSocketServer {

    private static int TCP_PORT = 8081;

    private Set<WebSocket> conns;

    public WebSocketService() {
        super(new InetSocketAddress(TCP_PORT));
        conns = new HashSet<>();
    }

    @Override
    public void onClose(WebSocket conn, int arg1, String arg2, boolean arg3) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception e) {
        if (conn != null) {
            conns.remove(conn);
            // do some thing if required
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket arg0, String message) {
        System.out.println("Message from client: " + message);
        for (WebSocket sock : conns) {
            System.out.println(sock.toString());
            sock.send(message);
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake arg1) {
        conns.add(conn);
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());        
    }

    @Override
    public void onStart() {
        super.start();
        System.out.println("Server socket start!!!!");
    }
    
}
