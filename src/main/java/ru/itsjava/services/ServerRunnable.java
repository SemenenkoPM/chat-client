package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
@RequiredArgsConstructor
public class ServerRunnable implements Runnable{
    private final Socket socket;

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String serverMessage;
        while ((serverMessage = serverReader.readLine()) != null) {
            System.out.println(serverMessage);
        }

    }
}
