package com.antii.IntershipREST.models;

import java.util.Arrays;

public class StudentDetails extends User  implements IUserDetails{

	private static final long serialVersionUID = -4309007234561407795L;
	private String profilePic;
	private String profileCv;
	private String profileName;
	private String profileSurname;
	private String profilePhone;
	private String profileAddress;
	private String profileSchool;
	private String[] profileSkills;
	private ProfessorDetails professor;
	
	public StudentDetails() {}
	public StudentDetails(int user_id,String username,String password,String role, String profilePic, String profileCv, String profileName, String profileSurname,
			String profilePhone, String profileAddress, String profileSchool, String[] profileSkills,
			ProfessorDetails professor) {
		super(user_id,username,password,role);
		this.profilePic = profilePic;
		this.profileCv = profileCv;
		this.profileName = profileName;
		this.profileSurname = profileSurname;
		this.profilePhone = profilePhone;
		this.profileAddress = profileAddress;
		this.profileSchool = profileSchool;
		this.profileSkills = profileSkills;
		this.professor = professor;
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
	public String getProfileSchool() {
		return profileSchool;
	}
	public void setProfileSchool(String profileSchool) {
		this.profileSchool = profileSchool;
	}
	public String[] getProfileSkills() {
		return profileSkills;
	}
	public void setProfileSkills(String[] profileSkills) {
		this.profileSkills = profileSkills;
	}
	public ProfessorDetails getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorDetails professor) {
		this.professor = professor;
	}
	@Override
	public String toString() {
		return "StudentDetails [profilePic=" + profilePic + ", profileCv=" + profileCv + ", profileName=" + profileName
				+ ", profileSurname=" + profileSurname + ", profilePhone=" + profilePhone + ", profileAddress="
				+ profileAddress + ", profileSchool=" + profileSchool + ", profileSkills="
				+ Arrays.toString(profileSkills) + ", professor=" + professor + ", getId()=" + getId()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getRole()=" + getRole()
				+ "]";
	}


}
