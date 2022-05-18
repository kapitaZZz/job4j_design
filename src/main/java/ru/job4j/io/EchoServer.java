package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] message = in.readLine().split(" ")[1].split("=");
                    if ("Hello".equalsIgnoreCase(message[1])) {
                        out.write("Hello!".getBytes(StandardCharsets.UTF_8));
                    } else if ("Exit".equalsIgnoreCase(message[1])) {
                        server.close();
                    } else {
                        out.write("What".getBytes(StandardCharsets.UTF_8));
                    }
                    out.flush();
                }
            }
        }
    }
}
