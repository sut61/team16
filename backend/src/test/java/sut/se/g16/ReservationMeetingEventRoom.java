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

import java.net.SocketPermission;
import java.util.Collections;
import java.util.Date;
import java.util.OptionalInt;
import java.util.Set;

import javax.sound.sampled.SourceDataLine;
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
public class ReservationMeetingEventRoom {

	@Autowired
	private ReservationRoomRepository reservationRoomRepository;
	@Autowired
	private ReservationMeetingEventRoomRepository reservationMeetingEventRoomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private MeetingEventRoomRepository meetingEventRoomRepository;
	@Autowired
	private MeetingEventRoomTypeRepository meetingEventRoomTypeRepository;
	@Autowired
	private RoomStatusRepository roomStatusRepository;
	@Autowired
	private TypeTimeRepository typeTimeRepository;
	@Autowired
	private FunctionRepository functionRepository;
	


	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

// Test Case Sprint #2
	@Test
	public void testVadidValueAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add Pillow2");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2100, 2 - 1, 1));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testDetailCannotBeNullSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail(null);
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("1++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("1++++++++++++++++");
		}
	}

	@Test
	public void testDateInCannotBeNullSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add Pillow2");
		resermet.setReservationMeetingEventRoomDateIn(null);
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 1, 1));

		try {
			entityManager.persist(resermet);
			entityManager.flush();

		//	fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("2++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("2++++++++++++++++");
		}
	}

	@Test
	public void testDateOutCannotBeNullSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add Pillow2");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(null);

		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("3++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("3++++++++++++++++");
		}
	}

	@Test
	public void testDetailLengthMax6SP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add Pillow2BlanketTowelBlanketTowelBlanketTowel");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("4++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("4++++++++++++++++");
		}
	}

	@Test
	public void testDetailLengthMin6SP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add dd3f");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("5++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("5++++++++++++++++");
		}
	}

	@Test
	public void testDetailStartWithAToZSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("#dd Pillow2");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("6++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("6++++++++++++++++");
		}
	}

	@Test
	public void testDetailMustBeUnique() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setDetail("Add Pillow2");
		resermet.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));
		entityManager.persist(resermet);
		entityManager.flush();
		ReservationMeetingEventRoomEntity resermet2 = new ReservationMeetingEventRoomEntity();
		resermet2.setDetail("Add Pillow2");
		resermet2.setReservationMeetingEventRoomDateIn(new Date(2018 - 1900, 2 - 1, 1));
		resermet2.setReservationMeetingEventRoomDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(resermet2);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("12====================");
			e.printStackTrace();
			System.out.println("====================");

		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("122====================");
			System.out.println(e);
			e.printStackTrace();
			System.out.println("====================");

		}
	}


	@Test
	public void testVadidValueCustomerAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewCustomerEntity(customerRepository.findBycustomerUsername("WhanWhan55"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testCustomerCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewCustomerEntity(customerRepository.findBycustomerUsername("null"));
		
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("+++++++++++++++");
		}
	}


	@Test
	public void testVadidValueHotelAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewHotelEntity(hotelRepository.findByName("PhimaiIn"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testHotelCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewHotelEntity(hotelRepository.findByName("null"));
		
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("+++++++++++++++");
		}
	}


	@Test
	public void testVadidValueMeetingEventRoomTypeAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewMeetingEventRoomTypeEntity(meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName("??????"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testMeetingEventRoomTypeCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewMeetingEventRoomTypeEntity(meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName(null));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testVadidValueMeetingEventRoomAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewMeetingEventRoomTypeEntity(meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName("AA0000"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testMeetingEventRoomCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewMeetingEventRoomTypeEntity(meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName(null));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}


	@Test
	public void testVadidValueFunctionAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewFunctionEntity(functionRepository.findByName("???????"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testFunctionCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewFunctionEntity(functionRepository.findByName(null));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testVadidValueRoomStatusAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewRoomStatusEntity(roomStatusRepository.findByName("???????"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testRoomStatusCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewRoomStatusEntity(roomStatusRepository.findByName(null));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testVadidValueTypeTimsAllSP2() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewTypeTimeEntity(typeTimeRepository.findBytypeTimeName("Morning"));
		reservationMeetingEventRoomRepository.save(resermet);

	}

	@Test
	public void testTypeTimeCannotBeNull() {
		ReservationMeetingEventRoomEntity resermet = new ReservationMeetingEventRoomEntity();
		resermet.setNewTypeTimeEntity(typeTimeRepository.findBytypeTimeName(null));
		try {
			entityManager.persist(resermet);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	
}
