package org.example;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cipherator cipher = new Cipherator();

        try {
            String text = readText();
            int shift = readShift();
            Action action = readAction();

            String result = switch (action) {
                case ENCRYPT -> cipher.encrypt(text, shift);
                case DECRYPT -> cipher.decrypt(text, shift);
            };

            System.out.println("\nResult:");
            System.out.println(result);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String readText() {
        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        if (text.isBlank()) {
            throw new IllegalArgumentException("Must be not null");
        }
        return text;
    }

    private static int readShift() {
        System.out.print("Enter shift: ");
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Must be a number");
        }
        int shift = scanner.nextInt();
        scanner.nextLine();
        return shift;
    }

    private static Action readAction() {
        System.out.print("What to do? \n1 - encrypt | 2 - decrypt: ");
        String input = scanner.nextLine().trim();

        return switch (input) {
            case "1" -> Action.ENCRYPT;
            case "2" -> Action.DECRYPT;
            default -> throw new IllegalArgumentException("wrong answer");
        };
    }

    private enum Action {
        ENCRYPT,
        DECRYPT
    }
}
