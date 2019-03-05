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
public class ReservationRoom {

	@Autowired
	private ReservationRoomRepository reservationRoomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private StatusPaymentRepository statusPaymentRepository;
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

	@Test
	public void testVadidValueAll() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add Pees2");
		reservationRoomRepository.save(reserroom);
	}

	@Test
	public void testReserservationRoomCannotBeNull() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom(null);

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testReservationRoomLength10T40() {
		;
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add asadafgdhf1dfgfgfsdfddf");

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testDateIn1CannotBeNullSP2() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add Pillow2");
		reserroom.setDateIn(null);
		reserroom.setDateOut(new Date(2018 - 1900, 1, 1));

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
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
	public void testDateOut1CannotBeNullSP2() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add Pillow2");
		reserroom.setDateIn(new Date(2018 - 1900, 1, 1));
		reserroom.setDateIn(null);

		try {
			entityManager.persist(reserroom);
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
	public void testCommentLengthMax6SP2() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add Pillow2BlanketTowelBlanketTowelBlanketTowel");
		reserroom.setDateIn(new Date(2018 - 1900, 2 - 1, 1));
		reserroom.setDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(reserroom);
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
	public void testCommentLengthMin6SP2() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("Add dd3f");
		reserroom.setDateIn(new Date(2018 - 1900, 2 - 1, 1));
		reserroom.setDateIn(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(reserroom);
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
	public void testCommentStartWithAToZSP2() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setCommentReserroom("#dd Pillow2");
		reserroom.setDateIn(new Date(2018 - 1900, 2 - 1, 1));
		reserroom.setDateOut(new Date(2018 - 1900, 2 - 1, 1));
		try {
			entityManager.persist(reserroom);
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
	public void testCommentMustBeUnique() {
		ReservationRoomEntity reserroom1 = new ReservationRoomEntity();
		reserroom1.setCommentReserroom("Add Pillow2");
		reserroom1.setDateIn(new Date(2018 - 1900, 2 - 1, 1));
		reserroom1.setDateOut(new Date(2018 - 1900, 2 - 1, 1));
		entityManager.persist(reserroom1);
		entityManager.flush();
		ReservationRoomEntity reserroom2 = new ReservationRoomEntity();
		reserroom2.setCommentReserroom("Add Pillow2");
		reserroom2.setDateIn(new Date(2018 - 1900, 2 - 1, 1));
		reserroom2.setDateOut(new Date(2018 - 1900, 2 - 1, 1));

		try {
			entityManager.persist(reserroom2);
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
	public void testVadidValueHotelAll() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewHotelEntity(hotelRepository.findByName("PhiMaiIn"));
		reservationRoomRepository.save(reserroom);
	}

	@Test
	public void testHotelCannotBeNull() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewHotelEntity(hotelRepository.findByName(null));

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testVadidValueCustomerAll() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewCustomerEntity(customerRepository.findCustomerByName("PhiMaiIn"));
		reservationRoomRepository.save(reserroom);
	}

	@Test
	public void testCustomerCannotBeNull() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewCustomerEntity(customerRepository.findCustomerByName(null));

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}


	@Test
	public void testVadidValuePromotionAll() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewPromotionEntity(promotionRepository.findByPromotionName("PhiMaiIn"));
		reservationRoomRepository.save(reserroom);
	}

	@Test
	public void testPromotionCannotBeNull() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewPromotionEntity(promotionRepository.findByPromotionName(null));

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	@Test
	public void testVadidValueRoomTypeAll() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		reservationRoomRepository.save(reserroom);
	}

	@Test
	public void testRoomtypeCannotBeNull() {
		ReservationRoomEntity reserroom = new ReservationRoomEntity();
		reserroom.setNewRoomTypeEntity(roomTypeRepository.findByName(null));

		try {
			entityManager.persist(reserroom);
			entityManager.flush();

			//fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			// assertEquals(violations.size(), 1);
			System.out.print("23++++++++++++++++");
			System.out.print(e.getConstraintViolations());
			System.out.print("23++++++++++++++++");
		}
	}

	

	
}