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

import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
public class ReviewTests {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private ReservationRoomRepository reservationRepository;
    // @Autowired
    // private RoomRepository roomRepository;
    // @Autowired
    // private MeetingEventRoomRepository MeetingEventRoomRepository;

    @Autowired
    private TestEntityManager entityManager;
    ReviewEntity review = new ReviewEntity();
    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setScore(5);
        try {
            entityManager.persistAndFlush(review);
            fail("Should not pass to this line");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }

    @Test
    public void checkCustomerNull() {
        review.setNewCustomerEntity(null);
        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkHotelNull() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(null);
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkReservationIdNull() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(null);
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkRoomTypeNull() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(null);
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkScoreNull() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("comment Tester");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(0);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkpatternComment() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment(";;;;;;;;;");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkPatternTest() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("Comment Tester");
        review.setProblem(";;;;;;;;;;;");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checksizeCommentTest() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checkSizeProblemTest() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("Comment Tester");
        review.setProblem(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

    @Test
    public void checksizeUnderCommentTest() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("aa");
        review.setProblem("Problem Tester");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }
    @Test
    public void checksizeUpderProblemTest() {
        review.setNewCustomerEntity(customerRepository.findBycustomerUsername("film"));
        review.setComment("Comment Tester");
        review.setProblem("aa");
        review.setNewHotelEntity(hotelRepository.findByhotelNameEng("PhimaiIn"));
        review.setNewReservationRoomEntity(reservationRepository.findRservationById(1L));
        review.setNewRoomTypeEntity(roomTypeRepository.findByName("Standard"));
        review.setScore(5);

        try {
            entityManager.persistAndFlush(review);

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("===========================================");
            System.out.println("=============" + e + "=========================");
            System.out.println("===========================================");
        }
    }

}
