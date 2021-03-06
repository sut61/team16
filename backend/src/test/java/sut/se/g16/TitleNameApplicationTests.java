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
public class TitleNameApplicationTests {

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
        TitleNameEntity title = new TitleNameEntity();
		title.setTitlenameType("Mr.");
		try {
			entityManager.persist(title);
			entityManager.flush();

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    }

    @Test
	public void TestTitlenameNotNull() {
        TitleNameEntity title = new TitleNameEntity();
		title.setTitlenameType(null);
		try {
			entityManager.persist(title);
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
			System.out.println("TestTitlenameNotNull" + e.getMessage());
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

