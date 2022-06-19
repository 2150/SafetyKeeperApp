package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Booking;
import com.app.service.BookingService;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@GetMapping("/bookings")
	public List<Booking> getBookings() {
		System.out.println("KGOTSO");
		return bookingService.getByStatus("closed");
	}
	
	@PostMapping("/book-space")
	public Booking bookSpace(@RequestBody Booking booking) {
		return bookingService.bookSpace(booking);
	}
	
	@GetMapping("/error")
	public void error() {
		System.out.println("Error");
	}
	
	@PatchMapping
	public Booking updateBooking(@RequestBody Booking booking) {
		return bookingService.updateBooking(booking);
	}
	
	@DeleteMapping("/delete/{id}")
	public int deleteBooking(@PathVariable Long id) {
		bookingService.deleteById(id);
		return 200;
	}
	
//	@Scheduled(fixedRate = 1000)
//	public void ev() {
//		System.out.println("Controller");
//		bookingService.evalSystem();
//	}
	
}
