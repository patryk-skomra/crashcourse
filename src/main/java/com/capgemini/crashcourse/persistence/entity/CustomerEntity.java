package com.capgemini.crashcourse.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity extends AbstractEntity {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private String email;

	@Column(nullable = false)
	private String telephoneNrumber;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private AddressEntity address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNrumber() {
		return telephoneNrumber;
	}

	public void setTelephoneNrumber(String telephoneNrumber) {
		this.telephoneNrumber = telephoneNrumber;
	}

}
