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
public class FoodOrderTest {
	@Test
	 public void testRoomNumberCannotBeNull(){}

	@Autowired
	private FoodOrderRepository foodOrderRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test Sprint#1 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test FoodOrderEntity */
	@Test
	public void testFoodOrderValueAll() {
		FoodOrderEntity fo = new FoodOrderEntity();
		fo.setTotalProiceOrder(1000L);
		foodOrderRepository.save(fo);
	}

	@Test
	public void testFoodOrderNegative() {
		FoodOrderEntity fo = new FoodOrderEntity();
		fo.setTotalProiceOrder(-100L);
		foodOrderRepository.save(fo);
		try {
			entityManager.persist(fo);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test FoodOrder Total Price Negative");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
	@Test
	public void testFoodOrderCannotBeNull() {
		FoodOrderEntity fo = new FoodOrderEntity();
		fo.setTotalProiceOrder(null);
		foodOrderRepository.save(fo);
		try {
			entityManager.persist(fo);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test FoodOrder is Null");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    }	
}
