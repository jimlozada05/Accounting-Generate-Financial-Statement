package dao;

import java.util.*;
import java.sql.*;

import connect.*;
import table.*;
import util.*;

public class FinancialPositionDAO {
	
	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<FinancialPosition> getAllRecords(){
		List<FinancialPosition> list=new ArrayList<FinancialPosition>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from financial_position");
			rs=ps.executeQuery();
			while(rs.next()) {
				FinancialPosition fp=new FinancialPosition();
				fp.setFinancial_id(rs.getInt("financial_id"));
				fp.setJournal_id(rs.getInt("journal_id"));
				fp.setTotal_asset(rs.getDouble("total_asset"));
				fp.setTotal_liability(rs.getDouble("total_liability"));
				fp.setCreator_id(rs.getInt("creator_id"));
				list.add(fp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<FinancialPosition> getAllRecordsInCreator(int creator_id){
		List<FinancialPosition> list=new ArrayList<FinancialPosition>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from financial_position WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				FinancialPosition fp=new FinancialPosition();
				fp.setFinancial_id(rs.getInt("financial_id"));
				fp.setJournal_id(rs.getInt("journal_id"));
				fp.setTotal_asset(rs.getDouble("total_asset"));
				fp.setTotal_liability(rs.getDouble("total_liability"));
				fp.setCreator_id(rs.getInt("creator_id"));
				list.add(fp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<FinancialPosition> getByJournalId(int journal_id) {
		List<FinancialPosition> list = new ArrayList<FinancialPosition>();
		
		try {
			con = DBConnect.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM financial_position WHERE journal_id = ?");
			ps.setInt(1, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FinancialPosition fp=new FinancialPosition();
				fp.setFinancial_id(rs.getInt("financial_id"));
				fp.setJournal_id(rs.getInt("journal_id"));
				fp.setTotal_asset(rs.getDouble("total_asset"));
				fp.setTotal_liability(rs.getDouble("total_liability"));
				fp.setCreator_id(rs.getInt("creator_id"));
				list.add(fp);
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static FinancialPosition getOneRecordByJournal(int journal_id) {
		FinancialPosition statement = new FinancialPosition();
		
		try {
			con = DBConnect.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM financial_position WHERE journal_id = ?");
			ps.setInt(1, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				statement.setFinancial_id(rs.getInt("financial_id"));
				statement.setJournal_id(rs.getInt("journal_id"));
				statement.setTotal_asset(rs.getDouble("total_asset"));
				statement.setTotal_liability(rs.getDouble("total_liability"));
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
	
	
	public static int deleteFinancialPosition(FinancialPosition fp) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM financial_position WHERE financial_id=?");
			
			ps.setInt(1, fp.getFinancial_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1; 
	}
	
	public static int addFinancialPosition(int journal_id, double total_asset, double total_liability, int creator_id) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO financial_position VALUES(null,?,?,?,?)");
			
			ps.setInt(1, journal_id);
			ps.setDouble(2, total_asset);
			ps.setDouble(3, total_liability);
			ps.setInt(4, creator_id);

			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
}
