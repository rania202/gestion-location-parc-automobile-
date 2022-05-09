package controleurs;

import java.io.IOException;
import java.time.LocalDate;

import accesBD.ClientDAO;
import application.Main;
import bindings.BindClient;
import bindings.BindReser;
import bindings.BindVoiture;
import entites.Client;
import entites.Voiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.Alert.AlertType;

public class ControleReser{

    @FXML
    private TextField adresse;

    @FXML
    private Button ajout;

    @FXML
    private Button annule;
    
    @FXML
    private Button btRetour;

    @FXML
    private TextField cin;

    @FXML
    private TextField code;

    @FXML
    private DatePicker date_debut;

    @FXML
    private DatePicker date_reservation;

    @FXML
    private ComboBox<Voiture> immat;

    @FXML
    private TextField marque;

    @FXML
    private TextField nbrJ;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField prix;

    @FXML
    private TextField tel;

    @FXML
    private TextField type;
    
   
    
 private BindReser rv= new BindReser();
 private BindVoiture im= new BindVoiture();
 private BindClient bc= new BindClient();
 private LocalDate dateLimite=LocalDate.now();
 private static BindClient bcInitial;
   
    	
    	
    	
    	
    	immat.setConverter(v);
    	immat.getSelectionModel().select(0);

    	
    	cin.textProperty().bindBidirectional(rv.getClient().cinProperty());
    	nom.textProperty().bindBidirectional(rv.getClient().nomProperty());
    	prenom.textProperty().bindBidirectional(rv.getClient().prenomProperty());
    	tel.textProperty().bindBidirectional(rv.getClient().telProperty());
    	adresse.textProperty().bindBidirectional(rv.getClient().adrProperty());
    	
    	
    	marque.textProperty().bindBidirectional(im.modeleProperty());
    	type.textProperty().bindBidirectional(im.typeProperty());
    	prix.textProperty().bindBidirectional(im.prixProperty());
    	
    	code.textProperty().bindBidirectional(rv.codeProperty(),new NumberStringConverter());
    	date_reservation.valueProperty().bindBidirectional(rv.date_RProperty());
    	date_debut.valueProperty().bindBidirectional(rv.date_DProperty());
    	nbrJ.textProperty().bindBidirectional(rv.nbrJProperty(), new NumberStringConverter());
    	//ObservableList<BindVoiture> Voitures= FXCollections.observableArrayList();
    
    	
    

    
    @FXML
    void activerDateDebut(ActionEvent event) {
    	System.out.println("hello");
    	date_debut.setDisable(false); 
    	dateLimite= date_reservation.getValue();
    	date_debut.setDayCellFactory(getCellFactory());
    	

    }
    @FXML
    void desactiverDateDebut(MouseEvent event) {
    	date_debut.getEditor().clear();
    	date_debut.setDisable(true);
    	
    }

    @FXML
    void annuler_reservation(ActionEvent event) {

    }
    
    
    
    @FXML
    void afficheVoit(ActionEvent event) {
    	im.toBindVoiture(immat.valueProperty().get());
    	
    	
    	System.out.println("hhhh");
    	System.out.println(im.getModele());
    
    }
    @FXML
    void afficheClient(ActionEvent event) {
    	System.out.println("gggggg");
    rv.ClientProperty().get().chercher();
    bc= rv.getClient();
    	

    }
    

   


	


	public static BindClient getBcInitial() {
		return bcInitial;
	}


	public static void setBcInitial(BindClient bcInitial) {
		ControleReser.bcInitial = bcInitial;
	}


	@FXML
    void nouvelle_reservation(ActionEvent event) {
    	        rv.voitureProperty().set(im);
    	    	
    	    	if(rv.ajouter()) {
    	    		Alert alt= new Alert(AlertType.CONFIRMATION);
    	    		double p = Double.parseDouble (rv.getVoiture().getPrix());
    	    		int n = rv.getNbrJ();
    	    		alt.setContentText("Réservation ajoutée. le prix de la réservation est "+(p*n)+" DT");
    	    		alt.showAndWait();
    	    	}
    	    	else {
    	    		Alert alt= new Alert(AlertType.ERROR);
    	    		alt.setContentText("Erreur lors de l'ajout de la réservation");
    	    		alt.showAndWait();
    	    	}
    	    
    	    	rv.annuler();

    	    }
    
    

    Callback<DatePicker, DateCell> getCellFactory() {
        final Callback<DatePicker, DateCell> cellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(dateLimite)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return cellFactory;
    }
    
    @FXML
    void retourPrincipale(ActionEvent event) throws IOException {
    	BorderPane root= (BorderPane) Main.fPrincipale.getScene().getRoot();
		Pane subRoot=FXMLLoader.load(getClass().getResource("../interfaces/Boutons.fxml"));
		
		root.setCenter(subRoot);
		Main.fPrincipale.show();;

    }
    
    @FXML
    void actionDateDebut(ActionEvent event) {
    	rv.getVoitures().clear();
    	immat.setItems(rv.getVoitures());
    	immat.setDisable(false);
    	im.toBindVoiture(immat.valueProperty().get());

    }



    }


