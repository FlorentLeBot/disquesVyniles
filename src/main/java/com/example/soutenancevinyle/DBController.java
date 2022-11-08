package com.example.soutenancevinyle;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBController {

    @FXML
    private TextField serverName;
    @FXML
    private TextField dbName;
    @FXML
    private TextField dbPort;
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    String ser = serverName.getText();
    String dbname = serverName.getText();
    String port = dbPort.getText();
    String log = login.getText();
    String pass = password.getText();

    Connection conn;


    public Connection dbConn(){
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+ser+":"+port+"/"+dbname+", "+log+", "+pass+"")) {
                    //"jdbc:mysql://127.0.0.1:3306/scraping", "root", "")) {
                if (conn != null) {
                    System.out.println("Connexion réussie !");
                } else {
                    System.out.println("Problème de connexion !");
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
    }

    public boolean insert(Scraping s) throws SQLException {

        boolean res = false;
        String sql = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/scraping", "root", "");

            sql = "INSERT INTO scraping(titre, description, prix, id_genre, date) VALUES(?,?,?,?,?)";

            /*
            sql = "INSERT INTO rechercher(titre, description, prix, id_genre, annee) " +
                    "VALUES('" + title + "','" + description + "','" + price + "', '" + Integer.parseInt(id_genre) + "', '" + date + "')";
            */

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            res = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public void onClickClosePara(){

    }
}
