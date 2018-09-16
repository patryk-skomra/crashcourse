package com.capgemini.crashcourse.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.crashcourse.persistence.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

}
