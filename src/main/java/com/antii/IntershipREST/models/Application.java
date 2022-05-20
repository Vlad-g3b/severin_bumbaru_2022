package com.antii.IntershipREST.models;

public class Application {
	private Internship internship = new Internship();
	private Company Company = new Company();
	private	String Status;
	private	IUserDetails student = new StudentDetails();
	public Application() {	
	}
	public Internship getInternship() {
		return internship;
	}
	public void setInternship(Internship internship) {
		this.internship = internship;
	}
	public Company getCompany() {
		return Company;
	}
	public void setCompany(Company company) {
		Company = company;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public IUserDetails getStudent() {
		return student;
	}
	public void setStudent(IUserDetails student) {
		this.student = student;
	}
	
	
}
