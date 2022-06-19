package com.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.thymeleaf.util.DateUtils;

@Entity
public class Booking implements Comparable<Booking>{

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String luggageName;
	
	private String luggageDescription;
	
	private String reason;
	
	private String status;
	
	private Date timeOfArrival;
	
	private boolean withdraw;
	
	private Date timeOfBooking;
	
	private Boolean updated;
	
	private Date timeOfUpdate;
	
	private boolean collected;
	
	private Date timeCollected;
	
	public Long getId() {
		return id;
	}
	
	public String getLuggageName() {
		return luggageName;
	}
	
	public String getLuggageDescription() {
		return luggageDescription;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Date getTimeOfBooking() {
		return timeOfBooking;
	}
	
	public boolean getwithdraw() {
		return withdraw;
	}
	
	public Boolean getUpdated() {
		return updated;
	}
	
	public Date getTimeOfUpdate() {
		return timeOfUpdate;
	}
	
	public Date getTimeOfArrival() {
		return timeOfArrival;
	}
	
	public boolean getCollected() {
		return collected;
	}
	
	public Date getTimeCollected() {
		return timeCollected;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLuggageName(String luggageName) {
		this.luggageName = luggageName;
	}
	
	public void setLuggageDescription(String luggageDescription) {
		this.luggageDescription = luggageDescription;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setTimeOfArrival(Date timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}
	
	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}
	
	public void setTimeOfBooking(Date timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	
	public void setUpdated(Boolean updated) {
		this.updated = updated;
	}
	
	public void setTimeOfUpdate(Date timeOfUpdate) {
		this.timeOfUpdate = timeOfUpdate;
	}
	
	public void setCollected(boolean isCollected) {
		this.collected = isCollected;
	}
	
	public void setTimeCollected(Date timeCollected) {
		this.timeCollected = timeCollected;
	}

	@Override
	public int compareTo(Booking objBooking) {
		System.out.println("Time: " + this);
		if (this.timeOfBooking.getHours() > objBooking.timeOfBooking.getHours()) {
			return 1;
		} else if (this.timeOfBooking.getHours() < objBooking.timeOfBooking.getHours()) {
			return -1;
		} else {
			if (this.timeOfBooking.getMinutes() > objBooking.timeOfBooking.getMinutes()) {
				return 1;
			} else if (this.timeOfBooking.getMinutes() < objBooking.timeOfBooking.getMinutes()) {
				return -1;
			} else {
				if (this.timeOfBooking.getHours() > objBooking.timeOfBooking.getSeconds()) {
					return 1;
				} else if (this.timeOfBooking.getHours() < objBooking.timeOfBooking.getSeconds()) {
					return -1;
				}
			}
		}
		return 0;
	}
	
}
