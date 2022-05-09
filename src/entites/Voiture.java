package entites;

public class Voiture {
	private String type;
	private String num_immat;
	private String modele;
	private float prix;
	
	public Voiture(String num_immat, String type , String modele,float px){
		this.num_immat=num_immat;
		this.type=type;
		this.modele=modele;
		this.prix=px;
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNum_immat() {
		return num_immat;
	}

	public void setNum_immat(String num_immat) {
		this.num_immat = num_immat;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	
	@Override
	public String toString() {
		return "Voiture [type=" + type + ", num_immat=" + num_immat + ", modele=" + modele + ", prix=" + prix + "]";
	}
	

}

