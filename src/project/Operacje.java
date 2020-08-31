package project;

public class Operacje {

    private String nr_konta;
    private String rodzaj_operacji;
    private String miasto;
    private String ulica;
    private int kwota;
    private String data_operacji;


    public String getNr_konta() {
        return nr_konta;
    }

    public void setNr_konta(String nr_konta) {
        this.nr_konta = nr_konta;
    }

    public String getRodzaj_operacji() {
        return rodzaj_operacji;
    }

    public void setRodzaj_operacji(String rodzaj_operacji) {
        this.rodzaj_operacji = rodzaj_operacji;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getKwota() {
        return kwota;
    }

    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    public String getData_operacji() {
        return data_operacji;
    }

    public void setData_operacji(String data_operacji) {
        this.data_operacji = data_operacji;
    }

    public Operacje(String nr_konta, String rodzaj_operacji, String miasto, String ulica, int kwota, String data_operacji) {
        this.nr_konta = nr_konta;
        this.rodzaj_operacji = rodzaj_operacji;
        this.miasto = miasto;
        this.ulica = ulica;
        this.kwota = kwota;
        this.data_operacji = data_operacji;
    }
}