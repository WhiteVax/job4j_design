package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (var server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                try (var socket = server.accept();
                     var out = socket.getOutputStream();
                     var in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String requestLine = in.readLine();
                    if (requestLine == null || requestLine.isBlank()) {
                        continue;
                    }

                    String[] parts = requestLine.split("\\s");
                    if (parts.length < 2) {
                        continue;
                    }

                    String path = parts[1];
                    String message = extractMessage(path);

                    if ("Hello".equalsIgnoreCase(message)) {
                        System.out.println("msg=hello > Hello.");
                    } else if ("Exit".equalsIgnoreCase(message)) {
                        System.out.println("msg=Exit > Завершить работу.");
                        server.close();
                    } else if (!message.isEmpty()) {
                        System.out.printf("msg=%s > What?\r\n", message);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Error IO", e);
        }
    }


    private static String extractMessage(String path) {
        int index = path.indexOf("msg=");
        if (index == -1) {
            return "";
        }
        return path.substring(index + 4).split("&")[0];
    }
}