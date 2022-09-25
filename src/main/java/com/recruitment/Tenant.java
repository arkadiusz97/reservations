package com.recruitment;

import javax.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;

	public Tenant() {}
	
	public Tenant(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getSurname() {
		return this.surname;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
