package com.capgemini.crashcourse.persistence.repository.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.crashcourse.persistence.repository.CustomizedBuildingRepository;

@Repository
public class CustomizedBuildingRepositoryImpl implements CustomizedBuildingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public double averageApartmentsAreaInBuilding(long buildingId) {
		Double result = this.entityManager
				.createQuery("SELECT AVG(a.area) " 
						+ "FROM BuildingEntity b " 
						+ "INNER JOIN b.apartments a " 
						+ "WHERE b.id = :buildingId", Double.class)
				.setParameter("buildingId", buildingId).getSingleResult();

		return Optional.ofNullable(result).orElse(0.0);
	}

	@Override
	public int percentageOfSoldApartmentsInBuilding(long buildingId) {
		Long result = this.entityManager
				.createQuery("SELECT COUNT(a.id) * 100 / b.numberOfFlats " 
						+ "FROM BuildingEntity b "
						+ "INNER JOIN b.apartments a " 
						+ "WHERE b.id = :buildingId AND a.status = 'SOLD' ", Long.class)
				.setParameter("buildingId", buildingId).getSingleResult();

		return Optional.ofNullable(result).orElse(0L).intValue();
	}

}
