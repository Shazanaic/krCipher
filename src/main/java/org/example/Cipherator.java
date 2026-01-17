package org.example;

public class Cipherator {

    private static final String ENG_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENG_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String RU_UPPER  = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RU_LOWER  = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String encrypt(String text, int shift) {
        return process(text, shift);
    }

    public String decrypt(String text, int shift) {
        return process(text, -shift);
    }

    private String process(String text, int shift) {
        if (text == null) {
            throw new IllegalArgumentException("Text must be not null");
        }

        StringBuilder result = new StringBuilder(text.length());

        for (char ch : text.toCharArray()) {
            result.append(shifter(ch, shift));
        }

        return result.toString();
    }

    private char shifter(char ch, int shift) {
        if (ENG_UPPER.indexOf(ch) >= 0) {
            return shift(ch, ENG_UPPER, shift);
        }
        if (ENG_LOWER.indexOf(ch) >= 0) {
            return shift(ch, ENG_LOWER, shift);
        }
        if (RU_UPPER.indexOf(ch) >= 0) {
            return shift(ch, RU_UPPER, shift);
        }
        if (RU_LOWER.indexOf(ch) >= 0) {
            return shift(ch, RU_LOWER, shift);
        }
        return ch;
    }

    private char shift(char ch, String alphabet, int shift) {
        int len = alphabet.length();
        int index = alphabet.indexOf(ch);

        int cycl = ((shift % len) + len) % len;
        int newindx = (index + cycl) % len;

        return alphabet.charAt(newindx);
    }
}
