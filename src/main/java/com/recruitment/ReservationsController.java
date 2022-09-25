package com.recruitment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationsController {
	private ReservationsModel reservationsModel;
	public ReservationsController(ReservationsModel reservationsModel) {
		this.reservationsModel = reservationsModel;
	}
	@RequestMapping("/getreservationsforobject")
	public ResponseEntity<List<ReservationWithDetails>> getObjectsForRent(
	@RequestParam(value="objectname", defaultValue="") String objectName) {
		List<ReservationWithDetails> reservationsWithDetails = null;
		try {
			reservationsWithDetails = reservationsModel.getObjectsForRent(objectName, "", true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(reservationsWithDetails);
	}
	
	@RequestMapping("/getreservationsfortenant")
	public ResponseEntity<List<ReservationWithDetails>> getObjectsForTenant(
	@RequestParam(value="tenantname", defaultValue="") String tenantName,
	@RequestParam(value="tenansurname", defaultValue="") String tenantSurname) {
		List<ReservationWithDetails> reservationsWithDetails = null;
		try {
			reservationsWithDetails = reservationsModel.getObjectsForRent(tenantName, tenantSurname, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(reservationsWithDetails);
	}
	
	@PutMapping("/updatereservation")
	public ResponseEntity<String> updateReservation(
	@RequestParam(value="id", defaultValue="") int id,
	@RequestParam(value="tenantid", defaultValue="") int tenantId,
	@RequestParam(value="objectforrentid", defaultValue="") int objectForRentId,
	@RequestParam(value="firstday", defaultValue="") String firstDay,
	@RequestParam(value="lastday", defaultValue="") String lastDay) {
		try {
			reservationsModel.updateReservation(id, tenantId,
				objectForRentId, firstDay, lastDay);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	@PostMapping("/createreservation")
	public ResponseEntity<String> getObjectsForTenant(
	@RequestParam(value="tenantid", defaultValue="") int tenantId,
	@RequestParam(value="objectforrentid", defaultValue="") int objectForRentId,
	@RequestParam(value="firstday", defaultValue="") String firstDay,
	@RequestParam(value="lastday", defaultValue="") String lastDay) {
		try {
			reservationsModel.createReservation(tenantId,
				objectForRentId, firstDay, lastDay);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}