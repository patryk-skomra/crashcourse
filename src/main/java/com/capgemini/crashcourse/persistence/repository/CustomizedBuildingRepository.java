package com.capgemini.crashcourse.persistence.repository;

public interface CustomizedBuildingRepository {

	double averageApartmentsAreaInBuilding(long buildingId);

	int percentageOfSoldApartmentsInBuilding(long buildingId);
}
