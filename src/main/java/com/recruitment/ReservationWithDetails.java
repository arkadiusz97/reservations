package com.recruitment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "detailedreservations")
public class ReservationWithDetails {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tenantName")
	private String tenantName;
	
	@Column(name = "tenantSurname")
	private String tenantSurname;
	
	@Column(name = "lessorName")
	private String lessorName;
	
	@Column(name = "lessorSurname")
	private String lessorSurname;
	
	@Column(name = "objectName")
	private String objectName;
	
	@Column(name = "firstDay")
	private LocalDateTime firstDay;
	
	@Column(name = "lastDay")
	private LocalDateTime lastDay;

	public ReservationWithDetails() {}
	
	public ReservationWithDetails(String tenantName, String tenantSurname,
	String lessorName, String lessorSurame, String objectName,
	LocalDateTime firstDay, LocalDateTime lastDay) {
		this.tenantName = tenantName;
		this.tenantSurname = tenantSurname;
		this.lessorName = lessorName;
		this.lessorSurname = lessorSurname;
		this.objectName = objectName;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
	}
	
	public String getTenantName() {
		return this.tenantName;
	}
	public String getTenantSurname() {
		return this.tenantSurname;
	}
	public String getLessorName() {
		return this.lessorName;
	}
	public String getLessorSurname() {
		return this.lessorSurname;
	}
	public String getObjectName() {
		return this.objectName;
	}
	public LocalDateTime getFirstDay() {
		return this.firstDay;
	}
	public LocalDateTime getLastDay() {
		return this.lastDay;
	}
	
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public void setTenantSurname(String tenantSurname) {
		this.tenantSurname = tenantSurname;
	}
	public void setLessorName(String lessorName) {
		this.lessorName = lessorName;
	}
	public void setLessorSurname(String lessorSurname) {
		this.lessorSurname = lessorSurname;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public void setFirstDay(LocalDateTime firstDay) {
		this.firstDay = firstDay;
	}
	public void setLastDay(LocalDateTime lastDay) {
		this.lastDay = lastDay;
	}
}