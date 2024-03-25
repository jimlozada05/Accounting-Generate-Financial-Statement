package table;

public class OwnerEquity {

	private int equity_id;
	private int journal_id;
	private double end_capital;
	private int creator_id;
	public int getEquity_id() {
		return equity_id;
	}
	public void setEquity_id(int equity_id) {
		this.equity_id = equity_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public double getEnd_capital() {
		return end_capital;
	}
	public void setEnd_capital(double end_capital) {
		this.end_capital = end_capital;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
}
