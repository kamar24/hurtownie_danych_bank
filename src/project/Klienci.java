package project;

public class Klienci {
    private String imie;
    private String nazwisko;
    private String nr_konta;
    private String pesel;
    private String kod_pocztowy;
    private String ulica;
    private String nr_lokalu;
    private String miejscowosc;


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

    public String getNr_konta() {
        return nr_konta;
    }

    public void setNr_konta(String nr_konta) {
        this.nr_konta = nr_konta;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_lokalu() {
        return nr_lokalu;
    }

    public void setNr_lokalu(String nr_lokalu) {
        this.nr_lokalu = nr_lokalu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Klienci(String imie, String nazwisko, String nr_konta, String pesel, String kod_pocztowy, String ulica, String nr_lokalu, String miejscowosc) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_konta = nr_konta;
        this.pesel = pesel;
        this.kod_pocztowy = kod_pocztowy;
        this.ulica = ulica;
        this.nr_lokalu = nr_lokalu;
        this.miejscowosc = miejscowosc;
    }
}
