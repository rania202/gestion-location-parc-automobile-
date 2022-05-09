package bindings;

import java.time.LocalDate;
import java.util.List;

import accesBD.ReservationDAO;
import accesBD.VoitureDAO;
import bindings.BindClient;
import entites.Reservation;
import entites.Voiture;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BindReser {
	
		ObjectProperty<BindClient> Client = new SimpleObjectProperty<>();

		ObjectProperty<BindVoiture> voiture = new SimpleObjectProperty<>();
		IntegerProperty code= new SimpleIntegerProperty();
		ObjectProperty<LocalDate> date_R= new SimpleObjectProperty<>();
		IntegerProperty nbrJ= new SimpleIntegerProperty();
		ObjectProperty<LocalDate> date_D= new SimpleObjectProperty<>();
		ObservableList<Voiture> voitures = FXCollections.observableArrayList();
		
		
		public BindReser() {
			Client.set(new BindClient());
			ReservationDAO rdao= new ReservationDAO();	   	     
			this.codeProperty().set(rdao.getNextCode()+1);			
			}
			
		
		public ObservableList<Voiture> getVoitures() {
			VoitureDAO dao= new VoitureDAO();
			List<entites.Voiture> voits=dao.getDisponible(getDate_D(), getNbrJ());
			voitures.addAll(voits);
			return voitures;
		}

		public Reservation toReservation() {
			BindClient bc = this.getClient();
			BindVoiture bv = this.getVoiture();			
			LocalDate dr = (LocalDate)this.getDate_R();
			LocalDate db = (LocalDate)this.getDate_D();
			System.out.println(bc);
			System.out.println(bv);
			return new Reservation(this.getCode(),bc.toClient(),bv.toVoiture(),dr,db,this.getNbrJ());
		}
		public void toBindReservation(Reservation r) {
			this.code.set(r.getCodereserv());
			this.Client.get().toBindClient(r.getClient());
			this.voiture.get().toBindVoiture(r.getVoiture());		
			this.date_R.set(r.getDateReservation());
			this.date_D.set(r.getDateDebut());
			this.nbrJ.set(r.getNbreJours());
		}
		public boolean ajouter() {
			ReservationDAO dao= new ReservationDAO();
			return dao.addReservation(this.toReservation());
		}

		


		public final ObjectProperty<BindClient> ClientProperty() {
			return this.Client;
		}
		

		public final BindClient getClient() {
			return this.ClientProperty().get();
		}
		

		public final void setClient(final BindClient Client) {
			this.ClientProperty().set(Client);
		}
		

		public final ObjectProperty<BindVoiture> voitureProperty() {
			return this.voiture;
		}
		

		public final BindVoiture getVoiture() {
			return this.voitureProperty().get();
		}
		

		public final void setVoiture(final BindVoiture voiture) {
			this.voitureProperty().set(voiture);
		}
		

		public final IntegerProperty codeProperty() {
			return this.code;
		}
		

		public final int getCode() {
			return this.codeProperty().get();
		}
		

		public final void setCode(final int code) {
			this.codeProperty().set(code);
		}
		

		public final ObjectProperty<LocalDate> date_RProperty() {
			return this.date_R;
		}
		

		public final LocalDate getDate_R() {
			return this.date_RProperty().get();
		}
		

		public final void setDate_R(final LocalDate date_R) {
			this.date_RProperty().set(date_R);
		}
		

		public final IntegerProperty nbrJProperty() {
			return this.nbrJ;
		}
		

		public final int getNbrJ() {
			return this.nbrJProperty().get();
		}
		

		public final void setNbrJ(final int nbrJ) {
			this.nbrJProperty().set(nbrJ);
		}
		

		public final ObjectProperty<LocalDate> date_DProperty() {
			return this.date_D;
		}
		

		public final LocalDate getDate_D() {
			return this.date_DProperty().get();
		}
		

		public final void setDate_D(final LocalDate date_D) {
			this.date_DProperty().set(date_D);
		}


		public void annuler() {
			getClient().annuler();
			getVoiture().annuler();
			nbrJ.set(0);
			date_D.set(LocalDate.now());
			date_R.set(LocalDate.now());
			
		}
		
		
		
		
	}

