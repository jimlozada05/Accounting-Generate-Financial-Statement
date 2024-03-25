package table;

public class OperatingActivity {

	private int operating_id;
	private int journal_id;
	private double operating_activity;
	private int creator_id;
	
	public int getOperating_id() {
		return operating_id;
	}
	public void setOperating_id(int operating_id) {
		this.operating_id = operating_id;
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
	public double getOperating_activity() {
		return operating_activity;
	}
	public void setOperating_activity(double operating_activity) {
		this.operating_activity = operating_activity;
	}
}
