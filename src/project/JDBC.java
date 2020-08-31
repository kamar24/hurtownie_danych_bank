package project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import project.*;

public class JDBC {

    public Connection polaczenie() {


        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/hd", "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

    //Klienci
    public List<Klienci> select_klienci() throws SQLException {

        List<Klienci> arrayList = new ArrayList();
        Statement stmt = null;
        String query = "Select * from klienci";


        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String nr_konta = rs.getString("nr_konta");
                String pesel = rs.getString("pesel");
                String kod_pocztowy = rs.getString("kod_pocztowy");
                String ulica = rs.getString("ulica");
                String nr_lokalu = rs.getString("nr_lokalu");
                String miejscowosc = rs.getString("miejscowosc");
                arrayList.add(new Klienci(imie,nazwisko,nr_konta,pesel,kod_pocztowy,ulica,nr_lokalu,miejscowosc));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //operacje
    public List<Operacje> select_operacje() throws SQLException {

        List<Operacje> arrayList = new ArrayList();
        Statement stmt = null;
        String query = "select kl.nr_konta as nr_konta_klienta,o.rodzaj_operacji,urz.miasto,urz.ulica,o.kwota,o.data_operacji from\n" +
                "klienci kl, operacje o, urzadzenia urz where kl.id_klienta=o.id_klienta and\n" +
                "o.id_urzadzenia=urz.id_urzadzenia";


        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nr_konta_klienta = rs.getString("nr_konta_klienta");
                String rodzaj_operacji = rs.getString("rodzaj_operacji");
                String urz_miasto = rs.getString("miasto");
                String urz_ulica = rs.getString("ulica");
                int kwota = rs.getInt("kwota");
                String data = rs.getString("data_operacji");
                arrayList.add(new Operacje(nr_konta_klienta,rodzaj_operacji,urz_miasto,urz_ulica,kwota,data));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //bilans rodzaj start
    public List<Urzadzenia> bilans_rodzaj_start( ) throws SQLException {

        List<Urzadzenia> arrayList = new ArrayList();
        Statement stmt = null;
        String query = "select urz.miasto, o.rodzaj_operacji,SUM(o.kwota) from urzadzenia urz, operacje o \n" +
                "where urz.id_urzadzenia=o.id_urzadzenia  \n" +
                "  group by grouping sets ((urz.miasto,o.rodzaj_operacji),(o.rodzaj_operacji)) order by rodzaj_operacji ";

        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String miasto = rs.getString("miasto");
                String rodzaj_operacji = rs.getString("rodzaj_operacji");
                int suma = rs.getInt("sum");

                arrayList.add(new Urzadzenia(miasto,rodzaj_operacji,suma));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //bilans rodzaj query
    public List<Urzadzenia> bilans_rodzaj(String rodzaj_ost ) throws SQLException {

        List<Urzadzenia> arrayList = new ArrayList();
        Statement stmt = null;
        String query = "select urz.miasto, o.rodzaj_operacji,SUM(o.kwota) from urzadzenia urz, operacje o \n" +
        "where urz.id_urzadzenia=o.id_urzadzenia and rodzaj_operacji like \n" +rodzaj_ost+
      "  group by grouping sets ((urz.miasto,o.rodzaj_operacji),(o.rodzaj_operacji)) order by rodzaj_operacji ";

        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String miasto = rs.getString("miasto");
                String rodzaj_operacji = rs.getString("rodzaj_operacji");
                int suma = rs.getInt("sum");

                arrayList.add(new Urzadzenia(miasto,rodzaj_operacji,suma));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //przelewy_start
    public List<Przelewy> przelewy_start( ) throws SQLException {

        List<Przelewy> arrayList = new ArrayList();
        Statement stmt = null;
        String query ="Select  extract(Year from data_przelewu) as rok, extract(Month from data_przelewu) as miesiac, sum(kwota) from przelewy \n" +
                "group by grouping sets((extract(Year from data_przelewu),extract(Month from data_przelewu)),\n" +
                "extract(Month from data_przelewu))\n" +
                "order by 1,2";
        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int rok = rs.getInt("rok");
                int miesiac = rs.getInt("miesiac");
                int kwota = rs.getInt("sum");

                arrayList.add(new Przelewy(rok,miesiac,kwota));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //przelewy_start
    public List<Przelewy> przelewy_query( String miesiac_wybor) throws SQLException {

        List<Przelewy> arrayList = new ArrayList();
        Statement stmt = null;
        String query ="Select  extract(Year from data_przelewu) as rok, extract(Month from data_przelewu) as miesiac, sum(kwota) from przelewy \n" +
                "where extract(Month from data_przelewu)=\n" + miesiac_wybor+
                "group by grouping sets((extract(Year from data_przelewu),extract(Month from data_przelewu)),\n" +
                "extract(Month from data_przelewu))\n" +
                "order by 1,2";
        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int rok = rs.getInt("rok");
                int miesiac = rs.getInt("miesiac");
                int kwota = rs.getInt("sum");

                arrayList.add(new Przelewy(rok,miesiac,kwota));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    //statystyki przelewy
    public List<Statystyki> select_il_przelewow() throws SQLException {

        List<Statystyki> arrayList = new ArrayList();
        Statement stmt = null;
        String query ="select kl.nr_konta, kl.imie,kl.nazwisko, Count(pr.id_nadawcy) as suma\n" +
                "from klienci kl, przelewy pr where kl.id_klienta=pr.id_nadawcy\n" +
                "group by id_klienta order by suma desc ;";
        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nr_konta = rs.getString("nr_konta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                int suma = rs.getInt("suma");

                arrayList.add(new Statystyki(nr_konta,imie,nazwisko,suma));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public List<Statystyki> select_il_wplat() throws SQLException {

        List<Statystyki> arrayList = new ArrayList();
        Statement stmt = null;
        String query ="select kl.nr_konta, kl.imie,kl.nazwisko, Count(op.id_operacji) as suma\n" +
                "from klienci kl, operacje op where kl.id_klienta=op.id_klienta and op.rodzaj_operacji='Wplata'\n" +
                "group by kl.id_klienta order by suma desc ;";
        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nr_konta = rs.getString("nr_konta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                int suma = rs.getInt("suma");

                arrayList.add(new Statystyki(nr_konta,imie,nazwisko,suma));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    public List<Statystyki> select_il_wyplat() throws SQLException {

        List<Statystyki> arrayList = new ArrayList();
        Statement stmt = null;
        String query ="select kl.nr_konta, kl.imie,kl.nazwisko, Count(op.id_operacji) as suma\n" +
                "from klienci kl, operacje op where kl.id_klienta=op.id_klienta and op.rodzaj_operacji='Wyplata'\n" +
                "group by kl.id_klienta order by suma desc ;";
        try {
            Connection conn = this.polaczenie();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nr_konta = rs.getString("nr_konta");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                int suma = rs.getInt("suma");

                arrayList.add(new Statystyki(nr_konta,imie,nazwisko,suma));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }
    }

