package com.capgemini.airlinereservationsystemcollections.bean;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TicketRequestInfo implements Serializable{
	private UsersInfo userInfo;
	private FlightsInfo flightInfo;
	private int noOfSeatsToBeBooked;

	public TicketRequestInfo() {
		super();
	}

	public TicketRequestInfo(UsersInfo userInfo, FlightsInfo flightInfo, int noOfSeatsToBeBooked) {
		super();
		this.userInfo = userInfo;
		this.flightInfo = flightInfo;
		this.noOfSeatsToBeBooked = noOfSeatsToBeBooked;
	}

}
