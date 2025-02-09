package com.cjc.main.sbi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
	
	@Id
	@Column(name="Account_No",nullable=false,unique=true)
	private long accno;
	
	@Column(name="First_Name",nullable=false,unique=false)
	private String name;
	
	@Column(name="Middle_Name",nullable=true)
	private String midname;
	
	@Column(name="Last_Name",nullable=false,unique=false)
	private String surname;
	
	@Column(name="Gender",nullable=false)
	private String gender;
	
	@Column(name="Age",nullable=false)
	private int age;
	
	@Column(name="Contact No.",nullable=false,unique=true)
	private long mobno;
	
	@Column(name="Aadhar_No.",nullable=false,unique=true)
	private long aadharno;
	
	@Column(name="Permanent_Address",nullable=false)
	private String address;
	
	@Column(name="Account_Balance",nullable=false)
	private double balance;

	public long getAccno() {
		return accno;
	}

	public void setAccno(long accno) {
		this.accno = accno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getMobno() {
		return mobno;
	}

	public void setMobno(long mobno) {
		this.mobno = mobno;
	}

	public long getAadharno() {
		return aadharno;
	}

	public void setAadharno(long aadharno) {
		this.aadharno = aadharno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
