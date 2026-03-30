package main;
import java.util.Objects;
import java.util.Random;
public class Csiga {

    //adattagok
    private final Random RND = new Random();
    private final String CSIGA = "🐌";
    private final int SEBESSEGFELSOHATAR;
    private boolean gyorsito = false;
    private String rajzoltTav = "";
    private Szinek szin;
    private int sebesseg;
    private int tav;

    //konstruktor
    public Csiga() {
        this(Szinek.RESET);
    }

    public Csiga(Szinek szin) {
        this(szin, 4);
    }
    
    public Csiga(Szinek szin, int SEBESSEGFELSOHATAR) {
        this.szin = szin;
        this.SEBESSEGFELSOHATAR = SEBESSEGFELSOHATAR;
    }

    //saját függvény
    public void megy() {
        sebesseg = RND.nextInt(0, SEBESSEGFELSOHATAR);
        if (gyorsito) {
            sebesseg = (sebesseg == 0) ? 1 : sebesseg * 2;
        }
        tav += sebesseg;
    }
    
    public String csikotHuz(boolean adattal) {
        rajzoltTav += karakterKiir();
        String rajz = "";
        if (adattal){
            rajz += "  "+getSzinkod() + rajzoltTav + getCsiga() + Szinek.RESET.getAnsiSzinkod() + " (" + getTav() + ")";
        }else {
            rajz += "  "+getSzinkod() + rajzoltTav + getCsiga() + Szinek.RESET.getAnsiSzinkod();
        }
        return rajz;
    }

    //saját segédvüggvény
    private String karakterKiir(){
        String karakterek = "";
        for (int i = 0; i < sebesseg; i++) {
            karakterek += getStilusKaraktere();
        }
        return karakterek;
    }
    
    private char getStilusKaraktere() {
        return gyorsito ? '=' : '-';
    }

    public void setGyorsito(boolean megkap) {
        this.gyorsito = megkap;
    }
    
    //getterek
    public Szinek getSzin() {
        return szin;
    }
    
    public String getSzinkod() {
        return szin.getAnsiSzinkod();
    }
    
    public String getCsiga() {
        return getSzinkod() + CSIGA;
    }
    
    public int getTav() {
        return tav;
    }

    public String getRajzoltTav() {
        return rajzoltTav;
    }

    //equals + hash
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.szin);
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
        final Csiga other = (Csiga) obj;
        return this.szin == other.szin;
    }
}
