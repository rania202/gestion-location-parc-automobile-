package controleurs;

import java.io.IOException;

import application.Main;
import bindings.BindClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleClient {

    @FXML
    private TextField adr;

    @FXML
    private Button btAjouter;

    @FXML
    private Button btAnnuler;

    @FXML
    private TextField cin;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;
    
    private BindClient bc= new BindClient();
    
   

    @FXML
    void ajouterClient(ActionEvent event)  {
    	boolean b= bc.ajouter();
    	if(b) {
    		Alert alt= new Alert(AlertType.CONFIRMATION);
    		alt.setContentText("Client ajouté");
    		alt.showAndWait();
    	}
    	else {
    		Alert alt= new Alert(AlertType.ERROR);
    		alt.setContentText("Erreur lors de l'ajout");
    		alt.showAndWait();
    	}
    	bc.annuler();

    }
    @FXML
    void rechercher(ActionEvent event) {
    	if(!bc.chercher()) {
    	Alert alt= new Alert(AlertType.ERROR);
		alt.setContentText("Client introuvable");
		alt.showAndWait();
		}
    	else btAjouter.setDisable(true);
    }

    @FXML
    void annuler(ActionEvent event) {
          bc.annuler();
          btAjouter.setDisable(false);
    }
    @FXML
    void reserver(ActionEvent event) throws IOException {
    	ControleReser.setBcInitial(bc);
    	System.out.println(bc.getNom());
    	BorderPane root= (BorderPane) Main.fPrincipale.getScene().getRoot();
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("../interfaces/ReservationIHM.fxml"));
		Pane subRoot=loader.load();
		root.setCenter(subRoot);
		
		
		Main.fPrincipale.show();
		
		

    }
    @FXML
    void modifier(ActionEvent event) {
    	if (bc.modifier()) {
    		Alert alt= new Alert(AlertType.CONFIRMATION);
    		alt.setContentText("client modifié");
    		alt.showAndWait();
    		
    	}
    	else {
    		Alert alt= new Alert(AlertType.ERROR);
    		alt.setContentText("Erreur lors de la modification");
    		alt.showAndWait();
    	}
    	bc.annuler();

    }

}
