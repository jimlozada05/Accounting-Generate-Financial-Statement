package table;

public class Journal {

	private int journal_id, total_acc, creator_id;
	private double total_debit, total_credit;
	private String type;
	
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public int getTotal_acc() {
		return total_acc;
	}
	public void setTotal_acc(int total_acc) {
		this.total_acc = total_acc;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}