package com.recruitment;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ReservationsModel {
	private Configuration configuration;
	private SessionFactory factory;
	private DateTimeFormatter dateTimeFormatter;
	public ReservationsModel() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
			configuration = (Configuration) context.getBean("getConfig");
			factory = configuration.buildSessionFactory();
			context.close();
		}
		catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	private List<Reservation> getReservationsForObject(int objectForRentId) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Reservation> reservationsList;
		Query query = null;
		try {
			tx = session.beginTransaction();
			query = session.createQuery("FROM Reservation WHERE objectForRentId = :objectForRentId");
			query.setParameter("objectForRentId", objectForRentId);
			tx.commit();
			reservationsList = query.list();
		}
		catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
			throw new InternalError("Database error.");
		}
		finally {
			session.close();
		}
		return reservationsList;
	}
	private boolean checkIfObjectIsNotAlreadyReserved(int objectForRentId, String firstDay, String lastDay) {
		boolean objectIsAlreadyReserved = true;
		List<Reservation> reservationsForObject = getReservationsForObject(objectForRentId);
		LocalDateTime firstDayDateTime = LocalDateTime.parse(firstDay, dateTimeFormatter);
		LocalDateTime lastDayDateTime = LocalDateTime.parse(lastDay, dateTimeFormatter);
		for(Reservation reservation : reservationsForObject) {
			if(reservation.getLastDay().isAfter(lastDayDateTime)
			&& reservation.getFirstDay().isBefore(lastDayDateTime)
			|| reservation.getLastDay().isAfter(firstDayDateTime)
			&& reservation.getFirstDay().isBefore(firstDayDateTime)) {
				objectIsAlreadyReserved = false;
				break;
			}
		}
		return objectIsAlreadyReserved;
	}
	public List<ReservationWithDetails> getObjectsForRent(String field1, String field2, boolean byObjectName) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<ReservationWithDetails> objectsForRent;
		Query<ReservationWithDetails> query = null;
		try {
			tx = session.beginTransaction();
			if(byObjectName) {
				query = session.createQuery("FROM com.recruitment.ReservationWithDetails WHERE "
					+ "objectName = :objectName");
				query.setParameter("objectName", field1);
			} else {
				query = session.createQuery("FROM com.recruitment.ReservationWithDetails WHERE "
					+ "tenantName = :tenantName AND tenantSurname = :tenantSurname");
				query.setParameter("tenantName", field1);
				query.setParameter("tenantSurname", field2);
			}
			tx.commit();
			objectsForRent = query.list();
		}
		catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
			throw new InternalError("Database error.");
		}
		finally {
			session.close();
		}
		return objectsForRent;
	}
	public void updateReservation(int id, int tenantId, int objectForRentId,
	String firstDay, String lastDay) {
		if(!checkIfObjectIsNotAlreadyReserved(objectForRentId, firstDay, lastDay)) {
			throw new InternalError("Object is already reserved in this period.");
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Reservation reservation = 
				(Reservation)session.get(Reservation.class, id);
			if(reservation == null) {
				throw new InternalError("Couldn't find reservation with given id.");
			}
			if(tenantId > 0) {
				reservation.setTenantId(tenantId);
			}
			if(objectForRentId > 0) {
				reservation.setObjectForRentId(objectForRentId);
			}
			if(!firstDay.isBlank()) {
				LocalDateTime firstDayDateTime = LocalDateTime.parse(firstDay, dateTimeFormatter);
				reservation.setFirstDay(firstDayDateTime);
			}
			if(!lastDay.isBlank()) {
				LocalDateTime lastDayDateTime = LocalDateTime.parse(lastDay, dateTimeFormatter);
				reservation.setLastDay(lastDayDateTime);
			}
			session.update(reservation);
			tx.commit();
		}
		catch(HibernateException e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
			throw new InternalError("Database error.");
		}
		finally {
			session.close();
		}
	}
	public void createReservation(int tenantId, int objectForRentId, String firstDay, String lastDay) {
		if(!checkIfObjectIsNotAlreadyReserved(objectForRentId, firstDay, lastDay)) {
			throw new InternalError("Object is already reserved in this period.");
		}
		LocalDateTime firstDayDateTime = LocalDateTime.parse(firstDay, dateTimeFormatter);
		LocalDateTime lastDayDateTime = LocalDateTime.parse(lastDay, dateTimeFormatter);
		Reservation reservation = new Reservation(tenantId, objectForRentId, firstDayDateTime, lastDayDateTime);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(reservation);
			tx.commit();
		}
		catch(HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			throw new InternalError("Database error.");
		}
		finally {
			session.close();
		}
	}
}
