package com.prokarma.csv.configuration;

public enum Constants {

	EMP_ID(0), PREFIX(1), FIRST_NAME(2), LAST_NAME(3), MIDDLE_NAME(4), SALARY(5), GENDER(6), STREET(7), CITY(8), ACTIVE(
			9), CSV_HEADER("Emp ID,Prefix,First Name,Last Name,Middle Name,Salary,Gender,Street,city,Active");

	private int intValue;

	private String stringValue;

	private Constants(int intValue) {
		this.intValue = intValue;
	}

	private Constants(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public int getIntValue() {
		return intValue;
	}
}
