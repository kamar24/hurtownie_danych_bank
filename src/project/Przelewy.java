package project;

public class Przelewy {
    private int rok;
    private int miesiac;
    private int kwota;

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public int getKwota() {
        return kwota;
    }

    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    public Przelewy(int rok, int miesiac, int kwota) {
        this.rok = rok;
        this.miesiac = miesiac;
        this.kwota = kwota;
    }
}
