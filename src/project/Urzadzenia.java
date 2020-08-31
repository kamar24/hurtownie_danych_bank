package project;

public class Urzadzenia {
    private String miasto;
    private String rodzaj_operacji;
    private int sum;

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getRodzaj_operacji() {
        return rodzaj_operacji;
    }

    public void setRodzaj_operacji(String rodzaj_operacji) {
        this.rodzaj_operacji = rodzaj_operacji;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Urzadzenia(String miasto, String rodzaj_operacji, int sum) {
        this.miasto = miasto;
        this.rodzaj_operacji = rodzaj_operacji;
        this.sum = sum;
    }
}
