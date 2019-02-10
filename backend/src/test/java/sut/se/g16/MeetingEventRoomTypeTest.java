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
public class MeetingEventRoomTypeTest {

	@Autowired
	private MeetingEventRoomTypeRepository meetingEventRoomTypeRepository;
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

	
}
