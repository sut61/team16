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
// public class FoodPaymentTest {
// 	@Test
// 	 public void testRoomNumberCannotBeNull(){}
// 	@Autowired
// 	private FoodPaymentRepository foodPaymentRepository;
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
// 	/* Test FoodPaymentEntity */
// 	@Test
// 	public void testFoodPaymentValueAll() {
// 		FoodPaymentEntity fp = new FoodPaymentEntity();
// 		fp.setFoodPaymentStatus("จ่ายแล้ว");
// 		foodPaymentRepository.save(fp);
// 	}

// 	@Test
// 	public void testFoodPamentCannotBeNull() {
// 		FoodPaymentEntity fp = new FoodPaymentEntity();
// 		fp.setFoodPaymentStatus(null);
// 		foodPaymentRepository.save(fp);
// 		try {
// 			entityManager.persist(fp);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodPayment is Null");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}	


// 	@Test
// 	public void testFoodPaymentFalsePattern() {
// 		FoodPaymentEntity fp = new FoodPaymentEntity();
// 		fp.setFoodPaymentStatus("asdf");
// 		foodPaymentRepository.save(fp);
// 		try {
// 			entityManager.persist(fp);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodPayment False Pattern");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testFoodPaymentMin() {
// 		FoodPaymentEntity fp = new FoodPaymentEntity();
// 		fp.setFoodPaymentStatus("ก");
// 		foodPaymentRepository.save(fp);
// 		try {
// 			entityManager.persist(fp);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodPayment Min");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testFoodPaymentMax() {
// 		FoodPaymentEntity fp = new FoodPaymentEntity();
// 		fp.setFoodPaymentStatus("ขนาดยาวเกินกว่าที่กำหนด");
// 		foodPaymentRepository.save(fp);
// 		try {
// 			entityManager.persist(fp);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodPayment Max");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
//     public void testFoodPaymentMustBeUnique() {
// 		FoodPaymentEntity fp1 = new FoodPaymentEntity();
// 		fp1.setFoodPaymentStatus("ไม่จ่าย");
//         entityManager.persist(fp1);
//         entityManager.flush();

// 		FoodPaymentEntity fp2 = new FoodPaymentEntity();
// 		fp2.setFoodPaymentStatus("ไม่จ่าย");
//         try {
// 			entityManager.persist(fp2);
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
// 			System.out.println("Test FoodPayment Uniqe");
// 			System.out.println("Error: "+e.getStackTrace());
// 			e.printStackTrace();
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 		}
// 	}

// }