package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;

public class FinancingActivityDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<FinancingActivity> getAllRecords(){
		List<FinancingActivity> list=new ArrayList<FinancingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from financing_activity");
			rs=ps.executeQuery();
			while(rs.next()) {
				FinancingActivity fa=new FinancingActivity();
				fa.setFinancing_id(rs.getInt("financing_id"));
				fa.setJournal_id(rs.getInt("journal_id"));
				fa.setFinancing_activity(rs.getDouble("financing_activity"));
				fa.setCreator_id(rs.getInt("creator_id"));
				list.add(fa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<FinancingActivity> getAllRecordsInCreator(int creator_id){
		List<FinancingActivity> list=new ArrayList<FinancingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from financing_activity WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				FinancingActivity fa=new FinancingActivity();
				fa.setFinancing_id(rs.getInt("financing_id"));
				fa.setJournal_id(rs.getInt("journal_id"));
				fa.setFinancing_activity(rs.getDouble("financing_activity"));
				fa.setCreator_id(rs.getInt("creator_id"));
				list.add(fa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<FinancingActivity> getARecord(int financing_id){
		List<FinancingActivity> list=new ArrayList<FinancingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM financing_activity where financing_id=?");
			
			ps.setInt(1, financing_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				FinancingActivity fa=new FinancingActivity();
				fa.setFinancing_id(rs.getInt("financing_id"));
				fa.setJournal_id(rs.getInt("journal_id"));
				fa.setFinancing_activity(rs.getDouble("financing_activity"));
				fa.setCreator_id(rs.getInt("creator_id"));
				list.add(fa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<FinancingActivity> getARecordByJournal(int journal_id){
		List<FinancingActivity> list=new ArrayList<FinancingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM financing_activity where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				FinancingActivity fa=new FinancingActivity();
				fa.setFinancing_id(rs.getInt("financing_id"));
				fa.setJournal_id(rs.getInt("journal_id"));
				fa.setFinancing_activity(rs.getDouble("financing_activity"));
				fa.setCreator_id(rs.getInt("creator_id"));
				list.add(fa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static FinancingActivity getOneRecordByJournal(int journal_id){
		FinancingActivity statement = new FinancingActivity();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM financing_activity where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setFinancing_id(rs.getInt("financing_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setFinancing_activity(rs.getDouble("financing_activity"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static int deleteFinancingActivity(FinancingActivity fa) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM financing_activity WHERE financing_id=?");
			
			ps.setInt(1, fa.getFinancing_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addFinancingActivity(int journal_id, double financing_activity, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO financing_activity VALUES(null,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, financing_activity);
			ps.setInt(3, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
