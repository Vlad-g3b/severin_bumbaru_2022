package com.antii.IntershipREST.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Internship implements Serializable{
	
	private static final long serialVersionUID = 7739739481158806182L;
	private int id;
	private Company company;
	private String theme;
	private String domain;
	private String status;
	private String type;
	private Date startDate;
	private int duration;
	private int spots;
	private String[] skills; 
	private Time startHour;
	private Time endHour;
	private String city;
	private String description;
	
	public Internship() {}

	public Internship(int id, Company company, String theme, String domain, String status, String type, Date startDate,
			int duration, int spots, String[] skills, Time startHour, Time endHour, String city) {
		super();
		this.id = id;
		this.company = company;
		this.theme = theme;
		this.domain = domain;
		this.status = status;
		this.type = type;
		this.startDate = startDate;
		this.duration = duration;
		this.spots = spots;
		this.skills = skills;
		this.startHour = startHour;
		this.endHour = endHour;
		this.setCity(city);
	}

	public Time getStartHour() {
		return startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}

	public Time getEndHour() {
		return endHour;
	}

	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Internship other = (Internship) obj;
		return id == other.id;
	}


	public int getSpots() {
		return spots;
	}


	public void setSpots(int spots) {
		this.spots = spots;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
