package com.antii.IntershipREST.models;

import java.io.Serializable;
import java.sql.Date;
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
	
	public Internship() {}
	

	public Internship(int id, Company company, String theme, String domain, String status, String type, Date startDate,
			int duration, int spots) {
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
	
	
	
}
