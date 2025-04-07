package jm.task.core;

public class ConsoleUtils {
    public static void printSuccess(String message) {
        System.out.println("\033[0;32m" + message + "\033[0m"); // Зеленый цвет для успеха
    }

    public static void printError(String message) {
        System.out.println("\033[0;31m" + message + "\033[0m"); // Красный цвет для ошибок
    }

    public static void printInfo(String message) {
        System.out.println("\033[0;34m" + message + "\033[0m"); // Синий цвет для информации
    }
}