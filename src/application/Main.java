package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage fPrincipale;
	
	@Override
	public void start(Stage primaryStage) {
		fPrincipale=primaryStage;
		
		try {
			
			BorderPane root = FXMLLoader.load(getClass().getResource("../interfaces/PrincipaleIHM.fxml"));
			Pane subRoot=FXMLLoader.load(getClass().getResource("../interfaces/Boutons.fxml"));
			fPrincipale.setTitle("Application de gestion de réservations");
			fPrincipale.setX(10);
			fPrincipale.setY(10);
			Scene scene = new Scene(root,1000,900);
			root.setCenter(subRoot);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
