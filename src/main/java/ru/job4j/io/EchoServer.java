package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    var string = input.readLine();
                    var message = getParameterValue(string, "msg");
                    switch (message) {
                        case "Hello" -> output.write("Hello, dear friend.".getBytes());
                        case "Exit" -> server.close();
                        default -> output.write(message.getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Fatal input/output error", e);
        }
    }

    private static String getParameterValue(String query, String parameterName) {
        var paramsToken = query.split("\\s+")[1];
        var params = paramsToken.substring(paramsToken.indexOf('?') + 1).split("&");
        String value = null;
        for (var p : params) {
            var pair = p.split("=");
            if (pair[0].equals(parameterName)) {
                value = pair[1];
                break;
            }
        }
        return value;
    }
}