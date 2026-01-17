package org.example;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cipherator cipher = new Cipherator();

        while (true) {
            try {
                Action action = readAction();

                if (action == Action.EXIT) {
                    break;
                }

                String text = readText();
                int shift = readShift();

                String result = switch (action) {
                    case ENCRYPT -> cipher.encrypt(text, shift);
                    case DECRYPT -> cipher.decrypt(text, shift);
                    default -> throw new IllegalStateException();
                };

                System.out.println("\nResult:");
                System.out.println(result);
                System.out.println();

            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private static String readText() {
        while (true) {
            System.out.print("Enter text: ");
            String text = scanner.nextLine();

            if (!text.isBlank()) {
                return text;
            }
            System.err.println("Text must not be empty");
        }
    }

    private static int readShift() {
        while (true) {
            System.out.print("Enter shift: ");
            if (scanner.hasNextInt()) {
                int shift = scanner.nextInt();
                scanner.nextLine();
                return shift;
            }
            System.err.println("Shift must be a number");
            scanner.nextLine();
        }
    }

    private static Action readAction() {
        while (true) {
            System.out.println("Choose action:");
            System.out.println("1 - Encrypt");
            System.out.println("2 - Decrypt");
            System.out.println("0 - Exit");
            System.out.print("-> ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    return Action.ENCRYPT;
                case "2":
                    return Action.DECRYPT;
                case "0":
                    return Action.EXIT;
                default:
                    System.err.println("Wrong answer");
            }
        }
    }

    private enum Action {
        ENCRYPT,
        DECRYPT,
        EXIT
    }
}
