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
                var socket = server.accept();
                try (var out = socket.getOutputStream();
                     var in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] string = in.readLine().split("\\s");
                    if (string[1].contains("msg=")) {
                        var msg = string[1].replaceAll("(\\/\\?)", "");
                        if ("msg=hello".equalsIgnoreCase(msg)) {
                            System.out.printf("%s > Hello.\r\n", msg);
                        } else if ("msg=exit".equalsIgnoreCase(msg)) {
                            System.out.printf("%s > Завершить работу.\r\n", msg);
                            server.close();
                        } else {
                            System.out.printf("%s > What?\r\n", msg);
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Error IO", e);
        }
    }
}
