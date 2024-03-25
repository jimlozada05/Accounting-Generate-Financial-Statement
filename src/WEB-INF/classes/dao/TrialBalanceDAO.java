package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connect.*;
import table.*;
import util.AccountTitle;

public class TrialBalanceDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public static List<TrialBalance> getAllRecords(){
		List<TrialBalance> list=new ArrayList<TrialBalance>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from trial_balance");
			rs=ps.executeQuery();
			while(rs.next()) {
				TrialBalance tb=new TrialBalance();
				tb.setBalance_id(rs.getInt("balance_id"));
				tb.setJournal_id(rs.getInt("journal_id"));
				tb.setTotal_debit(rs.getDouble("total_debit"));
				tb.setTotal_credit(rs.getDouble("total_credit"));
				tb.setCreator_id(rs.getInt("creator_id"));
				list.add(tb);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<TrialBalance> getAllRecordsInCreator(int creator_id){
		List<TrialBalance> list=new ArrayList<TrialBalance>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from trial_balance WHERE creator_id=?");
			ps.setInt(1, creator_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				TrialBalance tb=new TrialBalance();
				tb.setBalance_id(rs.getInt("balance_id"));
				tb.setJournal_id(rs.getInt("journal_id"));
				tb.setTotal_debit(rs.getDouble("total_debit"));
				tb.setTotal_credit(rs.getDouble("total_credit"));
				tb.setCreator_id(rs.getInt("creator_id"));
				list.add(tb);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<TrialBalance> getARecord(int balance_id){
		List<TrialBalance> list=new ArrayList<TrialBalance>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM trial_balance where balance_id=?");
			
			ps.setInt(1, balance_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				TrialBalance tb=new TrialBalance();
				tb.setBalance_id(rs.getInt("balance_id"));
				tb.setJournal_id(rs.getInt("journal_id"));
				tb.setTotal_debit(rs.getDouble("total_debit"));
				tb.setTotal_credit(rs.getDouble("total_credit"));
				tb.setCreator_id(rs.getInt("creator_id"));
				list.add(tb);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int deleteTrialBalance(TrialBalance tb) {
		int status=0;
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM trial_balance WHERE balance_id=?");
			ps.setInt(1, tb.getBalance_id());
			
			status=ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int insertTotalTrialBalance(int journal_id, int creator_id) {
		int status=0;
		try {
			
			con=DBConnect.getConnection();
			ps=con.prepareStatement("INSERT INTO trial_balance VALUES (null, ?, (SELECT SUM(total) FROM general_ledger WHERE total > 0 AND journal_id = ?), (SELECT SUM(total) FROM general_ledger WHERE total < 0 AND journal_id=?), ?)");
			ps.setInt(1, journal_id);
			ps.setInt(2, journal_id);
			ps.setInt(3, journal_id);
			ps.setInt(4, creator_id);
			
			status=ps.executeUpdate();
			
			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static Map<TrialBalance, List<GeneralLedger>> getTrialBalanceWithLedger(int journal_id) {
		Map<TrialBalance, List<GeneralLedger>> balanceForThisJournal = new HashMap<>();
		
		try {
			con = DBConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM trial_balance INNER JOIN general_ledger ON trial_balance.journal_id = general_ledger.journal_id WHERE trial_balance.journal_id = ? AND general_ledger.journal_id= ?");
			ps.setInt(1, journal_id);
			ps.setInt(2, journal_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				TrialBalance tb = new TrialBalance();
				tb.setBalance_id(rs.getInt(1));
				
				boolean foundKey = false;
				
				// Check if nakalagay na ung ledger sa Map
				for(TrialBalance key : balanceForThisJournal.keySet()) {
					if(key.getBalance_id() == tb.getBalance_id()) {
						tb = key;
						foundKey = true;
						break;
					}
				}
				
				if(foundKey) {
					List<GeneralLedger> ledgers = balanceForThisJournal.get(tb);
					
					GeneralLedger ledger = new GeneralLedger();
					ledger.setLedger_id(rs.getInt(6));
					ledger.setJournal_id(rs.getInt(7));
					ledger.setAccount_title(AccountTitle.valueOf(rs.getString(8)));
					ledger.setAll_debit(rs.getDouble(9));
					ledger.setAll_credit(rs.getDouble(10));
					ledger.setCreator_id(rs.getInt(11));
					ledger.setTotal(rs.getDouble(12));
					
					ledgers.add(ledger);

					balanceForThisJournal.put(tb, ledgers);
				} else {
					tb.setBalance_id(1);
					tb.setJournal_id(2);
					tb.setTotal_debit(3);
					tb.setTotal_credit(4);
					tb.setCreator_id(5);
					
					List<GeneralLedger> ledgers = new ArrayList<>();
					
					GeneralLedger ledger = new GeneralLedger();
					ledger.setLedger_id(rs.getInt(6));
					ledger.setJournal_id(rs.getInt(7));
					ledger.setAccount_title(AccountTitle.valueOf(rs.getString(8)));
					ledger.setAll_debit(rs.getDouble(9));
					ledger.setAll_credit(rs.getDouble(10));
					ledger.setCreator_id(rs.getInt(11));
					ledger.setTotal(rs.getDouble(12));
					
					ledgers.add(ledger);

					balanceForThisJournal.put(tb, ledgers);
				}
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return balanceForThisJournal;
	}
	
	public static TrialBalance getABalance(int journal_id){
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM trial_balance where journal_id=?");
			ps.setInt(1, journal_id);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				TrialBalance tb=new TrialBalance();
				tb.setBalance_id(rs.getInt("balance_id"));
				tb.setJournal_id(rs.getInt("journal_id"));
				tb.setTotal_debit(rs.getDouble("total_debit"));
				tb.setTotal_credit(rs.getDouble("total_credit"));
				tb.setCreator_id(rs.getInt("creator_id"));
				return tb;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
