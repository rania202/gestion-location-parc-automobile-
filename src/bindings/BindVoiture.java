package bindings;

import accesBD.VoitureDAO;
import entites.Voiture;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BindVoiture {
	StringProperty immat= new SimpleStringProperty();
	StringProperty type= new SimpleStringProperty();
	StringProperty modele= new SimpleStringProperty();
	StringProperty prix= new SimpleStringProperty();
	
	public final StringProperty immatProperty() {
		return this.immat;
	}
	
	public final String getImmat() {
		return this.immatProperty().get();
	}
	
	public final void setImmat(final String immat) {
		this.immatProperty().set(immat);
	}
	
	public final StringProperty typeProperty() {
		return this.type;
	}
	
	public final String getType() {
		return this.typeProperty().get();
	}
	
	public final void setType(final String type) {
		this.typeProperty().set(type);
	}
	
	public final StringProperty modeleProperty() {
		return this.modele;
	}
	
	public final String getModele() {
		return this.modeleProperty().get();
	}
	
	public final void setModele(final String modele) {
		this.modeleProperty().set(modele);
	}
	
	public final StringProperty prixProperty() {
		return this.prix;
	}
	
	public final String getPrix() {
		return this.prixProperty().get();
	}
	
	public final void setPrix(final String prix) {
		this.prixProperty().set(prix);
	}
	public Voiture toVoiture() {
		return new Voiture(this.getImmat(),this.getType(),this.getModele(),Float.parseFloat(this.getPrix()));
	}
	
	public void toBindVoiture(Voiture v) {
		if(v!=null) {
		this.immat.set(v.getNum_immat());
		this.type.set(v.getType());
		this.modele.set(v.getModele());
		this.prix.set(Float.toString(v.getPrix()));}
		
	}
	public boolean ajouter() {
		VoitureDAO dao= new VoitureDAO();
		return dao.addVoiture(this.toVoiture());
	}
	
	public boolean chercher(){
		VoitureDAO dao= new VoitureDAO();
		Voiture v=dao.getVoitureByimmat(this.getImmat());
		if(v==null)
			return false;
		else this.toBindVoiture(v);
		return true;
		
	}

	public void annuler() {
		this.immat.set("");
		this.type.set("");
		this.modele.set("");
		this.prix.set("");
		
	}
	public boolean modifier() {
		VoitureDAO dao= new VoitureDAO();
		boolean v=dao.updateVoiture(this.toVoiture());
		return v ;
		
	}
	public boolean supprimer() {
		VoitureDAO dao= new VoitureDAO();
		boolean v=dao.dropVoiture(this.getImmat());
		return v;
		
	}
	

	

}
