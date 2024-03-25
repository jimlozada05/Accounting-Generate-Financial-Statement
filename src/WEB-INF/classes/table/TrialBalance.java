package table;

public class TrialBalance {

	private int balance_id;
	private int journal_id;
	private double total_debit;
	private double total_credit;
	private int creator_id;
	
	public int getBalance_id() {
		return balance_id;
	}
	public void setBalance_id(int balance_id) {
		this.balance_id = balance_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public double getTotal_debit() {
		return total_debit;
	}
	public void setTotal_debit(double total_debit) {
		this.total_debit = total_debit;
	}
	public double getTotal_credit() {
		return total_credit;
	}
	public void setTotal_credit(double total_credit) {
		this.total_credit = total_credit;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	
}
