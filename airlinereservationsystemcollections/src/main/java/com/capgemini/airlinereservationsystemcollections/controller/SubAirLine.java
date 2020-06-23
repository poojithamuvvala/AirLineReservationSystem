package com.capgemini.airlinereservationsystemcollections.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.airlinereservationsystemcollections.bean.AdminInfo;
import com.capgemini.airlinereservationsystemcollections.bean.BookingsInfo;
import com.capgemini.airlinereservationsystemcollections.bean.FlightsInfo;
import com.capgemini.airlinereservationsystemcollections.bean.TicketRequestInfo;
import com.capgemini.airlinereservationsystemcollections.bean.UsersInfo;
import com.capgemini.airlinereservationsystemcollections.exception.AirLineReservationSystemException;
import com.capgemini.airlinereservationsystemcollections.repository.AirLineDataBase;
import com.capgemini.airlinereservationsystemcollections.services.AdminService;
import com.capgemini.airlinereservationsystemcollections.services.AdminServiceImpl;
import com.capgemini.airlinereservationsystemcollections.services.UserService;
import com.capgemini.airlinereservationsystemcollections.services.UserServiceImpl;
import com.capgemini.airlinereservationsystemcollections.validation.Validation;

public class SubAirLine {
	public static void airLineOperations() {

		AirLineDataBase.defaultDatabase();

		boolean flag = false;
		int checkId = 0;
		int capacity = 0;
		String checkName = null;
		String checkMobileno = null;
		String checkEmail = null;
		String checkPassword = null;
		int flightId = 0;
		String flightName = null;
		String flightSource = null;
		String flightDestination = null;
		LocalDate departureDate = null;
		LocalDate arrivalDate = null;
		LocalTime departureTime = null;
		LocalTime arrivalTime = null;
		Validation validation = new Validation();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				System.out.println("********************** WELCOME TO AIRLINE RESERVATION SYSTEM ********************");
				System.out.println("PRESS 1, to ENTER INTO ADMIN PAGE");
				System.out.println("PRESS 2, to ENTER INTO USER PAGE");
				System.out
						.println("===================================================================================");
				int i = scanner.nextInt();
				switch (i) {
				case 1:
					AdminService service = new AdminServiceImpl();
					do {
						try {
							System.out.println(
									"===================================================================================");
							System.out.println("PRESS 1, TO ADMIN REGISTERATION");
							System.out.println("PRESS 2, TO ADMIN LOGIN");
							System.out.println("PRESS 3, TO EXIT");
							System.out.println(
									"========================================================================================");
							int choice = scanner.nextInt();
							switch (choice) {

							case 1:
								do {
									try {
										System.out.println("Enter ID to Register as ADMIN : ");
										checkId = scanner.nextInt();
										validation.validatedId1(checkId);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("ID should consist of only digits");
										flag = false;
										scanner.next();
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Name to Register : ");
										checkName = scanner.next();
										validation.validatedName(checkName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should consists of only Alphabates");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter MobileNumber to Register : ");
										checkMobileno = scanner.next();
										validation.validatedMobile(checkMobileno);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("Mobile Number should consists of only numbers");
										flag = false;
										scanner.next();
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Email to Register : ");
										checkEmail = scanner.next();
										validation.validatedEmail(checkEmail);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println(
												"Enter proper email such that it should consist of numbers and alphabets");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Password :");
										checkPassword = scanner.next();
										validation.validatedPassword(checkPassword);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Password doesnt accept spaces ");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								AdminInfo bean = new AdminInfo();
								bean.setEmailId(checkEmail);
								bean.setPassword(checkPassword);
								bean.setAdminid(checkId);
								bean.setPassword(checkPassword);

								boolean check = service.RegisterAdmin(bean);
								if (check) {
									System.out.println("You have registered Successfully");
								} else {
									System.out.println("Already registered");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {

									AdminInfo authBean = service.authenticateAdmin(email, password);
									System.out.println("You have logged in successfully");
									System.out.println("Now you can perform the following operations:-");
									do {
										try {
											System.out.println(
													"===========================================================================");
											System.out.println("PRESS 1, TO ADD FLIGHTS");
											System.out.println("PRESS 2, TO SEARCH FLIGHT BY SOURCE");
											System.out.println("PRESS 3, TO  SEARCH FLIGHT BY DESTINATION");
											System.out.println("PRESS 4, TO SEARCH FLIGHT BY NAME");
											System.out.println("PRESS 5, TO CANCEL FLIGHT");
											System.out.println("PRESS 6, TO VIEW ALL FLIGHTS AND ITS DETAILS");
											System.out.println("PRESS 7, TO VIEW ALL BOOKINGS");
											System.out.println("PRESS 8, TO VIEW ALL USERS");
											System.out.println("PRESS 9, TO LOGOUT");
											System.out.println(
													"===========================================================================");
											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:

												do {
													System.out.println("Enter FlightID to add : ");
													flightId = scanner.nextInt();
													try {
														validation.validateId2(flightId);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Id should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter FlightName : ");
													flightName = scanner.next();
													try {
														validation.validatedName(flightName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("FlightName should contains only Alphabets");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter Source : ");
													flightSource = scanner.next();
													try {
														validation.validatedName(flightSource);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Source should contains only Alphabates");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													System.out.println("Enter Destination : ");
													flightDestination = scanner.next();
													try {
														validation.validatedName(flightDestination);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("Destination should contains only Alphabates");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter No.of seat Available in the Flight : ");
													capacity = scanner.nextInt();
													try {
														validation.validatedId1(capacity);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"no of Seats Available should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter the departure Time : ");
													int k=scanner.nextInt();
													int l=scanner.nextInt();
													int m=scanner.nextInt();
													departureTime = LocalTime.of(k,l,m);

													try {

														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("Departure Time should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													
													System.out.println("Enter the Arrival Time : ");
													
													departureTime = LocalTime.of(scanner.nextInt(), scanner.nextInt(),
															scanner.nextInt());

													try {

														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("ArrivalTime should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter the departure date : ");
													departureDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(),
															scanner.nextInt());

													try {

														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("Departure Date should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);
												do {
													System.out.println("Enter the arrival date : ");
													arrivalDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(),
															scanner.nextInt());

													try {

														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"noofSeatsAvailable should contains only digits");
													} catch (AirLineReservationSystemException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												FlightsInfo bean1 = new FlightsInfo();
												bean1.setFlightId(flightId);
												bean1.setFlightName(flightName);
												bean1.setSource(flightSource);
												bean1.setDestination(flightDestination);
												bean1.setCapacity(capacity);
												bean1.setArrivalTime(arrivalTime);
												bean1.setDepartureTime(departureTime);
												bean1.setDateOfArrival(arrivalDate);
												bean1.setDateOfDeparture(departureDate);
												boolean check2 = service.addFlight(bean1);
												if (check2) {
													System.out.println("Flight added to repository with id : " + flightId);
												} else {
													System.out.println("Flight already exist of id = " + flightId);
												}
												break;
											case 2:
												System.out.println("Search Flight Details by Source : ");
												String source = scanner.next();

												FlightsInfo bean3 = new FlightsInfo();
												bean3.setSource(source);
												List<FlightsInfo> flightSource1 = service.searchFlightBySource(source);
												System.out.println(
														"===========================================================================");
												System.out.println(String.format(
														"%-10s %-10s %-13s %-15s  %-15s %-15s%-15s %-15s %s",
														"FlightId", "FlightName", "Source", "Destination", "capacity",
														"ArrivalTime", "DepartureTime", "DepartureDate",
														"ArrivalDate"));
												for (FlightsInfo flightBean : flightSource1) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s%-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime(),
																flightBean.getDateOfDeparture(),
																flightBean.getDateOfArrival()));
													} else {
														System.out.println("No Flights are available with this Source");
													}
												}
												break;
											case 3:
												System.out.println("Search flight by Destination : ");
												String destination = scanner.next();

												FlightsInfo bean4 = new FlightsInfo();
												bean4.setDestination(destination);
												List<FlightsInfo> flightDestination1 = service
														.searchFlightByDestination(destination);
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : flightDestination1) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flights are available with this Destination");
													}
												}
												break;
											case 4:
												System.out.println(" SEARCH FLIGHT BY NAME : ");
												String name = scanner.next();

												FlightsInfo bean5 = new FlightsInfo();
												bean5.setFlightName(name);
												;
												List<FlightsInfo> fname = service.searchFlightByName(name);
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : fname) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flights are available with this Flight Name");
													}
												}
												break;
											case 5:

												int flightId3 = scanner.nextInt();
												if (flightId3 == 0) {
													System.out.println("Please Enter the Valid FlightId");
												} else {
													FlightsInfo bean6 = new FlightsInfo();
													bean6.setFlightId(flightId3);
													boolean remove = service.cancelFlight(flightId3);
													if (remove) {
														System.out
																.println("The Flight is removed of Id = " + flightId3);
													} else {
														System.out.println(
																"The Flight is not removed of Id = " + flightId3);
													}
												}
												break;
											case 6:
												List<FlightsInfo> info = service.viewAllFlights();
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));

												for (FlightsInfo flightBean : info) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flight are available in the Flight Details");
													}
												}
												break;
											case 7:
												List<BookingsInfo> info1 = service.viewAllBookings();
												System.out.println(
														"===========================================================================");

												System.out.println(String.format(
														"%-15s %-15s %-15s  %-15s  %-15s  %-15s  %-15s %s", "BookingId",
														"UserId", "NoOFSeatsBooked", "SeatNo", "DateOfBooking",
														"dateOfJourney", "Source", "Destination"));
												for (BookingsInfo bookingBean : info1) {
													if (bookingBean != null) {
														System.out.println(String.format(
																"%-15s %-15s %-15s  %-15s  %-15s  %-15s %-15s  %s",
																bookingBean.getBookingId(), bookingBean.getUserId(),
																bookingBean.getNoOfSeatsBooked(),
																bookingBean.getSeatNos(),
																bookingBean.getDateOfBooking(),
																bookingBean.getDateOfJourney(), bookingBean.getSource(),
																bookingBean.getDestination()));
													} else {
														System.out.println("No Bookings are available");
													}
												}
												break;

											case 8:
												List<UsersInfo> info2 = service.viewAllUsers();
												System.out.println(
														"===========================================================================");

												System.out.println(String.format("%-15s %-15s %-15s  %-15s  %s",
														"UserId", "UserName", "MailId", "Password", "PhoneNumber"));
												for (UsersInfo userBean : info2) {
													if (userBean != null) {
														System.out.println(String.format("%-10s %-10s %-13s  %-15s  %s",
																userBean.getUserId(), userBean.getUserName(),
																userBean.getEmailId(), userBean.getPassword(),
																userBean.getPhoneNumber()));
													} else {
														System.out.println("No Bookings are available");
													}
												}
												break;

											case 9:
												airLineOperations();

											default:
												System.out.println("Invalid Choice");
												break;
											}
										} catch (InputMismatchException e) {
											System.err.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;
							case 3:
								airLineOperations();
								break;

							default:
								System.out.println("Invalid Choice");
								break;
							}
						} catch (InputMismatchException e) {
							System.err.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}
					} while (true);
				case 2:
					UserService service1 = new UserServiceImpl();
					do {
						try {
							System.out.println(
									"===========================================================================");
							System.out.println("PRESS 1,To USER REGISTER");
							System.out.println("PRESS 2,TO USER LOGIN");
							System.out.println("PRESS 3,TO EXIT");
							System.out.println(
									"===========================================================================");
							int choice = scanner.nextInt();
							switch (choice) {
							case 1:

								do {
									try {
										System.out.println("Enter ID to Register as USER : ");
										checkId = scanner.nextInt();
										validation.validatedId1(checkId);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("ID should consist of only digits");
										flag = false;
										scanner.next();
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Name to Register : ");
										checkName = scanner.next();
										validation.validatedName(checkName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should consists of only Alphabates");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter MobileNumber to Register : ");
										checkMobileno = scanner.next();
										validation.validatedMobile(checkMobileno);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("Mobile Number  should consists of only numbers");
										flag = false;
										scanner.next();
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Email to Register : ");
										checkEmail = scanner.next();
										validation.validatedEmail(checkEmail);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println(
												"Enter proper email such that it should consist of numbers and alphabets");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								do {
									try {
										System.out.println("Enter Password :");
										checkPassword = scanner.next();
										validation.validatedPassword(checkPassword);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Password doesnt accept spaces ");
									} catch (AirLineReservationSystemException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);
								UsersInfo bean1 = new UsersInfo();
								bean1.setUserId(checkId);
								bean1.setUserName(checkName);
								bean1.setPhoneNumber(checkMobileno);
								bean1.setEmailId(checkEmail);
								bean1.setPassword(checkPassword);

								boolean check = service1.registerUser(bean1);
								if (check) {
									System.out.println("Registered Successfully");
								} else {
									System.out.println("Already registered");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {
									@SuppressWarnings("unused")
									UsersInfo userBean = service1.authenticateUser(email, password);
									System.out.println("Logged in Successfully");
									do {
										try {
											System.out.println(
													"===========================================================================");
											System.out.println("PRESS 1,TO SEARCH FLIGHT BY SOURCE");
											System.out.println("PRESS 2,TO SEARCH FLIGHT BY DESTINATION");
											System.out.println("PRESS 3,TO SEARCH FLIGHT BY NAME");
											System.out.println("PRESS 4,TO VIEW ALL FLIGHTDETAILS");
											System.out.println("PRESS 5,TO BOOK TICKET");
											System.out.println("PRESS 6,TO LOGOUT");
											System.out.println(
													"===========================================================================");
											int choice2 = scanner.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Search Flight Details by Source : ");
												String source = scanner.next();

												FlightsInfo bean3 = new FlightsInfo();
												bean3.setSource(source);
												List<FlightsInfo> flightSource1 = service1.searchBySource(source);
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : flightSource1) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println("No Flights are available with this Source");
													}
												}
												break;

											case 2:

												System.out.println("Search flight by Destination : ");
												String destination = scanner.next();

												FlightsInfo bean4 = new FlightsInfo();
												bean4.setDestination(destination);
												List<FlightsInfo> flightDestination1 = service1
														.searchByDestination(destination);
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : flightDestination1) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flights are available with this Destination");
													}
												}
												break;
											case 3:
												System.out.println(" SEARCH FLIGHT BY NAME : ");
												String name = scanner.next();

												FlightsInfo bean5 = new FlightsInfo();
												bean5.setFlightName(name);

												List<FlightsInfo> fname = service1.searchByName(name);
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : fname) {
													if (fname != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flights are available with this Flight Name");
													}
												}
												break;
											case 4:
												List<FlightsInfo> info = service1.getAllFlightDetails();
												System.out.println(
														"===========================================================================");
												System.out.println(
														String.format("%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																"FlightId", "FlightName", "Source", "Destination",
																"capacity", "ArrivalTime", "DepartureTime"));
												for (FlightsInfo flightBean : info) {
													if (flightBean != null) {
														System.out.println(String.format(
																"%-10s %-10s %-13s %-15s  %-15s %-15s %s",
																flightBean.getFlightId(), flightBean.getFlightName(),
																flightBean.getSource(), flightBean.getDestination(),
																flightBean.getCapacity(), flightBean.getArrivalTime(),
																flightBean.getDepartureTime()));
													} else {
														System.out.println(
																"No Flight are available in the Flight Details");
													}
												}
												break;
											case 5:
												System.out.println("Enter the details to book a ticket");
												System.out.println("Enter User Id : ");
												int userId = scanner.nextInt();
												UsersInfo user = new UsersInfo();
												user.setUserId(userId);
												System.out.println("Enter Flight Id : ");
												int flightId2 = scanner.nextInt();
												FlightsInfo flight = new FlightsInfo();
												flight.setFlightId(flightId2);
												System.out.println("Enter No of seats : ");
												int seats = scanner.nextInt();
												BookingsInfo bookingStatus = new BookingsInfo();
												bookingStatus.setNoOfSeatsBooked(seats);

												try {
													TicketRequestInfo request = service1.booktTicket(user, flight);
													System.out.println("Request placed to admin for booking a ticket");
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format(
															"%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
															"FlightId", "FlightName", "UserID", "UserName","ArrivalDate","DepartureDate","DepartureTime","ArrivalTime"));
													System.out.println(String.format(
															"%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
															request.getFlightInfo().getFlightId(),
															request.getFlightInfo().getFlightName(),
															request.getUserInfo().getUserId(),
															request.getUserInfo().getUserName(),
															request.getFlightInfo().getDateOfArrival(),
															request.getFlightInfo().getDateOfDeparture(),
															request.getFlightInfo().getDepartureTime(),
															request.getFlightInfo().getArrivalTime()
															));

												} catch (Exception e) {
													System.out.println("Invalid Request of booking");
												}
												break;
											case 6:
												airLineOperations();

											default:
												break;
											}
										} catch (InputMismatchException e) {
											System.err.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.err.println("Invalid Credentials");
								}
								break;
							case 3:
								airLineOperations();
								break;

							default:
								System.err.println("Invalid Choice, Enter either 1 or 2 or 3");
								
								break;
							}
						} catch (InputMismatchException e) { // if we give string in 1 n 2 n 3
							System.err.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}

					} while (true);

				}
			} catch (InputMismatchException e) { //// if we give string in 1 n 2
				System.err.println("Invalid entry please provide only positive integer");
				scanner.nextLine();
			}
		} while (true);
	}

}
