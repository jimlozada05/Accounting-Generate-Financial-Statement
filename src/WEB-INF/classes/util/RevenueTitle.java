package util;

public enum RevenueTitle {
	SERVICE_REVENUE("Service Revenue");

	
	private String str;
	
	RevenueTitle(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
}
