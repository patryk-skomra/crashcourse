package com.capgemini.crashcourse.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.crashcourse.persistence.entity.ApartmentEntity;
import com.capgemini.crashcourse.persistence.entity.BuildingEntity;
import com.capgemini.crashcourse.persistence.entity.Status;
import com.capgemini.crashcourse.persistence.repository.ApartmentRepository;
import com.capgemini.crashcourse.persistence.repository.BuildingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApartmentRepositoryTest {

	@Autowired
	private ApartmentRepository apartmentRepository;

	@Autowired
	BuildingRepository buildingRepository;

	@Before
	public void loadData() {
		BuildingEntity building = new BuildingEntity();
		building.setAddress("3-maja");
		building.setLiftAvailable(true);
		building.setNumberOfFlats(3);
		building.setNumberOfFloors(2);
		building.getApartments().add(createApartment(Status.FREE, 75.0));
		building.getApartments().add(createApartment(Status.FREE, 35.1));
		building.getApartments().add(createApartment(Status.FREE, 60.0));
		building.getApartments().add(createApartment(Status.FREE, 64.1));

		buildingRepository.save(building);
	}

	@Test
	public void shouldFindApartmentsByStatus() {
		// when
		List<ApartmentEntity> apartments = apartmentRepository.findByStatus(Status.FREE);

		// then
		assertThat(apartments).hasSize(4);
		assertThat(apartments.stream().map(a -> a.getStatus()).allMatch(s -> s.equals(Status.FREE))).isTrue();
	}

	@Test
	public void shouldNotFindAnyApartmentsByStatus() {
		// when
		List<ApartmentEntity> apartments = apartmentRepository.findByStatus(Status.SOLD);

		// then
		assertThat(apartments).isEmpty();
	}

	@Test
	public void shouldFindApartmentsByArea() {
		// when
		List<ApartmentEntity> apartments = apartmentRepository.findByAreaBetween(60, 75);

		// then
		assertThat(apartments).hasSize(3);
		assertThat(apartments.stream().map(a -> a.getArea()).allMatch(s -> s.compareTo(60.0) >= 0)).isTrue();
		assertThat(apartments.stream().map(a -> a.getArea()).allMatch(s -> s.compareTo(75.0) <= 0)).isTrue();
	}

	@Test
	public void shouldNotFindAnyApartmentsByArea() {
		// when
		List<ApartmentEntity> apartments = apartmentRepository.findByAreaBetween(80, 100);

		// then
		assertThat(apartments).isEmpty();
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
