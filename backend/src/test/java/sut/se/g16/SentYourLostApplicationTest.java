package sut.se.g16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sun.net.www.content.text.plain;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SentYourLostApplicationTest {
	@Autowired
	private SentYourLostRepository sentYourLostRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private CustomerRepository customerRepository;
	// @Autowired
	// private RoomRepository roomRepository;
	// @Autowired
	// private MeetingEventRoomRepository MeetingEventRoomRepository;

	@Autowired
	private TestEntityManager entityManager;
	SentYourLostEntity sent = new SentYourLostEntity();
	ItemEntity item = new ItemEntity();
	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		try {
			entityManager.persistAndFlush(sent);

		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 5);
		}
	}

	@Test
	public void enterAlldata() {
		sent.setAddress("afdasdfasdfasdADSFASDFA");
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
	}

	@Test
	public void checkCustomerNull() {
		sent.setNewCustomerEntity(null);
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress("nakhornnayok");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			System.out.println("check customer null sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checkHotelNull() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(null);
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress("nakhornnayok");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
			System.out.println("check hotel null sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checkRoomNumberNull() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(null);
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress("nakhornnayok");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			System.out.println("check roomnumber null sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checkRoomTypeNull() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(null);
		sent.setAddress("nakhornnayok");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
			System.out.println("check roomtype null sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checkAddressNull() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress(null);

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
			System.out.println("check address null sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checkpatternAddress() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress("......................................................................");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(),4);
			System.out.println("check pattern address sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checksizeAddressTest() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
			System.out.println("check size address sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void checksizeUnderCAddressTest() {
		sent.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
		sent.setNewRoomEntity(roomRepository.findRoomEntityByRoomNumber("A100"));
		sent.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
		sent.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
		sent.setAddress("aa");

		try {
			entityManager.persistAndFlush(sent);

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
			System.out.println("check size under address sent");
			System.out.println("===========================================");
			System.out.println("=============" + e + "=========================");
			System.out.println("===========================================\n\n\n\n\n\n\n");
		}
	}

	
}
