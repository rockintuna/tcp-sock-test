package me.rockintuna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Hello, World!");
        int port = 58080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("server socket open on port: {}.", port);

            for (int i = 0; i < 10; i++) {
                log.info("waiting for connection...");
                try (Socket socket = serverSocket.accept()){
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String s = bufferedReader.readLine();
                    log.info("received message: {}", s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            log.info("socket closed.");
            try {
                sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
