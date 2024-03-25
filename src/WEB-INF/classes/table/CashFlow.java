package table;

public class CashFlow {

	private int cash_id;
	private int journal_id;
	private double cash;
	private double cash_flow;
	private int creator_id;
	
	public int getCash_id() {
		return cash_id;
	}
	public void setCash_id(int cash_id) {
		this.cash_id = cash_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getCash_flow() {
		return cash_flow;
	}
	public void setCash_flow(double cash_flow) {
		this.cash_flow = cash_flow;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
}
