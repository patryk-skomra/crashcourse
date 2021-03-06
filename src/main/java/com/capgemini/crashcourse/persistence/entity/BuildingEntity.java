package com.capgemini.crashcourse.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BUILDING")
public class BuildingEntity extends AbstractEntity {

	private String description;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private boolean liftAvailable;

	@Column(nullable = false)
	private Integer numberOfFlats;

	@Column(nullable = false)
	private Integer numberOfFloors;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "BUILDING_ID")
	Set<ApartmentEntity> apartments;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isLiftAvailable() {
		return liftAvailable;
	}

	public void setLiftAvailable(boolean liftAvailable) {
		this.liftAvailable = liftAvailable;
	}

	public Integer getNumberOfFlats() {
		return numberOfFlats;
	}

	public void setNumberOfFlats(Integer numberOfFlats) {
		this.numberOfFlats = numberOfFlats;
	}

	public Integer getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(Integer numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public Set<ApartmentEntity> getApartments() {
		if (apartments == null) {
			apartments = new HashSet<>();
		}
		return apartments;
	}

	public void setApartments(Set<ApartmentEntity> apartments) {
		this.apartments = apartments;
	}

}
