package bindings;

import accesBD.ClientDAO;
import entites.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BindClient {
	StringProperty nom= new SimpleStringProperty();
	StringProperty prenom= new SimpleStringProperty();
	StringProperty cin= new SimpleStringProperty();
	StringProperty tel= new SimpleStringProperty();
	StringProperty adr= new SimpleStringProperty();
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	
	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	
	public final StringProperty cinProperty() {
		return this.cin;
	}
	
	public final String getCin() {
		return this.cinProperty().get();
	}
	
	public final void setCin(final String cin) {
		this.cinProperty().set(cin);
	}
	
	public final StringProperty telProperty() {
		return this.tel;
	}
	
	public final String getTel() {
		return this.telProperty().get();
	}
	
	public final void setTel(final String tel) {
		this.telProperty().set(tel);
	}
	
	public final StringProperty adrProperty() {
		return this.adr;
	}
	
	public final String getAdr() {
		return this.adrProperty().get();
	}
	
	public final void setAdr(final String adr) {
		this.adrProperty().set(adr);
	}
	
	public Client toClient() {
		return new Client(this.getCin(),this.getNom(),this.getPrenom(),this.getTel(),this.getAdr());
	}
	
	public void toBindClient(Client c) {
		this.cin.set(c.getCin());
		this.nom.set(c.getNom());
		this.prenom.set(c.getPrenom());
		this.tel.set(c.getTel());
		this.adr.set(c.getAdresse());
	}
	public boolean ajouter() {
		ClientDAO dao= new ClientDAO();
		return dao.addClient(this.toClient());
	}
	
	public boolean chercher(){
		ClientDAO dao= new ClientDAO();
		Client c=dao.getClientByCin(this.getCin());
		if(c==null)
			return false;
		else this.toBindClient(c);
		return true;
		
	}

	public void annuler() {
		this.cin.set("");
		this.nom.set("");
		this.prenom.set("");
		this.tel.set("");
		this.adr.set("");
		
	}

	public boolean supprimer() {
		ClientDAO dao= new ClientDAO();
		boolean c=dao.dropClient(this.getCin());
		return c;
	}

	public boolean modifier() {
		ClientDAO dao= new ClientDAO();
		boolean c=dao.updateClient(this.toClient());
		return c ;
	}

	
	

}
