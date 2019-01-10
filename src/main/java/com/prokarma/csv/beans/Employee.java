package com.prokarma.csv.beans;

public class Employee {

	private int empId;
	private String prefix;
	private String firstName;
	private String lastName;
	private String middleName;
	private double salary;
	private String gender;
	private String Street;
	private String city;
	private boolean active;

	public Employee() {
	}

	public Employee(int empId, String prefix, String firstName, String lastName, String middleName, double salary,
			String gender, String Street, String city, boolean active) {
		this.empId = empId;
		this.prefix = prefix;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.salary = salary;
		this.gender = gender;
		this.Street = Street;
		this.city = city;
		this.active = active;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", prefix=" + prefix + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", salary=" + salary + ", gender=" + gender + ", Street=" + Street
				+ ", city=" + city + ", active=" + active + "]";
	}

}
