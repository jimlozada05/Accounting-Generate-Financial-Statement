package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;

public class IncomeStatementDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<IncomeStatement> getAllRecords(){
		List<IncomeStatement> list=new ArrayList<IncomeStatement>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from income_statement");
			rs=ps.executeQuery();
			while(rs.next()) {
				IncomeStatement is=new IncomeStatement();
				is.setIncome_id(rs.getInt("income_id"));
				is.setJournal_id(rs.getInt("journal_id"));
				is.setNet_worth(rs.getDouble("net_worth"));
				is.setCreator_id(rs.getInt("creator_id"));
				list.add(is);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<IncomeStatement> getAllRecordsInCreator(int creator_id){
		List<IncomeStatement> list=new ArrayList<IncomeStatement>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from income_statement WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				IncomeStatement is=new IncomeStatement();
				is.setIncome_id(rs.getInt("income_id"));
				is.setJournal_id(rs.getInt("journal_id"));
				is.setNet_worth(rs.getDouble("net_worth"));
				is.setCreator_id(rs.getInt("creator_id"));
				list.add(is);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<IncomeStatement> getARecord(int income_id){
		List<IncomeStatement> list=new ArrayList<IncomeStatement>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM income_statement where income_id=?");
			
			ps.setInt(1, income_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				IncomeStatement is=new IncomeStatement();
				is.setIncome_id(rs.getInt("income_id"));
				is.setJournal_id(rs.getInt("journal_id"));
				is.setNet_worth(rs.getDouble("net_worth"));
				is.setCreator_id(rs.getInt("creator_id"));
				list.add(is);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<IncomeStatement> getARecordByJournal(int journal_id){
		List<IncomeStatement> list=new ArrayList<IncomeStatement>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM income_statement where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				IncomeStatement is=new IncomeStatement();
				is.setIncome_id(rs.getInt("income_id"));
				is.setJournal_id(rs.getInt("journal_id"));
				is.setNet_worth(rs.getDouble("net_worth"));
				is.setCreator_id(rs.getInt("creator_id"));
				list.add(is);
				
				list.add(is);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static IncomeStatement getOneRecordByJournal(int journal_id){
		IncomeStatement statement = new IncomeStatement();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM income_statement where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setIncome_id(rs.getInt("income_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setNet_worth(rs.getDouble("net_worth"));
				statement.setCreator_id(rs.getInt("creator_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static int deleteIncomeStatement(IncomeStatement is) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM income_statement WHERE income_id=?");
			
			ps.setInt(1, is.getIncome_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addIncomeStatement(int journal_id, double net_worth, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO income_statement VALUES(null,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, net_worth);
			ps.setInt(3, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
