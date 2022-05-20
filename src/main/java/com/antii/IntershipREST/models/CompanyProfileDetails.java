package com.antii.IntershipREST.models;

public class CompanyProfileDetails extends User implements IUserDetails {

	private static final long serialVersionUID = -3875430102881960422L;
	private String profilePic;
	private String profilePhone;
	private Company company = new Company();
	public CompanyProfileDetails() {}
	public CompanyProfileDetails(int user_id,String username,String password,String role, String profilePic,String profilePhone,Company company) {
		super(user_id,username,password,role);
		this.profilePic = profilePic;
		this.profilePhone = profilePhone;
		this.setCompany(company);
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getProfilePhone() {
		return profilePhone;
	}
	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
