package br.goodfarma.helper;

import br.goodfarma.enumerable.Validations;

import java.util.regex.Pattern;

public class Validation {
    public static boolean validateEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) return false;
        }
        return true;
    }

    public static boolean validateWithRegex(String regex, String validate) {
        return Pattern.matches(regex, validate);
    }

    public static boolean validateWithRegex(Validations validation, String validate) {
        return validateWithRegex(validation.getRegex(), validate);
    }
}
