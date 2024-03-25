package dao;

import java.util.*;
import java.sql.*;

import connect.*;
import table.*;
import util.*;

public class OwnerEquityDAO {
	
	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<OwnerEquity> getAllRecords(){
		List<OwnerEquity> list=new ArrayList<OwnerEquity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from owners_equity");
			rs=ps.executeQuery();
			while(rs.next()) {
				OwnerEquity oe=new OwnerEquity();
				oe.setEquity_id(rs.getInt("equity_id"));
				oe.setJournal_id(rs.getInt("journal_id"));
				oe.setEnd_capital(rs.getDouble("end_capital"));
				oe.setCreator_id(rs.getInt("creator_id"));
				list.add(oe);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<OwnerEquity> getAllRecordsInCreator(int creator_id){
		List<OwnerEquity> list=new ArrayList<OwnerEquity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from owners_equity WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				OwnerEquity oe=new OwnerEquity();
				oe.setEquity_id(rs.getInt("equity_id"));
				oe.setJournal_id(rs.getInt("journal_id"));
				oe.setEnd_capital(rs.getDouble("end_capital"));
				oe.setCreator_id(rs.getInt("creator_id"));
				list.add(oe);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<OwnerEquity> getByJournalId(int journal_id) {
		List<OwnerEquity> list = new ArrayList<OwnerEquity>();
		
		try {
			con = DBConnect.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM owners_equity WHERE journal_id = ?");
			ps.setInt(1, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OwnerEquity oe=new OwnerEquity();
				oe.setEquity_id(rs.getInt("equity_id"));
				oe.setJournal_id(rs.getInt("journal_id"));
				oe.setEnd_capital(rs.getDouble("end_capital"));
				oe.setCreator_id(rs.getInt("creator_id"));
				list.add(oe);
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static OwnerEquity getOneRecordByJournal(int journal_id) {
		OwnerEquity statement = new OwnerEquity();
		
		try {
			con = DBConnect.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM owners_equity WHERE journal_id = ?");
			ps.setInt(1, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setEquity_id(rs.getInt("equity_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setEnd_capital(rs.getDouble("end_capital"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
	
	public static int deleteOwnerEquity(OwnerEquity oe) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM owner_equity WHERE equity_id=?");
			
			ps.setInt(1, oe.getEquity_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1; 
	}
	
	public static int addOwnerEquity(int journal_id, double end_capital, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO owners_equity VALUES(null,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, end_capital);
			ps.setInt(3, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
