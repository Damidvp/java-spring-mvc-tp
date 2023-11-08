package fr.diginamic.springmvc.enums;

public enum Sex {
	M("M"), F("F");

	private String val;
	
	private Sex(String val) {
		this.setVal(val);
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
