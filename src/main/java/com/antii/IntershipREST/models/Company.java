package com.antii.IntershipREST.models;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {

	private static final long serialVersionUID = 1243495334503594175L;
	private int id;
	private String name;
	private String address;
	private String description;
	private String status;
	
	public Company() {}
	
	public Company(int id, String name, String address, String description, String status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		Company other = (Company) obj;
		return id == other.id;
	}
	
	
}
