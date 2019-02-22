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
public class ListTest {
	@Test
	 public void testRoomNumberCannotBeNull(){}

	@Autowired
	private ListRepository listRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test Sprint#1 & Sprint#2 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test ListEntity */

	@Test
	public void testListValueAll() {
		ListEntity list = new ListEntity();
		list.setListName("Coca Cola");
		list.setTotalAmount(10L);
		list.setListPrice(100L);
		listRepository.save(list);
	}

	@Test
	public void testListNameFalsePattern() {
		ListEntity list = new ListEntity();
		list.setListName("55Cola");
		list.setTotalAmount(10L);
		list.setListPrice(100L);
		listRepository.save(list);
		try {
			entityManager.persist(list);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test ListName False Pattern");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testListCannotBeNull() {
		ListEntity list = new ListEntity();
		list.setListName(null);
		list.setTotalAmount(null);
		list.setListPrice(null);
		listRepository.save(list);;

		try {
			entityManager.persist(list);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test ListName is Null");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
		}
	}	

	@Test
	public void testListAmountAndListPriceNegative() {
		ListEntity list = new ListEntity();
		list.setListName("Coca Cola");
		list.setTotalAmount(-10L);
		list.setListPrice(-10L);
		listRepository.save(list);
		try {
			entityManager.persist(list);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test List Amount & List Price Negative");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			 assertEquals(violations.size(), 2);
		}
	}

	@Test
    public void testListNameMustBeUnique() {
		ListEntity list1 = new ListEntity();
		list1.setListName("Coca Cola");
		list1.setTotalAmount(10L);
		list1.setListPrice(10L);
        entityManager.persist(list1);
        entityManager.flush();

		ListEntity list2 = new ListEntity();
		list2.setListName("Coca Cola");
		list2.setTotalAmount(10L);
		list2.setListPrice(10L);
        try {
			entityManager.persist(list2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e){
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test ListName Uniqe");
			System.out.println("Error: "+e.getStackTrace());
			e.printStackTrace();
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
		}
	}
}