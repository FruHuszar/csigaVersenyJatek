package main;
import java.util.Objects;
import java.util.Random;
public class Csiga {

    private final Random RND = new Random();

    private final String CSIGA = "🐌";
    private Szinek szin;
    private boolean gyorsito;
    private int sebesseg;
    private int tav;
    private String megtettUt = "";

    public Csiga() {
        this(Szinek.RESET, false);
    }

    public Csiga(Szinek szin) {
        this.szin = szin;
        this.gyorsito = false;
    }

    public Csiga(Szinek szin, boolean gyorsito) {
        this.szin = szin;
        this.gyorsito = gyorsito;
    }

    public void megy() {
        sebesseg = RND.nextInt(0, 4);
        if (gyorsito) {
            sebesseg = (sebesseg == 0) ? 1 : sebesseg * 2;
        }
        tav += sebesseg;
    }

    public String karakterKiir(){
        String karakterek = "";
        for (int i = 0; i < sebesseg; i++) {
            karakterek += getStilusKaraktere();
        }
        return karakterek;
    }
    
    public char getStilusKaraktere() {
        return gyorsito ? '=' : '-';
    }

    public boolean isGyorsito() {
        return gyorsito;
    }
    
    public void setGyorsito(boolean megkap) {
        this.gyorsito = megkap;
    }
    
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
    
    public int getSebesseg() {
        return sebesseg;
    }
    
    public String getMegtettUt() {
        return megtettUt;
    }

    public void setMegtettUt(String szoveg){
        megtettUt += szoveg;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.CSIGA);
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
        if (!Objects.equals(this.CSIGA, other.CSIGA)) {
            return false;
        }
        return this.szin == other.szin;
    }

}
