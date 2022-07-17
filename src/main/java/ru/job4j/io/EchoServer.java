package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (var server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                var socket = server.accept();
                try (var out = socket.getOutputStream();
                     var in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    var firstLine = in.readLine();
                    String[] string = firstLine.split("\\s");
                    var msg = string[1].replaceAll("(\\/\\?msg=)", "");
                    if ("Bye".equals(msg)) {
                        server.close();
                    }
                    System.out.println(firstLine);
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
