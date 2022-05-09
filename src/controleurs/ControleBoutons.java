package controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControleBoutons {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clients;

    @FXML
    private Button reservations;

    @FXML
    private Button voitures;

    @FXML
    void getClients(ActionEvent event) throws IOException {
    	Stage s=Main.fPrincipale;
    	BorderPane root = FXMLLoader.load(getClass().getResource("../interfaces/ClientIHM.fxml"));
		Scene scene = new Scene(root,1000,700);
		s.setScene(scene);
		s.show();

    }

    @FXML
    void getReservations(ActionEvent event) throws IOException {
    	Stage s=Main.fPrincipale;
    	BorderPane root = FXMLLoader.load(getClass().getResource("../interfaces/ReservationIHM.fxml"));
		Scene scene = new Scene(root,1000,700);
		s.setScene(scene);
		s.show();

    }

    @FXML
    void getVoitures(ActionEvent event) throws IOException {
    	Stage s=Main.fPrincipale;
    	BorderPane root = FXMLLoader.load(getClass().getResource("../interfaces/VoitureIHM.fxml"));
		Scene scene = new Scene(root,1000,700);
		s.setScene(scene);
		s.show();

    }

  

}
