package ru.itsjava;

import lombok.SneakyThrows;

public class App {


    @SneakyThrows
    public static void main(String[] args) {
        new ClientImpl().start();
        }
    }

