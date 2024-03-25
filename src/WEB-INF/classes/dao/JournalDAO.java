package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;


public class JournalDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<Journal> getAllRecords(){
		List<Journal> list=new ArrayList<Journal>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from journal");
			rs=ps.executeQuery();
			while(rs.next()) {
				Journal gr=new Journal();
				gr.setJournal_id(rs.getInt("journal_id"));
				gr.setTotal_debit(rs.getDouble("total_debit"));
				gr.setTotal_credit(rs.getDouble("total_credit"));
				gr.setTotal_acc(rs.getInt("total_acc"));
				gr.setCreator_id(rs.getInt("creator_id"));
				gr.setType(rs.getString("type"));
				list.add(gr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Journal> getRecordsUnfinish(int creator_id, String type){
		List<Journal> list=new ArrayList<Journal>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM journal WHERE creator_id=? AND type=? ORDER BY journal_id DESC LIMIT 10");
			ps.setInt(1, creator_id);
			ps.setString(2, type);
			rs=ps.executeQuery();
			while(rs.next()) {
				Journal gr=new Journal();
				gr.setJournal_id(rs.getInt("journal_id"));
				gr.setTotal_debit(rs.getDouble("total_debit"));
				gr.setTotal_credit(rs.getDouble("total_credit"));
				gr.setTotal_acc(rs.getInt("total_acc"));
				gr.setCreator_id(rs.getInt("creator_id"));
				gr.setType(rs.getString("type"));
				list.add(gr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Journal> getAllFinish(int creator_id, String type){
		List<Journal> list=new ArrayList<Journal>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM journal WHERE creator_id=? and type=?");
			ps.setInt(1, creator_id);
			ps.setString(2, type);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Journal gr=new Journal();
				gr.setJournal_id(rs.getInt("journal_id"));
				gr.setTotal_debit(rs.getDouble("total_debit"));
				gr.setTotal_credit(rs.getDouble("total_credit"));
				gr.setTotal_acc(rs.getInt("total_acc"));
				gr.setCreator_id(rs.getInt("creator_id"));
				gr.setType(rs.getString("type"));
				list.add(gr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Journal> getARecord(int journal_id){
		List<Journal> list=new ArrayList<Journal>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM journal where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Journal gr=new Journal();
				gr.setJournal_id(rs.getInt("journal_id"));
				gr.setTotal_debit(rs.getDouble("total_debit"));
				gr.setTotal_credit(rs.getDouble("total_credit"));
				gr.setTotal_acc(rs.getInt("total_acc"));
				gr.setCreator_id(rs.getInt("creator_id"));
				gr.setType(rs.getString("type"));
				list.add(gr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Journal getGroupByID(int journal_id) {
		try{
				con=DBConnect.getConnection();
				ps=con.prepareStatement("SELECT * FROM journal WHERE journal_id=?");
				ps.setInt(1, journal_id);
				
				rs= ps.executeQuery();
				Journal gr = new Journal();
				
				if(rs.next()) {
					
					gr.setJournal_id(rs.getInt(1));
					gr.setTotal_debit(rs.getDouble(2));
					gr.setTotal_credit(rs.getDouble(3));
					gr.setTotal_acc(rs.getInt(4));
					gr.setCreator_id(rs.getInt(5));
					gr.setType(rs.getString(6));
					}
				
				rs.close();
				ps.close();
				con.close();
				return gr;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static int deleteGroup(Journal gr) {
		int status=0;
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM journal WHERE journal_id=?");
			ps.setInt(1, gr.getJournal_id());
			
			status=ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int addGroup(Journal gr) {
		int store=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("Insert into journal value(null,?,?,?,?,?)");
			ps.setDouble(1, gr.getTotal_debit());
			ps.setDouble(2, gr.getTotal_credit());
			ps.setInt(3, gr.getTotal_acc());
			ps.setInt(4, gr.getCreator_id());
			ps.setString(5, gr.getType());
			ps.executeUpdate();
			ps=con.prepareStatement("SELECT max(journal_id) from journal where creator_id=? and total_debit=0 and total_credit=0 and total_acc=0");
			ps.setInt(1, gr.getCreator_id());
			rs=ps.executeQuery();
			if(rs.next()) {
				store=rs.getInt(1);
			}
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return store;
	}
	
	public static int editFinish(int journal_id, String type) {
		int status=0;
		try {
			Connection con =DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("Update journal set type=? where journal_id=?");
			ps.setString(1, type);
			ps.setInt(2, journal_id);
			
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch(Exception e) {e.printStackTrace();}
		return status;
	}
	
	public static int reupdateJournal(int id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("UPDATE `journal` SET total_debit = (SELECT SUM(debit) FROM journal_entry WHERE journal_id = ?), total_credit = (SELECT SUM(credit) FROM journal_entry WHERE journal_id = ?), total_acc = (SELECT COUNT(*) FROM journal_entry WHERE journal_id = ?) WHERE journal_id = ?");
			
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, id);
			ps.setInt(4, id);
			
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
