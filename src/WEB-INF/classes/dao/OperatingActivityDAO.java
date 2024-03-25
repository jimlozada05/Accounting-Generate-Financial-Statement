package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;

public class OperatingActivityDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<OperatingActivity> getAllRecords(){
		List<OperatingActivity> list=new ArrayList<OperatingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from operating_activity");
			rs=ps.executeQuery();
			while(rs.next()) {
				OperatingActivity oa=new OperatingActivity();
				oa.setOperating_id(rs.getInt("operating_id"));
				oa.setJournal_id(rs.getInt("journal_id"));
				oa.setOperating_activity(rs.getDouble("operating_activity"));
				oa.setCreator_id(rs.getInt("creator_id"));
				list.add(oa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<OperatingActivity> getAllRecordsInCreator(int creator_id){
		List<OperatingActivity> list=new ArrayList<OperatingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from operating_activity WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				OperatingActivity oa=new OperatingActivity();
				oa.setOperating_id(rs.getInt("operating_id"));
				oa.setJournal_id(rs.getInt("journal_id"));
				oa.setOperating_activity(rs.getDouble("operating_activity"));
				oa.setCreator_id(rs.getInt("creator_id"));
				list.add(oa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<OperatingActivity> getARecord(int operating_id){
		List<OperatingActivity> list=new ArrayList<OperatingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM operating_activity where operating_id=?");
			
			ps.setInt(1, operating_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				OperatingActivity oa=new OperatingActivity();
				oa.setOperating_id(rs.getInt("operating_id"));
				oa.setJournal_id(rs.getInt("journal_id"));
				oa.setOperating_activity(rs.getDouble("operating_activity"));
				oa.setCreator_id(rs.getInt("creator_id"));
				list.add(oa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<OperatingActivity> getARecordByJournal(int journal_id){
		List<OperatingActivity> list=new ArrayList<OperatingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM operating_activity where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				OperatingActivity oa=new OperatingActivity();
				oa.setOperating_id(rs.getInt("operating_id"));
				oa.setJournal_id(rs.getInt("journal_id"));
				oa.setOperating_activity(rs.getDouble("operating_activity"));
				oa.setCreator_id(rs.getInt("creator_id"));
				list.add(oa);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static OperatingActivity getOneRecordByJournal(int journal_id){
		OperatingActivity statement = new OperatingActivity();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM operating_activity where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setOperating_id(rs.getInt("operating_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setOperating_activity(rs.getDouble("operating_activity"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static int deleteOperatingActivity(OperatingActivity oa) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM operating_activity WHERE operating_id=?");
			
			ps.setInt(1, oa.getOperating_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addOperatingActivity(int journal_id, double operating_activity, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO operating_activity VALUES(null,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, operating_activity);
			ps.setInt(3, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
