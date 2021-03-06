package sut.se.g16;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void TestDataAll() {
        CarEntity car = new CarEntity();
        car.setCarName("Aston martin Vanquish 1972");
        car.setCarPrice(200000);
		try {
			entityManager.persist(car);
			entityManager.flush();

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    }

    @Test
	public void TestCarNameNotNull() {
        CarEntity car = new CarEntity();
        car.setCarName(null);
        car.setCarPrice(200000);
		try {
			entityManager.persist(car);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCarNameNotNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    } 
    @Test
	public void TestCarPriceNotNull() {
        CarEntity car = new CarEntity();
        car.setCarName("Aston martin Vanquish 1972");
        car.setCarPrice(null);
		try {
			entityManager.persist(car);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCarPriceNotNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    } 
}
