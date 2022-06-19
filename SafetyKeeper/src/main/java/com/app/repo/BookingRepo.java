package com.app.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Booking;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {

	public List<Booking> getAllByStatus(String status);
	
	public List<Booking> getAllByWithdraw(boolean isWithdrawn);
	
	public List<Booking> getAllByCollected(boolean isCollected);
}
