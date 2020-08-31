package project;

import com.sun.deploy.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import project.*;

import java.awt.*;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    JDBC cspie = new JDBC();
    private ObservableList<Klienci> selectklienci;
    private ObservableList<Operacje> selectoperacje;
    private ObservableList<Urzadzenia> selecturzadzenia;
    private ObservableList<Przelewy> selectprzelewy;
    private ObservableList<Statystyki> selectprzelewystat;
    @FXML
    private TableView<Klienci> klienci;
    @FXML
    private TableColumn<Klienci, String> imie_klienta;
    @FXML
    private TableColumn<Klienci, String> nazwisko_klienta;
    @FXML
    private TableColumn<Klienci, String> nr_konta;
    @FXML
    private TableColumn<Klienci, String> pesel_klienta;
    @FXML
    private TableColumn<Klienci, String> kod_pocztowy;
    @FXML
    private TableColumn<Klienci, String> ulica;
    @FXML
    private TableColumn<Klienci, String> nr_lokalu;
    @FXML
    private TableColumn<Klienci, String> miejscowosc;
    @FXML
    private TableView<Operacje> operacje;
    @FXML
    private TableColumn<Operacje, String> nr_konta_operacje;
    @FXML
    private TableColumn<Operacje, String> rodzaj_operacji;
    @FXML
    private TableColumn<Operacje, String> miasto_operacje;
    @FXML
    private TableColumn<Operacje, String> ulica_operacje;
    @FXML
    private TableColumn<Operacje, Integer> kwota_operacji;
    @FXML
    private TableColumn<Operacje, String> data_operacji;
    @FXML
    private TableView<Urzadzenia> table_bilans_operacji;
    @FXML
    private TableColumn<Urzadzenia, String> sum_miasto;
    @FXML
    private TableColumn<Urzadzenia, String> sum_rodzaj_operacji;
    @FXML
    private TableColumn<Urzadzenia, String> sum_suma_operacji;
    @FXML
    private ChoiceBox choice_miasto;
    @FXML
    private Button sum_miasto_wykonaj;
    @FXML
    private Button button_przelewy;
    @FXML
    private TableView<Przelewy> table_view_przelewy;
    @FXML
    private TableColumn<Przelewy,Integer> rok_przelewy;
    @FXML
    private TableColumn<Przelewy,Integer> miesiac_przelewy;
    @FXML
    private TableColumn<Przelewy,Integer>kwota_przelewy;
    @FXML
    private ChoiceBox choice_przelewy_miesiac;
    @FXML
    private ChoiceBox choice_statystyka;
    @FXML
    private TableView<Statystyki> tv_statystyka;
    @FXML
    private TableColumn<Statystyki,String> nr_konta_statystyka;
    @FXML
    private TableColumn<Statystyki,String> imie_statystyka;
    @FXML
    private TableColumn<Statystyki,String> nazwisko_statystyka;
    @FXML
    private TableColumn<Statystyki,Integer> suma_statystyka;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice_przelewy_miesiac.setItems(FXCollections.observableArrayList("%",1,2,3,4,5,6,7,8,9,10,11,12));
        choice_miasto.setItems(FXCollections.observableArrayList("%", "Wplata", "Wyplata"));
        choice_statystyka.setItems(FXCollections.observableArrayList("Przelewy","Wpłaty","Wypłaty"));
        try {
            selectklienci = FXCollections.observableArrayList(cspie.select_klienci());
            klienci.setItems(selectklienci);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        imie_klienta.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazwisko_klienta.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        nr_konta.setCellValueFactory(new PropertyValueFactory<>("nr_konta"));
        pesel_klienta.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        kod_pocztowy.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy"));
        ulica.setCellValueFactory(new PropertyValueFactory<>("ulica"));
        nr_lokalu.setCellValueFactory(new PropertyValueFactory<>("nr_lokalu"));
        miejscowosc.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));


        //select operacje
        try {
            selectoperacje = FXCollections.observableArrayList(cspie.select_operacje());
            operacje.setItems(selectoperacje);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nr_konta_operacje.setCellValueFactory(new PropertyValueFactory<>("nr_konta"));
        rodzaj_operacji.setCellValueFactory(new PropertyValueFactory<>("rodzaj_operacji"));
        miasto_operacje.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        ulica_operacje.setCellValueFactory(new PropertyValueFactory<>("ulica"));
        kwota_operacji.setCellValueFactory(new PropertyValueFactory<>("kwota"));
        data_operacji.setCellValueFactory(new PropertyValueFactory<>("data_operacji"));
   //bilans rodzaj start
        try {
            selecturzadzenia = FXCollections.observableArrayList(cspie.bilans_rodzaj_start());
            table_bilans_operacji.setItems(selecturzadzenia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sum_miasto.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        sum_rodzaj_operacji.setCellValueFactory(new PropertyValueFactory<>("rodzaj_operacji"));
        sum_suma_operacji.setCellValueFactory(new PropertyValueFactory<>("sum"));


    //przelewy  start
        try {
            selectprzelewy = FXCollections.observableArrayList(cspie.przelewy_start());
        table_view_przelewy.setItems(selectprzelewy);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        rok_przelewy.setCellValueFactory(new PropertyValueFactory<>("rok"));
        miesiac_przelewy.setCellValueFactory(new PropertyValueFactory<>("miesiac"));
        kwota_przelewy.setCellValueFactory(new PropertyValueFactory<>("kwota"));

}

        //bilans rodzaj
        @FXML
        void bilans_button(ActionEvent event){
            String operacja_wybor;
            operacja_wybor = choice_miasto.getSelectionModel().getSelectedItem().toString();

        String cudzyslow = "'";
        String operacja_query = cudzyslow + operacja_wybor + cudzyslow;

        try {
            selecturzadzenia = FXCollections.observableArrayList(cspie.bilans_rodzaj(operacja_query));
            table_bilans_operacji.setItems(selecturzadzenia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sum_miasto.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        sum_rodzaj_operacji.setCellValueFactory(new PropertyValueFactory<>("rodzaj_operacji"));
        sum_suma_operacji.setCellValueFactory(new PropertyValueFactory<>("sum"));
    }
    //przelewy
    @FXML
    void p_button(ActionEvent event){
        String przelewy_wybor;
        przelewy_wybor=choice_przelewy_miesiac.getSelectionModel().getSelectedItem().toString();


        if(przelewy_wybor=="%"){

            //przelewy  start
            try {
                selectprzelewy = FXCollections.observableArrayList(cspie.przelewy_start());
                table_view_przelewy.setItems(selectprzelewy);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            rok_przelewy.setCellValueFactory(new PropertyValueFactory<>("rok"));
            miesiac_przelewy.setCellValueFactory(new PropertyValueFactory<>("miesiac"));
            kwota_przelewy.setCellValueFactory(new PropertyValueFactory<>("kwota"));
        }
        else
            try {
                selectprzelewy = FXCollections.observableArrayList(cspie.przelewy_query(przelewy_wybor));
                table_view_przelewy.setItems(selectprzelewy);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        rok_przelewy.setCellValueFactory(new PropertyValueFactory<>("rok"));
        miesiac_przelewy.setCellValueFactory(new PropertyValueFactory<>("miesiac"));
        kwota_przelewy.setCellValueFactory(new PropertyValueFactory<>("kwota"));

    }
    @FXML
    void s_button(ActionEvent event) {
        String statystyka_wybor;
        statystyka_wybor = choice_statystyka.getSelectionModel().getSelectedItem().toString();

        if(statystyka_wybor=="Przelewy") {


            try {
                selectprzelewystat = FXCollections.observableArrayList(cspie.select_il_przelewow());
                tv_statystyka.setItems(selectprzelewystat);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nr_konta_statystyka.setCellValueFactory(new PropertyValueFactory<>("nr_konta"));
            imie_statystyka.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwisko_statystyka.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            suma_statystyka.setCellValueFactory(new PropertyValueFactory<>("suma"));
        }else
        {if(statystyka_wybor=="Wpłaty"){

            try {
                selectprzelewystat = FXCollections.observableArrayList(cspie.select_il_wplat());
                tv_statystyka.setItems(selectprzelewystat);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nr_konta_statystyka.setCellValueFactory(new PropertyValueFactory<>("nr_konta"));
            imie_statystyka.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwisko_statystyka.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            suma_statystyka.setCellValueFactory(new PropertyValueFactory<>("suma"));
        }
        else{
            try {
                selectprzelewystat = FXCollections.observableArrayList(cspie.select_il_wyplat());
                tv_statystyka.setItems(selectprzelewystat);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nr_konta_statystyka.setCellValueFactory(new PropertyValueFactory<>("nr_konta"));
            imie_statystyka.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwisko_statystyka.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            suma_statystyka.setCellValueFactory(new PropertyValueFactory<>("suma"));
        }}


    }}
