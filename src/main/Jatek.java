package main;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class Jatek {
    
    private final Random RND = new Random();
    private final int MAX_KOR;
    private final int ESELYSZAZALEK = 20;
    private JatekSebesseg jatekmenet;
    private String tipp;
    private Csiga nyertes = null;
    private int nyertesTav;
    
    private final Csiga PIROSCSIGA = new Csiga(Szinek.PIROS);
    private final Csiga ZOLDCSIGA = new Csiga(Szinek.ZOLD);
    private final Csiga KEKCSIGA = new Csiga(Szinek.KEK);
    private final Csiga[] CSIGAK = {PIROSCSIGA, ZOLDCSIGA, KEKCSIGA};

    public Jatek() {
        this(5, JatekSebesseg.GYORS);
    }

    public Jatek(int MAX_KOR, JatekSebesseg jatekmenet) {
        this.MAX_KOR = MAX_KOR;
        this.jatekmenet = jatekmenet;
    }

    public void start() {
        System.out.print("Melyik csiga fog nyerni? (p,z,k): ");
        Scanner tippeles = new Scanner(System.in);
        tipp = tippeles.nextLine().trim().toLowerCase();
        System.out.println("Játék keződik");
        
        megjelenites();
    }


    private void megjelenites() {
        TeljesVersenyKiiras();
        kiertekeles();
        uzenetKonzolra("A győztes:", nyertes, "("+nyertesTav+")", ".");
        JatekosNyerteEKiiras();
    }   
    
    private void kamuTorles() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void TeljesVersenyKiiras() {
        for (int kor = 1; kor <= MAX_KOR; kor++) {
            kamuTorles();
            System.out.println("\n┏────────────────────────────────────────┓");
            uzenetKonzolra("  Választott:", getTippeltCsiga(), "          "+kor+"", ". kör   \n");
            
            egyVersenyKor();

            System.out.println("\n┗────────────────────────────────────────┛");
            try { Thread.sleep(jatekmenet.getIdo()); } catch (InterruptedException e) {}
        }
    }

    public void egyVersenyKor() {
        gyorsitoSorsolas();
        for (Csiga cs : CSIGAK) {
            cs.megy();
            rajzol(false, cs);
            cs.setGyorsito(false);
        }
    }
    
    public void rajzol(boolean adattal, Csiga cs) {
        cs.setMegtettUt(cs.karakterKiir());
        if (adattal){
            System.out.println("  "+cs.getSzinkod() + cs.getMegtettUt() + cs.getCsiga() + Szinek.RESET.getAnsiSzinkod() + " (" + cs.getTav() + ")");
        }else {
            System.out.println("  "+cs.getSzinkod() + cs.getMegtettUt() + cs.getCsiga() + Szinek.RESET.getAnsiSzinkod());
        }
    }

    private void gyorsitoSorsolas(){
        if (RND.nextInt(100) < ESELYSZAZALEK) {
            int gyorsitottIndex = RND.nextInt(CSIGAK.length);
            CSIGAK[gyorsitottIndex].setGyorsito(true);
        }
    }
    
    public Csiga getTippeltCsiga(){
        Csiga tippelt = null;
        switch (tipp) {
            case "p" -> tippelt = PIROSCSIGA;
            case "k" -> tippelt = KEKCSIGA;
            case "z" -> tippelt = ZOLDCSIGA;
        }
        return tippelt;
    }
    
    public void kiertekeles() {
        nyertes = CSIGAK[0];
        for (Csiga cs : CSIGAK) {
            if (cs.getTav() > nyertes.getTav()) {
                nyertes = cs;
            }
        }
        nyertesTav = nyertes.getTav();
    }
    
    public void JatekosNyerteEKiiras(){
        boolean tippEltalalva = nyertes == getTippeltCsiga();
            
        if (tippEltalalva) {
            uzenetKonzolra("\nGratulálok! A csigád (", getTippeltCsiga(), ") nyert", "!");
        } else {
            uzenetKonzolra("\nA csigád (", getTippeltCsiga(), ") vesztett. "+nyertes.getCsiga()+Szinek.RESET.getAnsiSzinkod(), " csiga nyert.");
        }
    }
    
    public void uzenetKonzolra(String szovegEleje, Csiga csiga, String valtozo, String szovegVege){
        System.out.println(szovegEleje + " " + csiga.getCsiga()
                + Szinek.RESET.getAnsiSzinkod() + " "
                + valtozo + szovegVege);
    }
    
    public Random getRND() {
        return RND;
    }

    public int getMAX_KOR() {
        return MAX_KOR;
    }

    public int getESELYSZAZALEK() {
        return ESELYSZAZALEK;
    }

    public String getTipp() {
        return tipp;
    }

    public Csiga getNyertes() {
        return nyertes;
    }

    public int getNyertesTav() {
        return nyertesTav;
    }

    public Csiga[] getCsigak() {
        return CSIGAK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.MAX_KOR;
        hash = 79 * hash + this.ESELYSZAZALEK;
        hash = 79 * hash + Objects.hashCode(this.jatekmenet);
        hash = 79 * hash + Arrays.deepHashCode(this.CSIGAK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jatek other = (Jatek) obj;
        if (this.MAX_KOR != other.MAX_KOR) {
            return false;
        }
        if (this.ESELYSZAZALEK != other.ESELYSZAZALEK) {
            return false;
        }
        if (this.jatekmenet != other.jatekmenet) {
            return false;
        }
        return Arrays.deepEquals(this.CSIGAK, other.CSIGAK);
    }    
}
