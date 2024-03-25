package table;

public class InvestingActivity {
	
	private int investing_id;
	private int journal_id;
	private double investing_activity;
	private int creator_id;
	
	public int getInvesting_id() {
		return investing_id;
	}
	public void setInvesting_id(int investing_id) {
		this.investing_id = investing_id;
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
	public double getInvesting_activity() {
		return investing_activity;
	}
	public void setInvesting_activity(double investing_activity) {
		this.investing_activity = investing_activity;
	}
}
