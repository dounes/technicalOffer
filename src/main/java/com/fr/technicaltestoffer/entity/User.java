package com.fr.technicaltestoffer.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="USERAF")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="userID")
	private int userID;
	
	@Column(name="fullname")
	@NotBlank(message = "fullname is required")
	private String fullname;
	
	@Column(name="birthdate")
	@NotBlank(message = "birthDate is required")
	private LocalDate birthdate;
	
	@Column(name="country")
	@NotBlank(message = "country Of Residence is required")
	private String country;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone")
	private int phone;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User{" +
				"userID=" + userID +
				", fullname='" + fullname + '\'' +
				", birthdate=" + birthdate +
				", country='" + country + '\'' +
				", gender='" + gender + '\'' +
				", phone=" + phone +
				'}';
	}
}
