package ru.itsjava;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    private final static String HOST = "localhost";
    private final static int PORT = 8080;

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("I'm connected");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter socketWriter = new PrintWriter(socket.getOutputStream());

        new Thread(new ServerRunnable(socket)).start();



        System.out.println("Введите логин: ");
        String login = consoleReader.readLine();

        System.out.println("Введите пароль: ");
        String password = consoleReader.readLine();

        socketWriter.println("!auth!" + login + ":" + password);
        socketWriter.flush();

        while (true) {
        socketWriter.println(consoleReader.readLine());
        socketWriter.flush();
    }
//        Thread.sleep(5000);
//        socketWriter.println("Privet");
//        socketWriter.flush();

    }
}
