package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;
import util.*;


public class JournalEntryDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	static ResultSet rs2=null;
	
	public static List<JournalEntry> getAllRecords(){
		List<JournalEntry> list=new ArrayList<JournalEntry>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from journal_entry");
			rs=ps.executeQuery();
			while(rs.next()) {
				JournalEntry gj=new JournalEntry();
				gj.setEntry_id(rs.getInt("entry_id"));
				gj.setJournal_id(rs.getInt("journal_id"));
				gj.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gj.setDebit(rs.getDouble("debit"));
				gj.setCredit(rs.getDouble("credit"));
				gj.setDate(rs.getDate("date"));
				gj.setCreator_id(rs.getInt("creator_id"));
				gj.setLine_id(rs.getInt("line_id"));
				list.add(gj);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<JournalEntry> getByJournalId(int journal_id) {
		List<JournalEntry> list = new ArrayList<JournalEntry>();
		
		try {
			con = DBConnect.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM journal_entry WHERE journal_id = ?");
			ps.setInt(1, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				JournalEntry gj = new JournalEntry();
				gj.setEntry_id(rs.getInt("entry_id"));
				gj.setJournal_id(rs.getInt("journal_id"));
				gj.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gj.setDebit(rs.getDouble("debit"));
				gj.setCredit(rs.getDouble("credit"));
				gj.setDate(rs.getDate("date"));
				gj.setCreator_id(rs.getInt("creator_id"));
				gj.setLine_id(rs.getInt("line_id"));
				
				list.add(gj);
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<JournalEntry> getARecord(int entry_id){
		List<JournalEntry> list=new ArrayList<JournalEntry>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM journal_entry where entry_id=?");
			
			ps.setInt(1, entry_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				JournalEntry gj=new JournalEntry();
				gj.setEntry_id(rs.getInt("entry_id"));
				gj.setJournal_id(rs.getInt("journal_id"));
				gj.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gj.setDebit(rs.getDouble("debit"));
				gj.setCredit(rs.getDouble("credit"));
				gj.setDate(rs.getDate("date"));
				gj.setCreator_id(rs.getInt("creator_id"));
				gj.setLine_id(rs.getInt("line_id"));
				
				list.add(gj);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static JournalEntry getGeneralJournalByID(int entry_id) {
		try{
				con=DBConnect.getConnection();
				ps=con.prepareStatement("SELECT * FROM journal_entry WHERE entry_id=?");
				ps.setInt(1, entry_id);
				
				rs= ps.executeQuery();
				JournalEntry gj = new JournalEntry();
				
				if(rs.next()) {
					gj.setEntry_id(rs.getInt(1));
					gj.setJournal_id(rs.getInt(2));
					gj.setAccount_title(AccountTitle.valueOf(rs.getString(3)));
					gj.setDebit(rs.getDouble(4));
					gj.setCredit(rs.getDouble(5));
					gj.setDate(rs.getDate(6));
					gj.setCreator_id(rs.getInt(7));
					gj.setLine_id(rs.getInt(8));
					}
				
				rs.close();
				ps.close();
				con.close();
				return gj;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static int deleteGeneralJournal(JournalEntry gj) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM journal_entry WHERE entry_id=?");
			
			ps.setInt(1, gj.getEntry_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1; 
	}
	
	public static int addGeneralJournal(JournalEntry gj) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("Insert into journal_entry values(null,?,?,?,?,?,?,?)");
			
			ps.setInt(1,gj.getJournal_id());
			ps.setString(2, gj.getAccount_title().toString());
			ps.setDouble(3, gj.getDebit());
			ps.setDouble(4, gj.getCredit());
			ps.setDate(5, gj.getDate());
			ps.setInt(6, gj.getCreator_id());
			ps.setInt(7, gj.getLine_id());
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
	public static int editGeneralJournal(JournalEntry gj) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("UPDATE journal_entry SET account_title=?, debit=?, credit=?, date=? where entry_id=?");
			ps.setString(1, gj.getAccount_title().toString());
			ps.setDouble(2, gj.getDebit());
			ps.setDouble(3, gj.getCredit());
			ps.setDate(4, gj.getDate());
			ps.setInt(5, gj.getEntry_id());
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public static int getDebitByGeneralJournal(JournalEntry gj) {
		int store=0;
		try {
			Connection con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT debit from journal_entry where entry_id=?");
			ps.setInt(1, gj.getEntry_id());
			rs=ps.executeQuery();
			if(rs.next()) {
				store=rs.getInt(1);
			}
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return store;
	}
	public static int getCreditByGeneralJournal(JournalEntry gj) {
		int store=0;
		try {
			Connection con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT credit from journal_entry where entry_id=?");
			ps.setInt(1, gj.getEntry_id());
			rs=ps.executeQuery();
			if(rs.next()) {
				store=rs.getInt(1);
			}
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return store;
	}
	
	public static String getAccountTitleByGeneralJournal(JournalEntry gj) {
		String acc="";
		try {
			Connection con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT account_title from journal_entry where entry_id=?");
			ps.setInt(1, gj.getEntry_id());
			rs=ps.executeQuery();
			if(rs.next()) {
				acc=rs.getString(1);
			}
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return acc;
	}
	
	public static List<JournalEntry> getAllRecordWithCash(int journal_id){
		List<JournalEntry> list=new ArrayList<JournalEntry>();
		
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT line_id from journal_entry where account_title='CASH' AND journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {

				ps=con.prepareStatement("SELECT * from journal_entry WHERE line_id=? AND journal_id=? AND NOT account_title='CASH'");
				ps.setInt(1, rs.getInt("line_id"));
				ps.setInt(2, journal_id);
				rs2=ps.executeQuery();
				
				while(rs2.next()) {
					JournalEntry gj=new JournalEntry();
					gj.setEntry_id(rs2.getInt("entry_id"));
					gj.setJournal_id(rs2.getInt("journal_id"));
					gj.setAccount_title(AccountTitle.valueOf(rs2.getString("account_title")));
					gj.setDebit(rs2.getDouble("debit"));
					gj.setCredit(rs2.getDouble("credit"));
					gj.setDate(rs2.getDate("date"));
					gj.setCreator_id(rs2.getInt("creator_id"));
					gj.setLine_id(rs2.getInt("line_id"));
					list.add(gj);
				}
			
			} 	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int selectLineNumber(int journal_id) {
		int status = 0; 
		try {
			Connection con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT max(line_id) from journal_entry where journal_id=?");
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				status= rs.getInt(1) + 1;
			}
			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
