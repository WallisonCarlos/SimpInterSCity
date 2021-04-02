package org.interscity.simpinterscity.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.net.*;
import java.io.*;

@Service
public class SocketService {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Async
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        System.out.println(greeting);
        out.println("Welcome!!\nConnected!!!");
    }

    public PrintWriter getPrintWriter() {
        return out;
    }

    public void stop()  throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

}