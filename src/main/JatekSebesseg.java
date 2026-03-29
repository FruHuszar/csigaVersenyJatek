package main;

public enum JatekSebesseg {
    VILLAM(300),
    GYORS(450),
    KOZEPES(600),
    LASSU(800),
    LEPESENKENT(1500);

    private final int miliszekundum;

    JatekSebesseg(int miliszekundum) {
        this.miliszekundum = miliszekundum;
    }

    public int getIdo() {
        return miliszekundum;
    }
}