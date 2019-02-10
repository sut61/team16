package sut.se.g16;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberHotelTest {


	@Autowired
	private MemberHotelRepository memberHotelRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Start Test Sprint #1
	// Test 2 filed of MemberHotelEntity contain NotNull And and @Column(unique =
	// true)
	@Test
	public void testEnterDataMemberHotelEntity() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Aphirat");
		mem.setMemberHotelPassword(1234L);
		memberHotelRepository.save(mem);
	}

	@Test
	public void testMemberHotelNameMusNotNull() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName(null);
		mem.setMemberHotelPassword(1234L);

		try {
			entityManager.persist(mem);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMemberHotelNameMustUnique() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Somsak");
		mem.setMemberHotelPassword(1234L);
		entityManager.persist(mem);
		entityManager.flush();

		MemberHotelEntity mem2 = new MemberHotelEntity();
		mem2.setMemberHotelName("Somsak");
		mem2.setMemberHotelPassword(1234L);

		try {
			entityManager.persist(mem2);
			entityManager.flush();

			fail("MemberHotelName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testMemberHotelNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testMemberHotelPasswordMustNotNull() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Aphirat");
		mem.setMemberHotelPassword(null);

		try {
			entityManager.persist(mem);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelPasswordMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

}