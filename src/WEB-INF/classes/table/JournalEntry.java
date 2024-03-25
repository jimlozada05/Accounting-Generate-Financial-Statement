package table;

import java.sql.Date;

import util.AccountTitle;

public class JournalEntry {
	
	private int entry_id, journal_id, creator_id;
	private AccountTitle account_title;
	private Date date;
	private Double debit, credit;
	private int line_id;
	
	public int getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public AccountTitle getAccount_title() {
		return account_title;
	}
	public void setAccount_title(AccountTitle account_title) {
		this.account_title = account_title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	
	
	
}
