package table;

public class FinancialPosition {

	private int financial_id;
	private int journal_id;
	private double total_asset;
	private double total_liability;
	private int creator_id;
	
	public int getFinancial_id() {
		return financial_id;
	}
	public void setFinancial_id(int financial_id) {
		this.financial_id = financial_id;
	}
	public int getJournal_id() {
		return journal_id;
	}
	public void setJournal_id(int journal_id) {
		this.journal_id = journal_id;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	public double getTotal_asset() {
		return total_asset;
	}
	public void setTotal_asset(double total_asset) {
		this.total_asset = total_asset;
	}
	public double getTotal_liability() {
		return total_liability;
	}
	public void setTotal_liability(double total_liability) {
		this.total_liability = total_liability;
	}
}
