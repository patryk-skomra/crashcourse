package com.capgemini.crashcourse.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.crashcourse.persistence.entity.ApartmentEntity;
import com.capgemini.crashcourse.persistence.entity.Status;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

	List<ApartmentEntity> findByAreaBetween(double from, double to);

	List<ApartmentEntity> findByStatus(Status status);
}
