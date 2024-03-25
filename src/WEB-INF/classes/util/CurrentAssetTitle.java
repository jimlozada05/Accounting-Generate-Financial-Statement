package util;

public enum CurrentAssetTitle {

	SUPPLIES("Supplies"),
	ACCOUNTS_RECEIVABLE("Accounts Receivable"),
	INVENTORY("Inventory");
	
	private String str;
	
	CurrentAssetTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}