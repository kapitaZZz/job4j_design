package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = in.readLine();
                    if (message.contains("/?msg=ByeHello")) {
                        out.write("Hello!".getBytes(StandardCharsets.UTF_8));
                    } else if (message.contains("/?msg=ByeExit")) {
                        server.close();
                    } else {
                        out.write("What".getBytes(StandardCharsets.UTF_8));
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception when server running - ", e);
        }
    }
}

