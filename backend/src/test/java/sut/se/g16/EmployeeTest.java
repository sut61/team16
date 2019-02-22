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
public class EmployeeTest {
	@Test
	 public void testRoomNumberCannotBeNull(){}

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test Sprint#2 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* Test EmployeeEntity */
	@Test
	public void testEmployeeValueAll() {
		EmployeeEntity em = new EmployeeEntity();
		em.setEmployeeUserName("A1234");
		em.setEmployeePassword("asdf1234");
		employeeRepository.save(em);
	}

	@Test
	public void testEmployeeFalsePattern() {
		EmployeeEntity em = new EmployeeEntity();
		em.setEmployeeUserName("AB55555");
		em.setEmployeePassword("asdf1234");
		employeeRepository.save(em);
		try {
			entityManager.persist(em);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test Employee False Pattern");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testEmployeeNull() {
		EmployeeEntity em = new EmployeeEntity();
		em.setEmployeeUserName(null);
		em.setEmployeePassword(null);
		employeeRepository.save(em);
		try {
			entityManager.persist(em);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test EmployeeUserName is Null & EmployeePassword is Null");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	@Test
	public void testEmployeePasswordMin() {
		EmployeeEntity em = new EmployeeEntity();
		em.setEmployeeUserName("A1234");
		em.setEmployeePassword("a1S");
		employeeRepository.save(em);
		try {
			entityManager.persist(em);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test EmployeePassword Min");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testEmployeePasswordMax() {
		EmployeeEntity em = new EmployeeEntity();
		em.setEmployeeUserName("A1234");
		em.setEmployeePassword("0123456789ABCdefghijk");
		employeeRepository.save(em);
		try {
			entityManager.persist(em);
			entityManager.flush();
			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			System.out.println("Test EmployeePassword Max");
			System.out.println("Error: " + e.getMessage());
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
	
}
