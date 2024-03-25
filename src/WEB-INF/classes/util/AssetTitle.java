package util;

public enum AssetTitle {
	CASH("Cash"),
	EQUIPMENT("Equipment"),
	SUPPLIES("Supplies"),
	ACCOUNTS_RECEIVABLE("Accounts Receivable"),
	ACCUMULATED_DEPRECIATION("Accumulated Depreciation"),
	PREPAID_EXPENSE("Prepaid Expense"),
	BUILDING("Building"),
	LAND("Land"),
	INVENTORY("Inventory");
	
	private String str;
	
	AssetTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
