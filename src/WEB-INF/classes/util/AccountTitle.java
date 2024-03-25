package util;

public enum AccountTitle {
	CASH("Cash"),
	EQUIPMENT("Equipment"),
	SUPPLIES("Supplies"),
	ACCOUNTS_RECEIVABLE("Accounts Receivable"),
	ACCUMULATED_DEPRECIATION("Accumulated Depreciation"),
	PREPAID_EXPENSE("Prepaid Expense"),
	BUILDING("Building"),
	LAND("Land"),
	INVENTORY("Inventory"),
	
	UNEARNED_INCOME("Unearned Income"),
	ACCOUNTS_PAYABLE("Accounts Payable"),
	SALARIES_PAYABLE("Salaries Payable"),
	UTILITIES_PAYABLE("Utilities Payable"),
	INTERESTS_PAYABLE("Interests Payable"),
	NOTES_PAYABLE("Notes Payable"),
	LOANS_PAYABLE("Loans Payable"),
	MORTGAGE_PAYABLE("Mortgage Payable"),
	RENT_PAYABLE("Rent Payable"),
	LONG_TERM_PAYABL("Long Term Payable"),
	
	CAPITAL("Capital"),
	
	DRAWING("Drawing"),
	
	SERVICE_REVENUE("Service Revenue"),
	RENT_EXPENSE("Rent Expense"),
	UTILITIES_EXPENSE("Utilities Expense"),
	SALARIES_EXPENSE("Salaries Expense"),
	SUPPLIES_EXPENSE("Supplies Expense"),
	PERMITS_AND_LICENSE_EXPENSE("Permits and License Expenses"),
	DEPRECIATION_EXPENSE("Depreciation Expense");
	
	private String str;
	
	AccountTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
