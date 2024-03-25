package util;

public enum CurrentLiabilityTitle {
	UNEARNED_INCOME("Unearned Income"),
	ACCOUNTS_PAYABLE("Accounts Payable"),
	
	INTERESTS_PAYABLE("Interests Payable"),
	
	
	
	;
	
	private String str;
	
	CurrentLiabilityTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
