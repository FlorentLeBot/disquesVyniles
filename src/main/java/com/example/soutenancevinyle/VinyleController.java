package com.example.soutenancevinyle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VinyleController {

    // Les attributs de la classe VinyleController
    @FXML
    private MenuItem close;
    @FXML
    private TextField minprice;
    @FXML
    private TextField maxprice;
    @FXML
    private TextField title;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private CheckBox discogs;
    @FXML
    private CheckBox fnac;
    @FXML
    private CheckBox vinylcorner;
    @FXML
    private CheckBox leboncoin;
    @FXML
    private CheckBox mesvinyles;
    @FXML
    private CheckBox culturefactory;
    @FXML
    private TextArea result;
    ScrolingBC sbc = new ScrolingBC();

    @FXML

    protected void onCloseClick(){
        Platform.exit();
    }

    @FXML
    protected void onClearClick(){
        minprice.setText("");
        maxprice.setText("");
        title.setText("");
        date.setValue(null);
        combobox.setValue("Selectionner un genre");
        combobox.setId("slt");
        discogs.setSelected(false);
        fnac.setSelected(false);
        vinylcorner.setSelected(false);
        leboncoin.setSelected(false);
        mesvinyles.setSelected(false);
        culturefactory.setSelected(false);
    }

    public void onPopupHelpClick() throws IOException {
        PopupScene();
    }

    public void afficher() throws Exception {
        String res = sbc.search(title.getText()); // affiche le resultat
        result.setText(res);
    }

    public void PopupScene() throws IOException{
        Stage popupHelpWindow = new Stage();
        popupHelpWindow.initModality(Modality.APPLICATION_MODAL);
        popupHelpWindow.setTitle("Mode d'emploi");
        //TODO faire un document texte et lire le fichier
        TextArea textArea1 = new TextArea("Un peu d'aidequos,");
        Button button1 = new Button("Fermer");
        button1.setOnAction(e -> popupHelpWindow.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(textArea1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        popupHelpWindow.setScene(scene1);
        popupHelpWindow.showAndWait();
    }

    public void PopupSceneSendMess() throws IOException{
        Stage popupHelpWindow = new Stage();
        popupHelpWindow.initModality(Modality.APPLICATION_MODAL);
        popupHelpWindow.setTitle("Mode d'emploi");
        //TODO faire un document texte et lire le fichier
        TextArea textArea1 = new TextArea("Un peu d'aidequos,");
        Button button1 = new Button("Fermer");
        button1.setOnAction(e -> popupHelpWindow.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(textArea1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        popupHelpWindow.setScene(scene1);
        popupHelpWindow.showAndWait();
    }


}