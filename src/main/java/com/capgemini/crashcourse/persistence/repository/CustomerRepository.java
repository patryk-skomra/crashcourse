package com.capgemini.crashcourse.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.crashcourse.persistence.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	CustomerEntity findFirstByTelephoneNumber(String telephoneNumber);
}
