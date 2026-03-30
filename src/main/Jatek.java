package main;
import java.util.Random;
import java.util.Scanner;
public class Jatek {
        
    //adattagok
    private final Random RND = new Random();
    private final Csiga[] CSIGAK = {new Csiga(Szinek.PIROS), new Csiga(Szinek.ZOLD), new Csiga(Szinek.KEK)};
    private final int ESELYSZAZALEK = 20;
    private final int MAXKOR;
    private JatekSebesseg jatekmenet;
    private Csiga nyertes = null;
    private String tipp;
    private int nyertesTav;
    
    //konstruktor
    public Jatek() {
        this(5, JatekSebesseg.GYORS);
    }

    public Jatek(int MAXKOR, JatekSebesseg jatekmenet) {
        this.MAXKOR = MAXKOR;
        this.jatekmenet = jatekmenet;
    }

    //saját függvény
    public void start() {
        fogad();
        jatekMegjelenit();
    }

    private void fogad() {
        System.out.print("Melyik csiga fog nyerni? (p,z,k): ");
        Scanner tippeles = new Scanner(System.in);
        tipp = tippeles.nextLine().trim().toLowerCase();
    }

    private void jatekMegjelenit() {
        verseny();
        kiertekeles();
        uzenetKonzolra("A győztes:", nyertes, "("+nyertesTav+")", ".");
        nyertesKiir();
    }   

    private void verseny() {
        for (int kor = 1; kor <= MAXKOR; kor++) {
            aktualisKorFrissit();
            aktualisKorMegjelenit(kor);
            alszik();
        }
    }

    private void aktualisKorFrissit() {
        gyorsitoSorsolas();
        for (Csiga cs : CSIGAK) {
            cs.megy();
        }
    }
    
    private void aktualisKorMegjelenit(int kor){
        konzolTorles();
        System.out.println("\n┏────────────────────────────────────────┓");
        uzenetKonzolra("  Választott:", getTippeltCsiga(), "          " + kor + "", ". kör   \n");

        for (Csiga cs : CSIGAK) {
            System.out.println(cs.csikotHuz(false));
            cs.setGyorsito(false); 
        }

        System.out.println("\n┗────────────────────────────────────────┛");
    }
    
    private void kiertekeles() {
        nyertes = CSIGAK[0];
        for (Csiga cs : CSIGAK) {
            if (cs.getTav() > nyertes.getTav()) {
                nyertes = cs;
            }
        }
        nyertesTav = nyertes.getTav();
    }
    
    private void nyertesKiir(){
        boolean tippEltalalva = nyertes == getTippeltCsiga();  
        if (tippEltalalva) {
            uzenetKonzolra("\nGratulálok! A csigád (", getTippeltCsiga(), ") nyert", "!");
        } else {
            uzenetKonzolra("\nA csigád (", getTippeltCsiga(), ") vesztett. "+nyertes.getCsiga()+Szinek.RESET.getAnsiSzinkod(), " csiga nyert.");
        }
    }
    
    private void uzenetKonzolra(String szovegEleje, Csiga csiga, String valtozo, String szovegVege){
        System.out.println(szovegEleje + " " + csiga.getCsiga()
                + Szinek.RESET.getAnsiSzinkod() + " "
                + valtozo + szovegVege);
    }
        
    //saját segédvüggvény
    private void gyorsitoSorsolas(){
        if (RND.nextInt(100) < ESELYSZAZALEK) {
            int gyorsitottIndex = RND.nextInt(CSIGAK.length);
            CSIGAK[gyorsitottIndex].setGyorsito(true);
        }
    }
    
    private void konzolTorles() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private void alszik() {
        try { Thread.sleep(jatekmenet.getIdo()); } catch (InterruptedException e) {}
    }
    
    //getterek
    public Csiga getTippeltCsiga(){
        Csiga tippelt = null;
        switch (tipp) {
            case "p" -> tippelt = CSIGAK[0];
            case "z" -> tippelt = CSIGAK[1];
            case "k" -> tippelt = CSIGAK[2];
        }
        return tippelt;
    }

    public Csiga getNyertes() {
        return nyertes;
    }
}    