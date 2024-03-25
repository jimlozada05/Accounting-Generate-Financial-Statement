package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;

public class InvestingActivityDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<InvestingActivity> getAllRecords(){
		List<InvestingActivity> list=new ArrayList<InvestingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from investing_activity");
			rs=ps.executeQuery();
			while(rs.next()) {
				InvestingActivity ia=new InvestingActivity();
				ia.setInvesting_id(rs.getInt("investing_id"));
				ia.setJournal_id(rs.getInt("journal_id"));
				ia.setInvesting_activity(rs.getDouble("investing_activity"));
				ia.setCreator_id(rs.getInt("creator_id"));
				list.add(ia);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<InvestingActivity> getAllRecordsInCreator(int creator_id){
		List<InvestingActivity> list=new ArrayList<InvestingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from investing_activity WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				InvestingActivity ia=new InvestingActivity();
				ia.setInvesting_id(rs.getInt("investing_id"));
				ia.setJournal_id(rs.getInt("journal_id"));
				ia.setInvesting_activity(rs.getDouble("investing_activity"));
				ia.setCreator_id(rs.getInt("creator_id"));
				list.add(ia);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<InvestingActivity> getARecord(int investing_id){
		List<InvestingActivity> list=new ArrayList<InvestingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM investing_activity where investing_id=?");
			
			ps.setInt(1, investing_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				InvestingActivity ia=new InvestingActivity();
				ia.setInvesting_id(rs.getInt("investing_id"));
				ia.setJournal_id(rs.getInt("journal_id"));
				ia.setInvesting_activity(rs.getDouble("investing_activity"));
				ia.setCreator_id(rs.getInt("creator_id"));
				list.add(ia);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<InvestingActivity> getARecordByJournal(int journal_id){
		List<InvestingActivity> list=new ArrayList<InvestingActivity>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM investing_activity where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				InvestingActivity ia=new InvestingActivity();
				ia.setInvesting_id(rs.getInt("investing_id"));
				ia.setJournal_id(rs.getInt("journal_id"));
				ia.setInvesting_activity(rs.getDouble("investing_activity"));
				ia.setCreator_id(rs.getInt("creator_id"));
				list.add(ia);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static InvestingActivity getOneRecordByJournal(int journal_id){
		InvestingActivity statement = new InvestingActivity();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM investing_activity where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setInvesting_id(rs.getInt("investing_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setInvesting_activity(rs.getDouble("investing_activity"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static int deleteInvestingActivity(InvestingActivity ia) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM investing_activity WHERE investing_id=?");
			
			ps.setInt(1, ia.getInvesting_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addInvestingActivity(int journal_id, double investing_activity, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO investing_activity VALUES(null,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, investing_activity);
			ps.setInt(3, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
