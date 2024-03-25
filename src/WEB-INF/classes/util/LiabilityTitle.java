package util;

public enum LiabilityTitle {

	UNEARNED_INCOME("Unearned Income"),
	ACCOUNTS_PAYABLE("Accounts Payable"),
	SALARIES_PAYABLE("Salaries Payable"),
	UTILITIES_PAYABLE("Utilities Payable"),
	INTERESTS_PAYABLE("Interests Payable"),
	NOTES_PAYABLE("Notes Payable"),
	MORTGAGE_PAYABLE("Mortgage Payable"),
	LONG_TERM_PAYABL("Long Term Payable"),
	RENT_PAYABLE("Rent Payable");
	
	private String str;
	
	LiabilityTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
