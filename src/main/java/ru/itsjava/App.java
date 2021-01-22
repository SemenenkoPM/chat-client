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
        while (true) {

            System.out.println("Введите 1 для регистрации, введите 2 для авторизации");
            String numMenu = consoleReader.readLine();
            socketWriter.println(numMenu);
            socketWriter.flush();
            if (numMenu.equals("1")) {
                while (true) {
                    System.out.println("Введите логин: ");
                    String login = consoleReader.readLine();

                    System.out.println("Введите пароль: ");
                    String password = consoleReader.readLine();

                    socketWriter.println("!auth!" + login + ":" + password);
                    socketWriter.flush();
                    BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String serverMessage = serverReader.readLine();
                    System.out.println(serverMessage);
                    if (serverMessage.equals("Вы успешно авторизовались")) {
                        break;
                    }
                }
                break;
            }
            if (numMenu.equals("2")) {

                System.out.println("Введите новый логин: ");
                String login = consoleReader.readLine();
                socketWriter.println(login);
                socketWriter.flush();

                System.out.println("Введите новый пароль: ");
                String password = consoleReader.readLine();
                socketWriter.println(password);
                socketWriter.flush();

                System.out.println("Регистрация успешна");


            }

        }
            new Thread(new ServerRunnable(socket)).start();

            while (true) {
                socketWriter.println(consoleReader.readLine());
                socketWriter.flush();
            }
//        Thread.sleep(5000);
//        socketWriter.println("Privet");
//        socketWriter.flush();

        }
    }

