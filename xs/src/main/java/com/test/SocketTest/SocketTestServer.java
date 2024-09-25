package com.test.SocketTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer extends Thread {

    private static final int PORT = 12345;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started, waiting for client connection...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                new ClientHandler(clientSocket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream output = socket.getOutputStream();

                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Received from client: " + clientMessage);
                    output.write(("Message received: " + clientMessage).getBytes());
                    output.flush();
                }

                input.close();
                output.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
