package com.antii.IntershipREST.models;

public class TutorDetails extends User implements IUserDetails {

	private static final long serialVersionUID = -3875430102881960422L;
	private String profilePic;
	private String profileCv;
	private String profileName;
	private String profileSurname;
	private String profilePhone;
	private String profileAddress;
	private String[] profileSkills;
	
	public TutorDetails() {}
	public TutorDetails(int user_id,String username,String password,String role, String profilePic, String profileCv, String profileName, String profileSurname,
			String profilePhone, String profileAddress, String profileSchool, String[] profileSkills) {
		super(user_id,username,password,role);
		this.profilePic = profilePic;
		this.profileCv = profileCv;
		this.profileName = profileName;
		this.profileSurname = profileSurname;
		this.profilePhone = profilePhone;
		this.profileAddress = profileAddress;
		this.profileSkills = profileSkills;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getProfileCv() {
		return profileCv;
	}
	public void setProfileCv(String profileCv) {
		this.profileCv = profileCv;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileSurname() {
		return profileSurname;
	}
	public void setProfileSurname(String profileSurname) {
		this.profileSurname = profileSurname;
	}
	public String getProfilePhone() {
		return profilePhone;
	}
	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}
	public String getProfileAddress() {
		return profileAddress;
	}
	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}
	public String[] getProfileSkills() {
		return profileSkills;
	}
	public void setProfileSkills(String[] profileSkills) {
		this.profileSkills = profileSkills;
	}
}
