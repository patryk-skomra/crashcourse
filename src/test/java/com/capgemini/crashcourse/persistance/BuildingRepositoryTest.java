package com.capgemini.crashcourse.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.crashcourse.persistence.entity.ApartmentEntity;
import com.capgemini.crashcourse.persistence.entity.BuildingEntity;
import com.capgemini.crashcourse.persistence.entity.Status;
import com.capgemini.crashcourse.persistence.repository.BuildingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingRepositoryTest {

	@Autowired
	private BuildingRepository buildingRepository;

	@Test
	public void shouldCalculatePercentageOfSoldApartmentsInBuildingWithSoldApartments() {
		// given
		BuildingEntity building = new BuildingEntity();
		building.setAddress("3-maja");
		building.setLiftAvailable(true);
		building.setNumberOfFlats(4);
		building.setNumberOfFloors(2);
		building.getApartments().add(createApartment(Status.SOLD, 75.0));
		building.getApartments().add(createApartment(Status.SOLD, 35.1));
		building.getApartments().add(createApartment(Status.FREE, 60.0));
		building.getApartments().add(createApartment(Status.FREE, 64.1));

		BuildingEntity savedBuilding = buildingRepository.save(building);

		// when
		int result = buildingRepository.percentageOfSoldApartmentsInBuilding(savedBuilding.getId());

		// then
		assertThat(result).isEqualTo(50);
	}

	@Test
	public void shouldCalculatePercentageOfSoldApartmentsInBuildingWithoutSoldApartments() {
		// given
		BuildingEntity building = new BuildingEntity();
		building.setAddress("3-maja");
		building.setLiftAvailable(true);
		building.setNumberOfFlats(2);
		building.setNumberOfFloors(2);
		building.getApartments().add(createApartment(Status.FREE, 60.0));
		building.getApartments().add(createApartment(Status.FREE, 64.1));

		BuildingEntity savedBuilding = buildingRepository.save(building);

		// when
		int result = buildingRepository.percentageOfSoldApartmentsInBuilding(savedBuilding.getId());

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldCalculateAverageAreaInBuilding() {
		// given
		BuildingEntity building = new BuildingEntity();
		building.setAddress("3-maja");
		building.setLiftAvailable(true);
		building.setNumberOfFlats(4);
		building.setNumberOfFloors(2);
		building.getApartments().add(createApartment(Status.SOLD, 75.1));
		building.getApartments().add(createApartment(Status.SOLD, 35.5));
		building.getApartments().add(createApartment(Status.FREE, 60.0));
		building.getApartments().add(createApartment(Status.FREE, 64.1));

		BuildingEntity savedBuilding = buildingRepository.save(building);

		// when
		double result = buildingRepository.averageApartmentsAreaInBuilding(savedBuilding.getId());

		// then
		assertThat(result).isEqualTo(58.675);
	}

	private ApartmentEntity createApartment(Status status, double area) {
		ApartmentEntity apartment = new ApartmentEntity();
		apartment.setStatus(status);
		apartment.setArea(area);
		apartment.setNumberOfRooms(3);
		apartment.setNumberOfBalconies(1);
		apartment.setFloor(1);
		apartment.setPrice(BigDecimal.valueOf(500000));
		return apartment;
	}
}
