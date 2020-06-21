package com.capgemini.airlinereservationsystemcollections.bean;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UsersInfo implements Serializable {
	private int userId;
	private String emailId;
	private String userName;
	private String password;
	private LocalDate dateOfBirth;
	private String address;
	private String phoneNumber;

	public UsersInfo() {
		super();
	}

	public UsersInfo(int userId, String emailId, String userName, String password, LocalDate dateOfBirth,
			String address, String phoneNumber) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	

}
