package controleurs;

import java.io.IOException;

import application.Main;
import bindings.BindVoiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ControleVoiture {

    @FXML
    private Button ajoutV;

    @FXML
    private Button annuleV;

    @FXML
    private TextField immat;

    @FXML
    private TextField modele;

    @FXML
    private TextField prix;
    @FXML
    private Button btRetour;

    @FXML
    private TextField type;
    private BindVoiture bc= new BindVoiture();
 

    @FXML
    void ajouterV(ActionEvent event) {
    	boolean b= bc.ajouter();
    	if(b) {
    		Alert alt= new Alert(AlertType.CONFIRMATION);
    		alt.setContentText("Voiture ajouté");
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
    void annulerV(ActionEvent event) {
          bc.annuler();
          ajoutV.setDisable(false);
    }
    @FXML
    void recherche(ActionEvent event) {
    	if(!bc.chercher()) {
        	Alert alt= new Alert(AlertType.ERROR);
    		alt.setContentText("Voiture introuvable");
    		alt.showAndWait();}
    	else ajoutV.setDisable(true);

    }
    @FXML
    void modifier(ActionEvent event) {
    	boolean b =bc.modifier();
    	if (b==true) {
    		Alert alt= new Alert(AlertType.CONFIRMATION);
    		alt.setContentText("Voiture modifiée");
    		alt.showAndWait();
    		
    	}
    	else {
    		Alert alt= new Alert(AlertType.ERROR);
    		alt.setContentText("Erreur lors de la modification");
    		alt.showAndWait();
    	}
    	bc.annuler();

    }

    @FXML
    void supprimer(ActionEvent event) {
    	if (!bc.supprimer()) {
    		Alert alt= new Alert(AlertType.ERROR);
    		alt.setContentText("Erreur lors de la suppression");
    		alt.showAndWait();
    	}
    	bc.annuler();


    }
    @FXML
    void retourPrincipale(ActionEvent event) throws IOException {
    	BorderPane root= (BorderPane) Main.fPrincipale.getScene().getRoot();
		Pane subRoot=FXMLLoader.load(getClass().getResource("../interfaces/Boutons.fxml"));
		
		root.setCenter(subRoot);
		Main.fPrincipale.show();;
    }


}
