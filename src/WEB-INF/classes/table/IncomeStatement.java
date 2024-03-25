package table;

public class IncomeStatement {

	private int income_id;
	private int journal_id;
	private double net_worth;
	private int creator_id;
	
	public int getIncome_id() {
		return income_id;
	}
	public void setIncome_id(int income_id) {
		this.income_id = income_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public double getNet_worth() {
		return net_worth;
	}
	public void setNet_worth(double net_worth) {
		this.net_worth = net_worth;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
}
