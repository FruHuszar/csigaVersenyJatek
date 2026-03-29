package main;

public enum Szinek {

    RESET("\u001B[0m"),
    PIROS("\u001B[31m"),
    ZOLD("\u001B[32m"),
    KEK("\u001B[34m");

    private final String ansiSzinkod;

    Szinek(String ansiSzinkod) {
        this.ansiSzinkod = ansiSzinkod;
    }

    public String getAnsiSzinkod() {
        return ansiSzinkod;
    }
}