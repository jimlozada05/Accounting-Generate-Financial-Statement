package table;

public class FinancingActivity {

	private int financing_id;
	private int journal_id;
	private double financing_activity;
	private int creator_id;
	
	public int getFinancing_id() {
		return financing_id;
	}
	public void setFinancing_id(int financing_id) {
		this.financing_id = financing_id;
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
	public double getFinancing_activity() {
		return financing_activity;
	}
	public void setFinancing_activity(double financing_activity) {
		this.financing_activity = financing_activity;
	}
}
