package table;

import java.util.Comparator;

import util.AccountTitle;

public class GeneralLedger implements Comparator<GeneralLedger> {
	
	private int ledger_id,journal_id,creator_id;
	private AccountTitle account_title;
	private Double all_debit, all_credit, total;
	
	public int getLedger_id() {
		return ledger_id;
	}
	public void setLedger_id(int ledger_id) {
		this.ledger_id = ledger_id;
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
	public AccountTitle getAccount_title() {
		return account_title;
	}
	public void setAccount_title(AccountTitle account_title) {
		this.account_title = account_title;
	}
	public Double getAll_debit() {
		return all_debit;
	}
	public void setAll_debit(Double all_debit) {
		this.all_debit = all_debit;
	}
	public Double getAll_credit() {
		return all_credit;
	}
	public void setAll_credit(Double all_credit) {
		this.all_credit = all_credit;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	@Override
	public int compare(GeneralLedger arg0, GeneralLedger arg1) {
		if(arg0.getLedger_id() == arg1.getLedger_id())
			return 1;
		
		else return 0;
	}
	
}
