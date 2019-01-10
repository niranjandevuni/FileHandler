package com.prokarma.csv.configuration;

public class Configuration {

	public static String checkNullCondition(String value) {
		return (!value.isEmpty() && value != null) ? value: "";
	}
}
