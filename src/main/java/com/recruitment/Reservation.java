package com.recruitment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tenantId")
	private int tenantId;
	
	@Column(name = "objectForRentId")
	private int objectForRentId;
	
	@Column(name = "firstDay")
	private LocalDateTime firstDay;
	
	@Column(name = "lastDay")
	private LocalDateTime lastDay;

	public Reservation() {}
	
	public Reservation(int tenantId, int objectForRentId,
	LocalDateTime firstDay, LocalDateTime lastDay) {
		this.tenantId = tenantId;
		this.objectForRentId = objectForRentId;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
	}
	
	public int getId() {
		return this.id;
	}
	public int getTenantId() {
		return this.tenantId;
	}
	public int getObjectForRentId() {
		return this.objectForRentId;
	}
	public LocalDateTime getFirstDay() {
		return this.firstDay;
	}
	public LocalDateTime getLastDay() {
		return this.lastDay;
	}
	
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public void setObjectForRentId(int objectForRentId) {
		this.objectForRentId = objectForRentId;
	}
	public void setFirstDay(LocalDateTime firstDay) {
		this.firstDay = firstDay;
	}
	public void setLastDay (LocalDateTime lastDay) {
		this.lastDay = lastDay;
	}
}
