package domain;

import org.json.JSONObject;

public class Stock {
	private static final String[] COMPANY_NAMES = new String[] {"Motion Pictures", "Allied Steamships", "National Utilities", "General Radio", "United Railways", "Acme Motors"};
	private static final String[] FIELD_NAMES = new String[] {"companyName", "parValue", "loanValue", "firstDivident", "isMortgaged"};
			
	private String companyName;
	private int parValue;
	private int loanValue;
	private int firstDivident;
	private boolean isMortgaged;
	
	public Stock() {
		this.companyName = null;
		this.parValue = 0;
		this.loanValue = 0;
		this.firstDivident = 0;
		this.isMortgaged = false;
	}
	
	public Stock(String companyName, int parValue, int loanValue, int firstDivident, boolean isMortgaged) {
		this.companyName = companyName;
		this.parValue = parValue;
		this.loanValue = loanValue;
		this.firstDivident = firstDivident;
		this.isMortgaged = isMortgaged;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	public int getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(int loanValue) {
		this.loanValue = loanValue;
	}

	public int getFirstDivident() {
		return firstDivident;
	}

	public void setFirstDivident(int firstDivident) {
		this.firstDivident = firstDivident;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public static String[] getCompanyNames() {
		return COMPANY_NAMES;
	}

	public static String[] getFieldNames() {
		return FIELD_NAMES;
	}

	public static Stock fromJSON(JSONObject stockAsJSON) {
		Stock stock = null;
		
		try {
			String companyName = stockAsJSON.getString(FIELD_NAMES[0]);
			int parValue = stockAsJSON.getInt(FIELD_NAMES[1]);
			int loanValue = stockAsJSON.getInt(FIELD_NAMES[2]);
			int firstDivident = stockAsJSON.getInt(FIELD_NAMES[3]);
			boolean isMortgaged = stockAsJSON.getBoolean(FIELD_NAMES[4]);
			stock = new Stock(companyName, parValue, loanValue, firstDivident, isMortgaged);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stock;
	}
}
