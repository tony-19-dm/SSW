package com.example.filefilter;

public class ErrorHandler {
    public void handleError(String message) {
        System.err.println("ERROR: " + message);
        System.exit(1); // Прерываем выполнение программы
    }
}
