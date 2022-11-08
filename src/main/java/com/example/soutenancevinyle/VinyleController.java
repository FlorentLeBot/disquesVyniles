package com.example.soutenancevinyle;

import com.mysql.cj.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Pattern;

public class VinyleController {

    // Les attributs de la classe VinyleController
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem sendMail;
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
    LeBonCoin sbc = new LeBonCoin(); // leboncoin.fr
    Discogs sdo = new Discogs(); // discogs.com
    Mesvinyles smv = new Mesvinyles(); // mesvinyles.fr
    Vinylcorner svc = new Vinylcorner(); // vinylcorner.FR
    // Fnac sfnac = new Fnac(); // fnac.com

    Email email = new Email();


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
    // ouverture de la popup d'aide
    public void onPopupHelpClick() throws IOException {
        PopupScene();
    }
    public void onPopupSendMailClick() throws IOException {
        PopupSceneMail();
    }

    // popup : Aide
    public void PopupScene() throws IOException{
        Stage popupHelpWindow = new Stage();
        popupHelpWindow.initModality(Modality.APPLICATION_MODAL);
        popupHelpWindow.setTitle("Mode d'emploi");
        //TODO faire un document texte et lire le fichier
        TextArea textArea1 = new TextArea("Un peu d'aide...");
        Button button1 = new Button("Fermer");
        button1.setOnAction(e -> popupHelpWindow.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(textArea1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        popupHelpWindow.setScene(scene1);
        popupHelpWindow.showAndWait();
    }

    // popup : Envoi Couriel
    public void PopupSceneMail() throws IOException{
        //String file = "";

        Stage popupMailWindow = new Stage();
        popupMailWindow.initModality(Modality.APPLICATION_MODAL);
        popupMailWindow.setTitle("Envoi courriel");
        Text text1 = new Text("Saisie du courriel");
        Label label1 = new Label("Veuillez saisir l'email de l'expéditeur.");
        TextField textField1 = new TextField();
        Button button1 = new Button("Envoyer");
        Button button2 = new Button("Annuler");
        button1.setOnAction(e -> email.sendMail(textField1.getText()));
        //button1.setOnAction(e -> popupMailWindow.close());
        button2.setOnAction(e -> popupMailWindow.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text1, label1, textField1, button1, button2);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        popupMailWindow.setScene(scene1);
        popupMailWindow.showAndWait();
    }

    // page : Paramètres de la base de données
    public void paraDB() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VinyleApplication.class.getResource("db-para-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Paramètre de base de données");
        stage.setScene(scene);
        stage.show();
    }

    // enregistrer un fichier
    public void saveFile() throws FileNotFoundException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll( // filtrer les extensions
        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null){
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(result.getText());
            writer.close();
        }
    }

    // affichage du resultat de la recherche
    public void displaySearch() throws Exception {

        String searchTitle = title.getText() + " " + combobox.getValue();

        if(leboncoin.isSelected()){
            int min = Integer.parseInt(minprice.getText());
            int max = Integer.parseInt(maxprice.getText());
            if(minprice.getText().equals("") || maxprice.getText().equals("")){
                min = 0;
                max = Integer.MAX_VALUE;
            }
            String res = sbc.searchBC(searchTitle, min, max); // affiche le resultat
            result.setText(String.valueOf(res));

        } else if (mesvinyles.isSelected()) {
            String res = smv.searchMV(searchTitle); // affiche le resultat
            result.setText(String.valueOf(res));

        } else if (vinylcorner.isSelected()) {
            String res = svc.searchVC(searchTitle); // affiche le resultat
            result.setText(String.valueOf(res));

        } else if(title.getText().equals("")){
            String res = "Vous devez remplir le champ titre";
            result.setText(String.valueOf(res));
        }
    }

}