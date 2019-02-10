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
public class MeetingEventRoomTest {

	@Autowired
	private MeetingEventRoomRepository MeetingEventRoomRepository;
	@Autowired
	private MeetingEventRoomTypeRepository meetingEventRoomTypeRepository;
	@Autowired
	private TypeTimeRepository typeTimeRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// -----------------------------------------------------------------------------------------//
	// Start Test Case Sprint #2
	// Test field of MeetingEventRoomEntity 3 field contain @Size @Notnull @Pattern
	// and @Column(unique = true)
	@Test
	public void testVadidValueAllSP2() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("SuraSumana");
		met.setMeetingEventRoomNumber("SS4500");
		met.setMeetingEventRoomPrice(45000L);
		MeetingEventRoomRepository.save(met);
	}

	@Test
	public void testRoomNumberCannotBeNullSP2() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("SuraSumana");
		met.setMeetingEventRoomNumber(null);
		met.setMeetingEventRoomPrice(45000L);

		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomNumber must not be null");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberCannotBeNullSP2");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength6MoreThanSP2() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("SuraSumana");
		met.setMeetingEventRoomNumber("SS45000");
		met.setMeetingEventRoomPrice(45000L);

		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomNumber must be 5-6 character");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength5-6MoreThanSP2");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength5LessThanSP2() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("SuraSumana");
		met.setMeetingEventRoomNumber("SS40");
		met.setMeetingEventRoomPrice(45000L);

		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomNumber must be 5-6 character");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength5-6LessThanSP2");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberStartWithAToZSP2() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("SuraSumana");
		met.setMeetingEventRoomNumber("#S4500");
		met.setMeetingEventRoomPrice(45000L);
		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomNumber must start with 2 character");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberStartWithAToZSP2");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNameMustBeUnique() {
		MeetingEventRoomEntity met1 = new MeetingEventRoomEntity();
		met1.setMeetingEventRoomName("SuraSumana");
		met1.setMeetingEventRoomNumber("SS4500");
		met1.setMeetingEventRoomPrice(45000L);
		entityManager.persist(met1);
		entityManager.flush();

		MeetingEventRoomEntity met2 = new MeetingEventRoomEntity();
		met2.setMeetingEventRoomName("SuraSumana");
		met2.setMeetingEventRoomNumber("SS4501");
		met2.setMeetingEventRoomPrice(45000L);

		try {
			entityManager.persist(met2);
			entityManager.flush();
			fail("MeetingEventRoomName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNameMustBeUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testRoomNameMustBeUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testRoomNameMustNotNull() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName(null);
		met.setMeetingEventRoomNumber("AA4000");
		met.setMeetingEventRoomPrice(45000L);
		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomName must not null");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNameMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMeetingEventRoomPriceMustNotNull() {
		MeetingEventRoomEntity met = new MeetingEventRoomEntity();
		met.setMeetingEventRoomName("Surasummana");
		met.setMeetingEventRoomNumber("AA4000");
		met.setMeetingEventRoomPrice(null);
		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventRoomPrice must not null");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMeetingEventRoomPriceMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test field of MeetingEventRoomTypeEntity 1 field(meetingEventRoomTypeName)
	// contain @Notnull and @Column(unique = true)

	// TestInsertValidAllData
	@Test
	public void testEnterMeetingEventRoomTypeDataValid() {
		MeetingEventRoomTypeEntity met = new MeetingEventRoomTypeEntity();
		met.setMeetingEventRoomTypeName("Meeting");
		meetingEventRoomTypeRepository.save(met);
	}

	@Test
	public void testMeetingEventRoomTypeNameMustNotNull() {
		MeetingEventRoomTypeEntity met = new MeetingEventRoomTypeEntity();
		met.setMeetingEventRoomTypeName(null);
		try {
			entityManager.persist(met);
			entityManager.flush();

			fail("MeetingEventTypeName must not null");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMeetingEventRoomTypeNameMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMeetingEventRoomTypeNameMustUnique() {
		MeetingEventRoomTypeEntity met1 = new MeetingEventRoomTypeEntity();
		met1.setMeetingEventRoomTypeName("Meeting");
		entityManager.persist(met1);
		entityManager.flush();

		MeetingEventRoomTypeEntity met2 = new MeetingEventRoomTypeEntity();
		met2.setMeetingEventRoomTypeName("Meeting");

		try {
			entityManager.persist(met2);
			entityManager.flush();

			fail("MeetingEventTypeName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMeetingEventRoomTypeNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testMeetingEventRoomTypeNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	// Test field of TypeTimeEntity 1 field(TypeTimeName)
	// contain @Notnull

	// TestInsertValidAllData
	@Test
	public void testEnterTypeTimeValidData() {
		TypeTimeEntity tt = new TypeTimeEntity();
		tt.setTypeTimeName("Meeting");
		typeTimeRepository.save(tt);
	}

	@Test
	public void testTypeTimeNameMustNotNull() {
		TypeTimeEntity tt = new TypeTimeEntity();
		tt.setTypeTimeName(null);
		try {
			entityManager.persist(tt);
			entityManager.flush();

			fail("TypeTimeName must not null");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testTypeTimeNameMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
}
