package br.goodfarma.helper;

public class Validation {
    public static boolean validateEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) return false;
        }
        return true;
    }
}
