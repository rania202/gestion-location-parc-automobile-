package accesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entites.Client;

public class ClientDAO {
	public boolean addClient(Client c) {
		String requete =" insert into client values(?,?,?,?,?)";
		Connection connect=DBConnection.getInstance();
		int n=0;
		PreparedStatement stm=null;
		try {
			stm= connect.prepareStatement(requete);
			stm.setString(1, c.getCin());
			stm.setString(2, c.getNom());
			stm.setString(3, c.getPrenom());
			stm.setString(4, c.getTel());
			stm.setString(5, c.getAdresse());
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
	public boolean dropClient (String  cin ) {
		String requete =" delete from client where cin =?" ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm= connect.prepareStatement(requete);
			stm.setString(1,cin);
			n=stm.executeUpdate();
			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return n>0;
	}
	public boolean updateClient (Client c) {
		String update =" update client set nom= ?,prenom=?,tel=?, adresse= ? where cin=?" ;
		Connection connect=DBConnection.getInstance();
		int n=0;
		try {
			PreparedStatement stm = connect.prepareStatement(update);
			stm.setString(5, c.getCin());
			stm.setString(1, c.getNom());
			stm.setString(2, c.getPrenom());
			stm.setString(3, c.getTel());
			stm.setString(4, c.getAdresse());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
		
	}
	public Client getClientByCin(String cin) {
		Client clt=null;
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select * from client where cin=?";
			PreparedStatement statement = connect.prepareStatement(select);
			statement.setString(1,cin);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				clt = new Client (res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return clt;
	}
	public List<Client> getAll() {
		Client clt=null;
		List<Client> liste = new ArrayList<Client>();
		Connection connect=DBConnection.getInstance();
		try {
			String select = "select * from client ";
			PreparedStatement statement = connect.prepareStatement(select);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				clt = new Client (res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
				liste.add(clt);
			}
	}catch (Exception e) {
			System.out.println( "ERROR:"+e.getMessage());
			}	
		
		return liste;
	}
}
