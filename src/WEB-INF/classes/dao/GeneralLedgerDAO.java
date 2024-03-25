package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connect.DBConnect;
import table.GeneralLedger;
import table.JournalEntry;
import util.AccountTitle;


public class GeneralLedgerDAO {
	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<GeneralLedger> getAllRecords(){
		List<GeneralLedger> list=new ArrayList<GeneralLedger>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from general_journal");
			rs=ps.executeQuery();
			while(rs.next()) {
				GeneralLedger gl=new GeneralLedger();
				gl.setLedger_id(rs.getInt("ledger_id"));
				gl.setJournal_id(rs.getInt("journal_id"));
				gl.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gl.setAll_debit(rs.getDouble("all_debit"));
				gl.setAll_credit(rs.getDouble("all_credit"));
				gl.setCreator_id(rs.getInt("creator_id"));
				gl.setTotal(rs.getDouble("total"));
				list.add(gl);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<GeneralLedger> getARecord(int ledger_id){
		List<GeneralLedger> list=new ArrayList<GeneralLedger>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM general_ledger where ledger_id=?");
			
			ps.setInt(1, ledger_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				GeneralLedger gl=new GeneralLedger();
				gl.setLedger_id(rs.getInt("ledger_id"));
				gl.setJournal_id(rs.getInt("journal_id"));
				gl.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gl.setAll_debit(rs.getDouble("all_debit"));
				gl.setAll_credit(rs.getDouble("all_credit"));
				gl.setCreator_id(rs.getInt("creator_id"));
				gl.setTotal(rs.getDouble("total"));
				
				list.add(gl);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<GeneralLedger> getARecordByJournal(int journal_id){
		List<GeneralLedger> list=new ArrayList<GeneralLedger>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM general_ledger where journal_id=?");
			
			ps.setInt(1, journal_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				GeneralLedger gl=new GeneralLedger();
				gl.setLedger_id(rs.getInt("ledger_id"));
				gl.setJournal_id(rs.getInt("journal_id"));
				gl.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gl.setAll_debit(rs.getDouble("all_debit"));
				gl.setAll_credit(rs.getDouble("all_credit"));
				gl.setCreator_id(rs.getInt("creator_id"));
				gl.setTotal(rs.getDouble("total"));
				
				list.add(gl);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static GeneralLedger getGeneralLedgerByID(int ledger_id) {
		try{
				con=DBConnect.getConnection();
				ps=con.prepareStatement("SELECT * FROM general_ledger WHERE ledger_id=?");
				ps.setInt(1, ledger_id);
				
				rs= ps.executeQuery();
				GeneralLedger gl = new GeneralLedger();
				
				if(rs.next()) {
					
					gl.setLedger_id(rs.getInt(1));
					gl.setJournal_id(rs.getInt(2));
					gl.setAccount_title(AccountTitle.valueOf(rs.getString(3)));
					gl.setAll_debit(rs.getDouble(4));
					gl.setAll_credit(rs.getDouble(5));
					gl.setCreator_id(rs.getInt(6));
					gl.setTotal(rs.getDouble(7));
					}
				
				rs.close();
				ps.close();
				con.close();
				return gl;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	

	
	public static int deleteGeneralLedger(GeneralLedger gl) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM general_journal WHERE ledger_id=?");
			
			ps.setInt(1, gl.getLedger_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static int addGeneralLedger(GeneralLedger gl) {
		int status=0;
		try {
			Connection con=DBConnect.getConnection();
			PreparedStatement ps=con.prepareStatement("Insert into general_ledger value(null,?,?,?,?,?,?)");
			
			ps.setInt(1, gl.getJournal_id());
			ps.setString(2, gl.getAccount_title().toString());
			ps.setDouble(3, gl.getAll_debit());
			ps.setDouble(4, gl.getAll_credit());
			ps.setInt(5, gl.getCreator_id());
			ps.setDouble(6, gl.getTotal());
			
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch (Exception e) {e.printStackTrace();}
		return status;
	}
	
	
	public static int updateLedgerByJournalIdAndAccountTitle(int journal_id, AccountTitle accountTitle, double debit, double credit, int creator_id){
		int status = 0;
		
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT ledger_id, all_debit, all_credit FROM general_ledger where journal_id=? and account_title=?");
			
			ps.setInt(1, journal_id);
			ps.setString(2, accountTitle.toString());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				ps=con.prepareStatement("UPDATE general_ledger SET all_debit=?, all_credit=?, total=? WHERE ledger_id=?");
				
				double all_debit = rs.getDouble("all_debit") + debit;
				double all_credit = rs.getDouble("all_credit") + credit;
				
				ps.setDouble(1, all_debit);
				ps.setDouble(2, all_credit);
				ps.setDouble(3, all_debit - all_credit);
				ps.setInt(4, rs.getInt("ledger_id"));
			} else {
				ps = con.prepareStatement("INSERT INTO general_ledger VALUES(null, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, journal_id);
				ps.setString(2, accountTitle.toString());
				ps.setDouble(3, debit);
				ps.setDouble(4, credit);
				ps.setInt(5, creator_id);
				ps.setDouble(6, debit-credit);
			}
			
			status = ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int updateEditLedgerByJournalIdAndAccountTitle(int journal_id, AccountTitle accountTitle, int creator_id,double debit, double credit, double old_debit, double old_credit, String old_acc){
		int status = 0;
		
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM general_ledger where journal_id=? and account_title=?");
			
			ps.setInt(1, journal_id);
			ps.setString(2, old_acc);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				ps=con.prepareStatement("UPDATE general_ledger SET all_debit=?, all_credit=?, total=? WHERE ledger_id=?");
				
				double all_debit = rs.getDouble("all_debit") - old_debit;
				double all_credit = rs.getDouble("all_credit") - old_credit;
				
				ps.setDouble(1, all_debit);
				ps.setDouble(2, all_credit);
				ps.setDouble(3, all_debit - all_credit);
				ps.setInt(4, rs.getInt("ledger_id"));
				status = ps.executeUpdate();
			} 
			
			ps=con.prepareStatement("SELECT * FROM general_ledger where journal_id=? and account_title=?");
			ps.setInt(1, journal_id);
			ps.setString(2, accountTitle.toString());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				ps=con.prepareStatement("UPDATE general_ledger SET all_debit=?, all_credit=?, total=? WHERE ledger_id=?");
				
				double all_debit = rs.getDouble("all_debit") + debit;
				double all_credit = rs.getDouble("all_credit") + credit;
				
				ps.setDouble(1, all_debit);
				ps.setDouble(2, all_credit);
				ps.setDouble(3, all_debit - all_credit);
				ps.setInt(4, rs.getInt("ledger_id"));
			} else {
				ps = con.prepareStatement("INSERT INTO general_ledger VALUES(null, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, journal_id);
				ps.setString(2, accountTitle.toString());
				ps.setDouble(3, debit);
				ps.setDouble(4, credit);
				ps.setInt(5, creator_id);
				ps.setDouble(6, debit-credit);
			}
			
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static Map<GeneralLedger, List<JournalEntry>> getLedgersWithAccounts(int journal_id) {
		Map<GeneralLedger, List<JournalEntry>> ledgersForThisJournal = new HashMap<>();
		
		try {
			con = DBConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM general_ledger RIGHT JOIN journal_entry ON journal_entry.account_title = general_ledger.account_title WHERE general_ledger.journal_id = ? AND journal_entry.journal_id = ?");
			ps.setInt(1, journal_id);
			ps.setInt(2, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				GeneralLedger ledger = new GeneralLedger();
				ledger.setLedger_id(rs.getInt(1));
				
				boolean foundKey = false;
				
				// Check if nakalagay na ung ledger sa Map
				for(GeneralLedger key : ledgersForThisJournal.keySet()) {
					if(key.getLedger_id() == ledger.getLedger_id()) {
						ledger = key;
						foundKey = true;
						break;
					}
				}
				
				if(foundKey) {
					List<JournalEntry> entries = ledgersForThisJournal.get(ledger);
					
					JournalEntry entry = new JournalEntry();
					entry.setEntry_id(rs.getInt(8));
					entry.setJournal_id(rs.getInt(9));
					entry.setAccount_title(ledger.getAccount_title());
					entry.setDebit(rs.getDouble(11));
					entry.setCredit(rs.getDouble(12));
					entry.setDate(rs.getDate(13));
					entry.setCreator_id(rs.getInt(14));
					
					entries.add(entry);

					ledgersForThisJournal.put(ledger, entries);
				} else {
					ledger.setJournal_id(rs.getInt(2));
					ledger.setAccount_title(AccountTitle.valueOf(rs.getString(3)));
					ledger.setAll_debit(rs.getDouble(4));
					ledger.setAll_credit(rs.getDouble(5));
					ledger.setCreator_id(rs.getInt(6));
					ledger.setTotal(rs.getDouble(7));
					
					List<JournalEntry> entries = new ArrayList<>();
					
					JournalEntry entry = new JournalEntry();
					entry.setEntry_id(rs.getInt(8));
					entry.setJournal_id(rs.getInt(9));
					entry.setAccount_title(ledger.getAccount_title());
					entry.setDebit(rs.getDouble(11));
					entry.setCredit(rs.getDouble(12));
					entry.setDate(rs.getDate(13));
					entry.setCreator_id(rs.getInt(14));
					
					entries.add(entry);
					
					ledgersForThisJournal.put(ledger, entries);
				}
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ledgersForThisJournal;
	}
	
	public static int updateDeleteLedgerByJournalIdAndAccountTitle(int journal_id, AccountTitle accountTitle, double old_debit, double old_credit, String old_acc){
		int status = 0;
		
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM general_ledger where journal_id=? and account_title=?");
			
			ps.setInt(1, journal_id);
			ps.setString(2, old_acc);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				ps=con.prepareStatement("UPDATE general_ledger SET all_debit=?, all_credit=?, total=? WHERE ledger_id=?");
				
				double all_debit = rs.getDouble("all_debit") - old_debit;
				double all_credit = rs.getDouble("all_credit") - old_credit;
				
				ps.setDouble(1, all_debit);
				ps.setDouble(2, all_credit);
				ps.setDouble(3, all_debit - all_credit);
				ps.setInt(4, rs.getInt("ledger_id"));
				status = ps.executeUpdate();
			} 

			status = ps.executeUpdate();
			ps.close();
			con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return status;
	}
	
	public static List<GeneralLedger> getForIncomeStatement(int journal_id) {
		List<GeneralLedger> list = new ArrayList<>();
		
		try {
			con = DBConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM general_ledger WHERE journal_id = ? AND account_title IN ('SERVICE_REVENUE', 'RENT_EXPENSE', 'UTILITIES_EXPENSE', 'UTILITIES_EXPENSE', 'SUPPLIES_EXPENSE', 'DEPRECIATION_EXPENSE')");
			ps.setInt(1, journal_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				GeneralLedger gl=new GeneralLedger();
				
				gl.setLedger_id(rs.getInt("ledger_id"));
				gl.setJournal_id(rs.getInt("journal_id"));
				gl.setAccount_title(AccountTitle.valueOf(rs.getString("account_title")));
				gl.setAll_debit(rs.getDouble("all_debit"));
				gl.setAll_credit(rs.getDouble("all_credit"));
				gl.setCreator_id(rs.getInt("creator_id"));
				gl.setTotal(rs.getDouble("total"));
				
				list.add(gl);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
