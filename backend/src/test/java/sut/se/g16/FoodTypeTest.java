// package sut.se.g16;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import sut.se.g16.Entity.*;
// import sut.se.g16.Repository.*;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.fail;

// import java.util.Collections;
// import java.util.OptionalInt;
// import java.util.Set;

// import javax.persistence.PersistenceException;
// import javax.validation.ConstraintViolation;
// import javax.validation.Validation;
// import javax.validation.Validator;
// import javax.validation.ValidatorFactory;

// import org.junit.Before;
// import org.junit.Test;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// @RunWith(SpringRunner.class)
// @DataJpaTest
// public class FoodTypeTest {
// 	@Test
// 	 public void testRoomNumberCannotBeNull(){}

// 	@Autowired
// 	private FoodTypeRepository foodTypeRepository;
// 	@Autowired
// 	private TestEntityManager entityManager;

// 	private Validator validator;

// 	@Before
// 	public void setup() {
// 		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
// 		validator = factory.getValidator();
// 	}

// 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 	/* Test Sprint#1 */
// 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 	/* Test FoodTypeEntity */
// 	@Test
// 	public void testFoodTypeValueAll() {
// 		FoodTypeEntity ft = new FoodTypeEntity();
// 		ft.setFoodTypeName("Snack");
// 		foodTypeRepository.save(ft);
// 	}

// 	@Test
// 	public void testFoodTypeCannotBeNull() {
// 		FoodTypeEntity ft = new FoodTypeEntity();
// 		ft.setFoodTypeName(null);
// 		foodTypeRepository.save(ft);
// 		try {
// 			entityManager.persist(ft);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodType is Null");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}	

// 	@Test
// 	public void testFoodTypeFalsePattern() {
// 		FoodTypeEntity ft = new FoodTypeEntity();
// 		ft.setFoodTypeName("Snack123");
// 		foodTypeRepository.save(ft);
// 		try {
// 			entityManager.persist(ft);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodType False Pattern");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testFoodTypeMin() {
// 		FoodTypeEntity ft = new FoodTypeEntity();
// 		ft.setFoodTypeName("Aa");
// 		foodTypeRepository.save(ft);
// 		try {
// 			entityManager.persist(ft);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodType Min");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testFoodTypeMax() {
// 		FoodTypeEntity ft = new FoodTypeEntity();
// 		ft.setFoodTypeName("Abcdefghijklmnopqrstuvwxyz");
// 		foodTypeRepository.save(ft);
// 		try {
// 			entityManager.persist(ft);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodType Max");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
//     public void testFoodTypeMustBeUnique() {
// 		FoodTypeEntity ft1 = new FoodTypeEntity();
// 		ft1.setFoodTypeName("Snack");
//         entityManager.persist(ft1);
//         entityManager.flush();

// 		FoodTypeEntity ft2 = new FoodTypeEntity();
// 		ft2.setFoodTypeName("Snack");
//         try {
// 			entityManager.persist(ft2);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		} catch (javax.persistence.PersistenceException e){
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodType Uniqe");
// 			System.out.println("Error: "+e.getStackTrace());
// 			e.printStackTrace();
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 		}
//     }
// }
