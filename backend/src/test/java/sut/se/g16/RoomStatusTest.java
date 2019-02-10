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
public class RoomStatusTest {

	@Autowired
	private RoomStatusRepository roomStatusRepository;
	@Autowired
	private MemberHotelRepository memberHotelRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private FurnitureRepository furnitureRepository;
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Test 1 filed of RoomStatusEntity contain NotNull And and @Column(unique =
	// true)

	@Test
	public void testEnterDataRoomStatusEntity() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName("ว่าง");
		roomStatusRepository.save(rs);
	}

	@Test
	public void testRoomStatusNameMusNotNull() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName(null);

		try {
			entityManager.persist(rs);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomStatusNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomStatusNameMustUnique() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName("ไม่ว่าง");
		entityManager.persist(rs);
		entityManager.flush();

		RoomStatusEntity rs2 = new RoomStatusEntity();
		rs2.setRoomStatusName("ไม่ว่าง");

		try {
			entityManager.persist(rs2);
			entityManager.flush();

			fail("RoomStatusName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomStatusNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testRoomStatusNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

}