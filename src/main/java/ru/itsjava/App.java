package ru.itsjava;

import lombok.SneakyThrows;
import ru.itsjava.services.ClientImpl;

public class App {


    @SneakyThrows
    public static void main(String[] args) {
        new ClientImpl().start();
        }
    }

