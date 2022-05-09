package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entites.Client;
import entites.Reservation;
import entites.Voiture;

public class ReservationDAO {
	public boolean addReservation(Reservation R) {
		String requete =" insert into reservation (cin, immat, date_Reservation,  nbreJours,dateDebut) values(?,?,?,?,?)";
		Connection connect=DBConnection.getInstance();
		int n=0;
		PreparedStatement stm=null;
		try {
			stm= connect.prepareStatement(requete);
			
			stm.setString(1, R.getClient().getCin());
			stm.setString(2, R.getVoiture().getNum_immat());
			java.sql.Date d= java.sql.Date.valueOf(R.getDateReservation());
			stm.setDate(3, d);
			stm.setLong(4, R.getNbreJours());
			d= java.sql.Date.valueOf( R.getDateDebut());
			stm.setDate(5, d);
			n=stm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return n>0;
		
	}
	public boolean dropReservation (int code ) {
		String requete =" delete from reservation where code_reservation = ? " ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm= connect.prepareStatement(requete);
			stm.setInt(1,code);
			n=stm.executeUpdate();
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return n>0;
	}
	public boolean updateReservation (Reservation R) {
		String update =" update reservation set cin= ? , immat= ? , date_reservation= ? , nbreJours=? , dateDebut= ? where code_reservation= ?" ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm = connect.prepareStatement(update);
			stm.setInt(6, R.getCodereserv());
			stm.setString(1, R.getClient().getCin());
			stm.setString(2, R.getVoiture().getNum_immat());
			java.sql.Date d= java.sql.Date.valueOf(R.getDateReservation());
			stm.setDate(3, d);
			stm.setInt(4, R.getNbreJours());
			d= java.sql.Date.valueOf( R.getDateDebut());
			stm.setDate(5, d);
			n=stm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0 ;
		
	}
	public Reservation getReservationByCode(int code) {
		
		Reservation r=null;
		Client c=null;
		Voiture v=null;
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select C.cin, C.nom, C.prenom, C.tel, C.adresse, V.immat, V.modele, V.type, V.prix,R.code_reservation ,  R.date_reservation , R.dateDebut , "
					+ "R.nbreJours from reservation as R NATURAL join client as C natural join voiture as V where code_reservation= ? ";
			PreparedStatement statement = connect.prepareStatement(select);
			statement.setInt(1,code);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				c= new Client(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
				v= new Voiture(res.getString(6),res.getString(7),res.getString(8),res.getFloat(9));
				
				r = new Reservation (res.getInt(10),c,v,res.getDate(11).toLocalDate(),res.getDate(12).toLocalDate(),res.getInt(13));
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return r;
	}
	public List<Reservation> getAll() {
		Client clt=null;
		Voiture vtr=null;
		Reservation r=null;
		List<Reservation> liste = new ArrayList<Reservation>();
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select C.cin, C.nom, C.prenom, C.tel, C.adresse, V.immat, V.modele, V.type, V.prix,R.code_reservation ,  R.date_reservation , R.dateDebut , "
					+ "R.nbreJours from reservation as R NATURAL join client as C natural join voiture as V  ";
			PreparedStatement statement = connect.prepareStatement(select);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				clt= new Client(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
				vtr= new Voiture(res.getString(6),res.getString(7),res.getString(8),res.getFloat(9));
				r = new Reservation (res.getInt(10),clt,vtr,res.getDate(11).toLocalDate(),res.getDate(12).toLocalDate(),res.getInt(13));
				liste.add(r);
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return liste;
	}
public int getNextCode() {		
		int n=0;
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select max(code_reservation) from Reservation";
			PreparedStatement statement = connect.prepareStatement(select);
			ResultSet res = statement.executeQuery();
			
			while (res.next()) 
				n=res.getInt(1);
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return n;
	}

}
