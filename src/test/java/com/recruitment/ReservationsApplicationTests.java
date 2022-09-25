package com.recruitment;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationsApplicationTests {
	@Autowired
	private ReservationsModel reservationsModel;
	private DateTimeFormatter dateTimeFormatter;
	public ReservationsApplicationTests() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	}
	@Test
	void testReservationForObject() {
		List<ReservationWithDetails> reservations = reservationsModel.getObjectsForRent("office", "", true);
		ReservationWithDetails reservation = reservations.get(0);
		Assertions.assertEquals("Jan", reservation.getTenantName());
		Assertions.assertEquals("Kowalski", reservation.getTenantSurname());
	}
	@Test
	void testReservationForTenant() {
		List<ReservationWithDetails> reservations = reservationsModel.getObjectsForRent("Adam", "Nowak", false);
		ReservationWithDetails reservation = reservations.get(0);
		Assertions.assertEquals("Karolina", reservation.getLessorName());
		Assertions.assertEquals("Wiśniewska", reservation.getLessorSurname());
	}
	@Test
	void testUpdateReservation() {
		String firstDateString = "2022-10-09 00:00";
		String lastDateString = "2022-10-10 00:00";
		reservationsModel.updateReservation(1, 1, 2, firstDateString, lastDateString);
		List<ReservationWithDetails> reservations = reservationsModel.getObjectsForRent("office", "", true);
		ReservationWithDetails reservation = reservations.get(0);
		
		LocalDateTime firstDayDateTime = LocalDateTime.parse(firstDateString, dateTimeFormatter);
		LocalDateTime lastDayDateTime = LocalDateTime.parse(lastDateString, dateTimeFormatter);
		
		Assertions.assertFalse(reservation.getFirstDay().isBefore(firstDayDateTime));
		Assertions.assertFalse(reservation.getFirstDay().isAfter(firstDayDateTime));
		Assertions.assertFalse(reservation.getLastDay().isBefore(lastDayDateTime));
		Assertions.assertFalse(reservation.getLastDay().isAfter(lastDayDateTime));
	}
	@Test
	void testCreateReservation() {
		String firstDateString = "2022-10-12 00:00";
		String lastDateString = "2022-10-16 00:00";
		reservationsModel.createReservation(1, 1, firstDateString, lastDateString);
		List<ReservationWithDetails> reservations = reservationsModel.getObjectsForRent("apartment", "", true);
		ReservationWithDetails reservation = reservations.get(1);
		Assertions.assertEquals("apartment", reservation.getObjectName());
	}
}
