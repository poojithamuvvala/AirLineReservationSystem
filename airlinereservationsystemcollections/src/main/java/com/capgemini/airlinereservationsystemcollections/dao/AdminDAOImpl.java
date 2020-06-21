package com.capgemini.airlinereservationsystemcollections.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.airlinereservationsystemcollections.bean.AdminInfo;
import com.capgemini.airlinereservationsystemcollections.bean.BookingsInfo;
import com.capgemini.airlinereservationsystemcollections.bean.FlightsInfo;
import com.capgemini.airlinereservationsystemcollections.bean.UsersInfo;
import com.capgemini.airlinereservationsystemcollections.exception.AirLineReservationSystemException;
import com.capgemini.airlinereservationsystemcollections.repository.AirLineDataBase;

public class AdminDAOImpl implements AdminDAO {

	public AdminInfo authenticateAdmin(String email, String password) {
		for (AdminInfo admin : AirLineDataBase.ADMININFO) {
			if ((admin.getEmailId().equals(email)) && (admin.getPassword().equals(password))) {
				return admin;
			}
		}
		throw new AirLineReservationSystemException("Invalid Credentials");
	}

	public boolean registerAdmin(AdminInfo newAdmin) {
		for (AdminInfo a1 : AirLineDataBase.ADMININFO) {
			if ((a1.getEmailId()).equals(newAdmin.getEmailId())) {
				return false;
			}
		}
		AirLineDataBase.ADMININFO.add(newAdmin);
		return true;
	}

	public boolean addFlight(FlightsInfo flightInfo) {
		for(FlightsInfo info: AirLineDataBase.FLIGHTSINFO) {
			if(info.getFlightId()==flightInfo.getFlightId()){
			     return false;	
			}
			
		}
		AirLineDataBase.FLIGHTSINFO.add(flightInfo);
		return true;
	}

	public boolean cancelFlight(int id) {
		boolean cancellationStatus = false;
		for (int i = 0; i <= AirLineDataBase.FLIGHTSINFO.size() - 1; i++) {
			FlightsInfo retrivedFlightInfo= AirLineDataBase.FLIGHTSINFO.get(i);
			int retrivedFlightId=retrivedFlightInfo.getFlightId();
			if(id==retrivedFlightId) {
				cancellationStatus=true;
				AirLineDataBase.FLIGHTSINFO.remove(i);
				break;
			}
			
		}
		return cancellationStatus;
	}

	public List<FlightsInfo> searchFlightByName(String flightName) {
		List<FlightsInfo> searchListByName = new ArrayList<FlightsInfo>();
		for (int i = 0; i <= AirLineDataBase.FLIGHTSINFO.size() - 1; i++) {
			FlightsInfo retrievedFlightInfo = AirLineDataBase.FLIGHTSINFO.get(i);
			String retrievedFname = retrievedFlightInfo.getFlightName();
			if (flightName.equals(retrievedFname)) {
				searchListByName.add(retrievedFlightInfo);
				return searchListByName;
			}
		}
		if (searchListByName.size() == 0) {
			throw new AirLineReservationSystemException("Flight with "+flightName+" not found");
		} else {
			return searchListByName;
		}
	}

	public List<FlightsInfo> searchFlightBySource(String source) {
		List<FlightsInfo> searchListBySource = new ArrayList<FlightsInfo>();
		for (int i = 0; i <= AirLineDataBase.FLIGHTSINFO.size() - 1; i++) {
			FlightsInfo retrievedFlight = AirLineDataBase.FLIGHTSINFO.get(i);
			String retrievedSourcename = retrievedFlight.getSource();
			if (source.equals(retrievedSourcename)) {
				searchListBySource.add(retrievedFlight);
				return searchListBySource;
			}
		}
		if (searchListBySource.size() == 0) {
			throw new AirLineReservationSystemException("Flight with "+source+" not found");
		} else {
			return searchListBySource;
		}
	}

	public List<FlightsInfo> searchFlightByDestination(String destination) {
		List<FlightsInfo> searchListByDestination = new ArrayList<FlightsInfo>();
		for (int i = 0; i <= AirLineDataBase.FLIGHTSINFO.size() - 1; i++) {
			FlightsInfo retrievedFlightInfo = AirLineDataBase.FLIGHTSINFO.get(i);
			String retrievedDestinationName = retrievedFlightInfo.getDestination();
			if (destination.equals(retrievedDestinationName)) {
				searchListByDestination.add(retrievedFlightInfo);
				return searchListByDestination;
			}
		}
		if (searchListByDestination.size() == 0) {
			throw new AirLineReservationSystemException("Flight with "+destination+" not found");
		} else {
			return searchListByDestination;
		}
	}

	public List<FlightsInfo> viewAllFlights() {
		List<FlightsInfo> flightsList = new ArrayList<FlightsInfo>();
		for (FlightsInfo flight : AirLineDataBase.FLIGHTSINFO) {
			flight.getFlightId();
			flight.getFlightName();
			flight.getSource();
			flight.getDestination();
			flight.getArrivalTime();
			flight.getDepartureTime();
			flightsList.add(flight);
		}
		return flightsList;
	}

	public List<UsersInfo> viewAllUsers() {
		List<UsersInfo> userList = new ArrayList<UsersInfo>();
		for (UsersInfo users : AirLineDataBase.USERSINFO) {
			users.getUserId();
			users.getUserName();
			users.getEmailId();
			users.getPhoneNumber();
			
			userList.add(users);
		}
		return  userList;
	}

	public List<BookingsInfo> viewAllBookings() {
		List<BookingsInfo> bookingsList = new ArrayList<BookingsInfo>();
		for (BookingsInfo booking : AirLineDataBase.BOOKINGSINFO) {
			booking.getBookingId();
			booking.getUserId();
			booking.getNoOfSeatsBooked();
			booking.getSeatNos();
			booking.getDateOfBooking();
			booking.getDateOfJourney();
			booking.getSource();
			booking.getDestination();
			bookingsList.add(booking);
		}
		return  bookingsList;
	}

}
