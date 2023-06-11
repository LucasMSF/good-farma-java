package br.goodfarma.enumerable;

public enum Validations {
    CPF("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"),
    TELEPHONE("\\(\\d{2}\\)\\s\\d{5}-\\d{4}");

    private final String regex;

    Validations(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return this.regex;
    }
}
