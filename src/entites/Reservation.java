package entites;

import java.time.LocalDate;

public class Reservation {
	 private int codereserv;
	 private Client client ;
	 private Voiture voiture;
	 private LocalDate dateDebut;
	 private LocalDate dateReservation;
	 private int nbreJours;
	public Reservation(int codereserv, Client client, Voiture voiture, LocalDate dateDebut, LocalDate dateReservation,
			int nbreJours) {
		super();
		this.codereserv = codereserv;
		this.client = client;
		this.voiture = voiture;
		this.dateDebut = dateDebut;
		this.dateReservation = dateReservation;
		this.nbreJours = nbreJours;
	}
	public int getCodereserv() {
		return codereserv;
	}
	public void setCodereserv(int codereserv) {
		this.codereserv = codereserv;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}
	public int getNbreJours() {
		return nbreJours;
	}
	public void setNbreJours(int nbreJours) {
		this.nbreJours = nbreJours;
	}
	@Override
	public String toString() {
		return "Reservation [codereserv=" + codereserv + ", client=" + client + ", voiture=" + voiture + ", dateDebut="
				+ dateDebut + ", dateReservation=" + dateReservation + ", nbreJours=" + nbreJours + "]";
	}
	
}
	 
	 
	  