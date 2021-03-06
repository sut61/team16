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
public class RoomTest {

    @Autowired
    private RoomRepository roomRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Start Test Sprint #1
	// Test field 2 field of RoomEntity Contain annotation @NotNull @Size @Pattern
	@Test
	public void testVadidValueAll() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A407");
		room.setRoomPrice(650L);
		roomRepository.save(room);
	}

	@Test
	public void testRoomNumberCannotBeNull() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber(null);
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberCannotBeNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength4To6LessThan4() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A47");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();
			fail("RoomNumber Should 4-6 character ");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength4To6LessThan4");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength4To6MoreThan6() {
		;
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A400007");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();
			fail("RoomNumber Should 4-6 character ");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength4To6MoreThan6");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberStartWithAToZ() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("#40007");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("First RoomNumber must A-Z or a-z and follow by digit");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberStartWithAToZ");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomPriceCannotBeNull() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A407");
		room.setRoomPrice(null);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomPriceCannotBeNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
}