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
public class RoomTypeTest {

	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Start Test Sprint #1
	// Test 4 filed of RoomTypeEntity contain NotNull And and @Column(unique = true)
	@Test
	public void testEnterDataRoomTypeEntity() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");
		roomTypeRepository.save(rt);
	}

	@Test
	public void testRoomTypeNameMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName(null);

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomTypeNameMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomTypeNameMustUnique() {
		RoomTypeEntity rt1 = new RoomTypeEntity();
		rt1.setBedType("Twice");
		rt1.setMaxPeople(3L);
		rt1.setNumberOfBed(1L);
		rt1.setRoomTypeName("VIP");
		entityManager.persist(rt1);
		entityManager.flush();

		RoomTypeEntity rt2 = new RoomTypeEntity();
		rt2.setBedType("Single");
		rt2.setMaxPeople(3L);
		rt2.setNumberOfBed(1L);
		rt2.setRoomTypeName("VIP");

		try {
			entityManager.persist(rt2);
			entityManager.flush();

			fail("RoomTypeName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomTypeNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testRoomTypeNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testBedTypeMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType(null);
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testBedTypeMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMaxPeopleMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(null);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMaxPeopleMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testNumberOfBedMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(null);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testNumberOfBedMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    }
}