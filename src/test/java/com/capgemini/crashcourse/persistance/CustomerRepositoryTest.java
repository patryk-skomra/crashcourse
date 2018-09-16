package com.capgemini.crashcourse.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.crashcourse.persistence.entity.CompanyEntity;
import com.capgemini.crashcourse.persistence.entity.CustomerEntity;
import com.capgemini.crashcourse.persistence.entity.PersonEntity;
import com.capgemini.crashcourse.persistence.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerRepositoryTest {

	private static final String COMPANY_PHONE_NUMBER = "111-222-333";
	private static final String PERSON_PHONE_NUMBER = "999-888-777";

	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void loadData() {
		CompanyEntity company = createCompany();
		customerRepository.save(company);

		PersonEntity person = createPerson();
		customerRepository.save(person);
	}

	@Test
	public void shouldFindPersonByTelephoneNumber() {
		// when
		CustomerEntity customer = customerRepository.findFirstByTelephoneNumber(PERSON_PHONE_NUMBER);

		// then
		assertThat(customer).isNotNull();
		assertThat(customer.getTelephoneNumber()).isEqualTo(PERSON_PHONE_NUMBER);
		assertThat(customer).isInstanceOf(PersonEntity.class);
	}

	@Test
	public void shouldFindCompanyByTelephoneNumber() {
		// when
		CustomerEntity customer = customerRepository.findFirstByTelephoneNumber(COMPANY_PHONE_NUMBER);

		// then
		assertThat(customer).isNotNull();
		assertThat(customer.getTelephoneNumber()).isEqualTo(COMPANY_PHONE_NUMBER);
		assertThat(customer).isInstanceOf(CompanyEntity.class);
	}

	@Test
	public void shouldNotFindAnyCustomerByTelephoneNumber() {
		// when
		CustomerEntity customer = customerRepository.findFirstByTelephoneNumber("123-123-123");

		// then
		assertThat(customer).isNull();
	}

	private PersonEntity createPerson() {
		PersonEntity person = new PersonEntity();
		person.setTelephoneNumber(PERSON_PHONE_NUMBER);
		person.setFirstName("Jan");
		person.setLastName("Kowalski");
		person.setDateOfBirth(LocalDate.of(1990, 1, 1));
		return person;
	}

	private CompanyEntity createCompany() {
		CompanyEntity company = new CompanyEntity();
		company.setTelephoneNumber(COMPANY_PHONE_NUMBER);
		company.setNip("123-321");
		company.setName("Acme");
		return company;
	}
}
