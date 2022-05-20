package com.antii.IntershipREST.models;

public class Criteria {
	Item type;
	Item domain;
	
	public Criteria() {
	}
	public Item getType() {
		return type;
	}
	public void setType(Item type) {
		this.type = type;
	}
	public Item getDomain() {
		return domain;
	}
	public void setDomain(Item domain) {
		this.domain = domain;
	}
}
class Item{
	private String key;
	private String[] value;
	public Item() {}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	
}