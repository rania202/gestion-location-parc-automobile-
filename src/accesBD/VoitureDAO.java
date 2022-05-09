package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entites.Voiture;

public class VoitureDAO {
	public boolean addVoiture(Voiture v) {
		String requete =" insert into voiture  values(?,?,?,?)";
		Connection connect=DBConnection.getInstance();
		int n=0;
		PreparedStatement stm=null;
		try {
			stm= connect.prepareStatement(requete);
			stm.setString(1, v.getNum_immat());
			stm.setString(2, v.getType());
			stm.setString(3, v.getModele());
			stm.setFloat(4, v.getPrix());
			n=stm.executeUpdate();
		} catch (SQLException e) {
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
	public Voiture getVoitureByimmat (String immat) {
		Voiture vtr=null;
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select * from voiture where immat=?";
			PreparedStatement statement = connect.prepareStatement(select);
			statement.setString(1,immat);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				vtr = new Voiture (res.getString(1),res.getString(2),res.getString(3),res.getFloat(4));
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return vtr;
	}
	public boolean dropVoiture (String  immat ) {
		String requete =" delete from voiture where immat = ? " ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm= connect.prepareStatement(requete);
			stm.setString(1, immat);
			n=stm.executeUpdate();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return n>0;
	}
	public boolean updateVoiture (Voiture v) {
		String update =" update voiture set modele= ? ,type= ? ,prix= ? where immat= ? " ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm = connect.prepareStatement(update);
			stm.setString(4, v.getNum_immat());
			stm.setString(2, v.getType());
			stm.setString(1, v.getModele());
			stm.setFloat(3, v.getPrix());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
		
	}
	public List<Voiture> getAll() {
		Voiture vtr=null;
		List<Voiture> liste = new ArrayList<Voiture>();
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select * from voiture ";
			PreparedStatement statement = connect.prepareStatement(select);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				vtr = new Voiture (res.getString(1),res.getString(2),res.getString(3),res.getFloat(4));
				liste.add(vtr);
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return liste;
	}
	public List<Voiture> getDisponible(LocalDate dd,int duree){
		Voiture vtr=null;
		List<Voiture> liste = new ArrayList<Voiture>();
		Connection connect=DBConnection.getInstance();
		try {
			
		LocalDate df=dd.plusDays(duree);
		String select = "select * from voiture v WHERE not exists (select * from reservation r where v.immat=r.immat and "
				+ "((datedebut between ? and ?) or"
				+ "(adddate(dateDebut,nbreJours) between ? and ?))) ";
		PreparedStatement statement = connect.prepareStatement(select);
		statement.setDate(1, java.sql.Date.valueOf(dd));
		statement.setDate(2, java.sql.Date.valueOf(df));
		statement.setDate(3, java.sql.Date.valueOf(dd));
		statement.setDate(4, java.sql.Date.valueOf(df));
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			vtr = new Voiture (res.getString(1),res.getString(2),res.getString(3),res.getFloat(4));
			liste.add(vtr);
		}
		}catch (Exception e) {
			e.printStackTrace();
			}	
		
		return liste;
	}
}
