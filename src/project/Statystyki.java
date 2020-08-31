package project;

public class Statystyki {
    private String nr_konta;
    private String imie;
    private String nazwisko;
    private int suma;

    public String getNr_konta() {
        return nr_konta;
    }

    public void setNr_konta(String nr_konta) {
        this.nr_konta = nr_konta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public Statystyki(String nr_konta, String imie, String nazwisko, int suma) {
        this.nr_konta = nr_konta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.suma = suma;
    }
}
