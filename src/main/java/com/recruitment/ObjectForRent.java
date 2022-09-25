package com.recruitment;

import javax.persistence.*;

@Entity
@Table(name = "objectsforrent")
public class ObjectForRent {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "lessorId")
	private int lessorId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unitPrice")
	private int unitPrice;

	@Column(name = "surface")
	private int surface;
	
	@Column(name = "description")
	private String description;

	public ObjectForRent() {}
	
	public ObjectForRent(String name, int unitPrice, int surface, String description) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.surface = surface;
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	public int getLessorId() {
		return this.lessorId;
	}
	public String getName() {
		return this.name;
	}
	public int getUnitPrice() {
		return this.unitPrice;
	}
	public int getSurface() {
		return this.surface;
	}
	public String getDescription() {
		return this.description;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setLessorId(int lessorId) {
		this.lessorId = lessorId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setSurface(int surface) {
		this.surface = surface;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
