package util;

public enum ExpenseTitle {
	RENT_EXPENSE("Rent Expense"),
	UTILITIES_EXPENSE("Utilities Expense"),
	SALARIES_EXPENSE("Salaries Expense"),
	SUPPLIES_EXPENSE("Supplies Expense"),
	PERMITS_AND_LICENSE_EXPENSE("Permits and License Expenses"),
	DEPRECIATION_EXPENSE("Depreciation Expense");
	
	private String str;
	
	ExpenseTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
