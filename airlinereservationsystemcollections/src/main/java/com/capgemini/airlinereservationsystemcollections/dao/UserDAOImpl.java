package com.capgemini.airlinereservationsystemcollections.dao;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.airlinereservationsystemcollections.bean.FlightsInfo;
import com.capgemini.airlinereservationsystemcollections.bean.TicketRequestInfo;
import com.capgemini.airlinereservationsystemcollections.bean.UsersInfo;
import com.capgemini.airlinereservationsystemcollections.exception.AirLineReservationSystemException;
import com.capgemini.airlinereservationsystemcollections.repository.AirLineDataBase;

public class UserDAOImpl implements UserDAO {

	public UsersInfo authenticateUser(String email, String password) {
		for (UsersInfo user : AirLineDataBase.USERSINFO) {
			if ((user.getEmailId().equals(email)) && (user.getPassword().equals(password))) {
				return user;
			}
		}
		throw new AirLineReservationSystemException("Invalid Credentials");
	}

	public boolean registerUser(UsersInfo usersInfo) {
		for (UsersInfo user : AirLineDataBase.USERSINFO) {
			if ((user.getEmailId()).equals(usersInfo.getEmailId())) {
				return false;
			}
		}
		AirLineDataBase.USERSINFO.add(usersInfo);
		return true;
	}

	public List<FlightsInfo> searchBySource(String source) {
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
			throw new AirLineReservationSystemException("Flight with " + source + " not found");
		} else {
			return searchListBySource;
		}
	}

	public List<FlightsInfo> searchByName(String flightName) {
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
			throw new AirLineReservationSystemException("Flight with " + flightName + " not found");
		} else {
			return searchListByName;
		}
	}

	public List<FlightsInfo> searchByDestination(String destination) {
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
			throw new AirLineReservationSystemException("Flight with " + destination + " not found");
		} else {
			return searchListByDestination;
		}
	}

	public List<FlightsInfo> getAllFlightDetails() {
		List<FlightsInfo> flightList = new ArrayList<FlightsInfo>();
		for (FlightsInfo flight : AirLineDataBase.FLIGHTSINFO) {
			flight.getFlightId();
			flight.getFlightName();
			flight.getSource();
			flight.getDestination();
			flight.getArrivalTime();
			flight.getDepartureTime();
			flightList.add(flight);
		}
		return flightList;
	}

	public TicketRequestInfo booktTicket(UsersInfo usersInfo, FlightsInfo flightsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUserInfo( UsersInfo usersInfo1) {
		for (UsersInfo userInfo : AirLineDataBase.USERSINFO) {
			
			if ((userInfo.getEmailId().equalsIgnoreCase(usersInfo1.getEmailId()) && (userInfo.getPassword().equals(usersInfo1.getPassword())))) {
				userInfo.setPassword(usersInfo1.getPassword());
				userInfo.setAddress(usersInfo1.getAddress());
				userInfo.setPhoneNumber(usersInfo1.getPhoneNumber());
				userInfo.setUserName(usersInfo1.getUserName());
				return true;
			
		}
		}

		throw new AirLineReservationSystemException("Password Can't be Changed Due To Invalid Credentials");
	}

}
