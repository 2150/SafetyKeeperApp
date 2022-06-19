package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.entity.Booking;
import com.app.repo.BookingRepo;

@Service
@EnableScheduling
public class BookingService {

	@Autowired
	private BookingRepo bookingRepo;
	
	private int totalSpaces = 3;
	
	private List<Booking> bookings;
	
	
	
	public BookingService() {
		this.bookings = new ArrayList();;
	}

	public Optional<Booking> getBookingById(Long id) {
		return bookingRepo.findById(id);
	}
	
	public List<Booking> getByStatus(String status) {
		return bookingRepo.getAllByStatus(status);
	}
	
	public Booking bookSpace(Booking booking) {
		System.out.println("Available: " + totalSpaces);
		booking.setTimeOfBooking(new Date());
		if (totalSpaces > 0) {
			totalSpaces--;
			return bookingRepo.save(booking);
		}
		this.bookings.add(booking);
		return null;
	}
	
	public Booking updateBooking(Booking booking) {
		Booking tempBooking = getBookingById(booking.getId()).get();
		if (tempBooking != null) {
//			Booking tempBooking = booking;
			String name = booking.getLuggageName();
			String desc = booking.getLuggageDescription();
			String reason = booking.getReason();
			String status = booking.getStatus();
			boolean withdraw = booking.getwithdraw();
			boolean collected = booking.getCollected();
			boolean isUpdated = false;
			
			if (name != null) {
				tempBooking.setLuggageName(name);
				isUpdated = true;
			}
			if (desc != null) {
				tempBooking.setLuggageDescription(desc);
				isUpdated = true;
			}
			if (reason != null) {
				tempBooking.setReason(reason);
				isUpdated = true;
			}
			if (status != null) {
				tempBooking.setStatus(status);
				isUpdated = true;
			}
			if (withdraw != false) {
				tempBooking.setWithdraw(withdraw);
				isUpdated = true;
			}
			if (collected != false) {
				tempBooking.setCollected(collected);
				tempBooking.setTimeCollected(new Date());
				isUpdated = true;
			}
			
			if (isUpdated) {
				tempBooking.setUpdated(true);
				tempBooking.setTimeOfUpdate(new Date());
			}
			return bookingRepo.save(tempBooking);
		}
		return null;
	}
	
	public void deleteById(Long id) {
		if (getBookingById(id) != null) {
			bookingRepo.deleteById(id);
			totalSpaces++;
		}
	}
	
	
	@Scheduled(fixedRate = 20000)
	private void evalSystem() {
		if (bookings.size() > 0) {
			sortBookings();
			if (totalSpaces > 0) {
				bookingRepo.save(bookings.get(0));
				bookings.remove(0);
			}
		}
		
		System.out.println("KGOTSO KGOTSO");
		
		// Update the temporary bookings ArrayList by removing withdrawn items 
		for (int i = 0; i < bookings.size(); i++) {
			Booking tmpBooking = bookings.get(i);
			if (tmpBooking.getwithdraw()) {
				bookings.remove(i);
			}
		}
		
		List<Booking> collectedLaggages = bookingRepo.getAllByCollected(true);
		int collectedLaggagesSize = collectedLaggages.size();
		if (collectedLaggagesSize > 0) {
			bookingRepo.deleteAll(collectedLaggages);
			totalSpaces += collectedLaggagesSize;
			
			while (totalSpaces > 0 && bookings.size() > 0) {
				bookingRepo.save(bookings.get(0));
				bookings.remove(0);
			}
		}
		
		// Update the database bookings by removing the withdrawn items
		// In case there are items on the temporary ArrayList add them to open database spaces
		List<Booking> withdrawnBookings = bookingRepo.getAllByWithdraw(true);
		int withdrawnBookingsSize = withdrawnBookings.size();
		if (withdrawnBookingsSize > 0) {
			bookingRepo.deleteAll(withdrawnBookings);
			totalSpaces += withdrawnBookingsSize;
			
			while (totalSpaces > 0 && bookings.size() > 0) {
				bookingRepo.save(bookings.get(0));
				bookings.remove(0);
			}
		}
	}
	
	private void sortBookings() {
		for (int i = 0; i < bookings.size(); i++) {
			for (int j = 0; j < bookings.size(); j++) {
				if (bookings.get(i).compareTo(bookings.get(j)) > 1) {
					Booking tempBooking = bookings.get(j);
					bookings.set(j, bookings.get(i));
					bookings.set(i, tempBooking);
				}
			}
		}
	}
}
