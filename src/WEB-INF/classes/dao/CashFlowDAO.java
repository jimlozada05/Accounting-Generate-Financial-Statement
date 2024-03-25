package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;

public class CashFlowDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
		
	public static List<CashFlow> getAllRecords(){
		List<CashFlow> list=new ArrayList<CashFlow>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from cash_flow");
			rs=ps.executeQuery();
			while(rs.next()) {
				CashFlow cf=new CashFlow();
				cf.setCash_id(rs.getInt("cash_id"));
				cf.setJournal_id(rs.getInt("journal_id"));
				cf.setCash(rs.getDouble("cash"));
				cf.setCash_flow(rs.getDouble("cash_flow"));
				cf.setCreator_id(rs.getInt("creator_id"));
				list.add(cf);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<CashFlow> getAllRecordsInCreator(int creator_id){
		List<CashFlow> list=new ArrayList<CashFlow>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from cash_flow WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				CashFlow cf=new CashFlow();
				cf.setCash_id(rs.getInt("cash_id"));
				cf.setJournal_id(rs.getInt("journal_id"));
				cf.setCash(rs.getDouble("cash"));
				cf.setCash_flow(rs.getDouble("cash_flow"));
				cf.setCreator_id(rs.getInt("creator_id"));
				list.add(cf);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<CashFlow> getARecord(int cash_id){
		List<CashFlow> list=new ArrayList<CashFlow>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM cash_flow where cash_id=?");
			
			ps.setInt(1, cash_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CashFlow cf=new CashFlow();
				cf.setCash_id(rs.getInt("cash_id"));
				cf.setJournal_id(rs.getInt("journal_id"));
				cf.setCash(rs.getDouble("cash"));
				cf.setCash_flow(rs.getDouble("cash_flow"));
				cf.setCreator_id(rs.getInt("creator_id"));
				list.add(cf);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<CashFlow> getARecordByJournal(int journal_id){
		List<CashFlow> list=new ArrayList<CashFlow>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM cash_flow where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CashFlow cf=new CashFlow();
				cf.setCash_id(rs.getInt("cash_id"));
				cf.setJournal_id(rs.getInt("journal_id"));
				cf.setCash(rs.getDouble("cash"));
				cf.setCash_flow(rs.getDouble("cash_flow"));
				cf.setCreator_id(rs.getInt("creator_id"));
				list.add(cf);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static CashFlow getOneRecordByJournal(int journal_id){
		CashFlow statement = new CashFlow();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM cash_flow where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setCash_id(rs.getInt("cash_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setCash(rs.getDouble("cash"));
				statement.setCash_flow(rs.getDouble("cash_flow"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static int deleteCashFlow(CashFlow cf) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM cash_flow WHERE cash_id=?");
			
			ps.setInt(1, cf.getCash_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addCashFlow(int journal_id, double cash ,double cash_flow, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO cash_flow VALUES(null,?,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, cash);
			ps.setDouble(3, cash_flow);
			ps.setInt(4, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
