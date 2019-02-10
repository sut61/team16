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
// public class FoodStockTest {
// 	@Test
// 	 public void testRoomNumberCannotBeNull(){}

// 	@Autowired
// 	private FoodStockRepository foodStockRepository;
// 	@Autowired
// 	private TestEntityManager entityManager;

// 	private Validator validator;

// 	@Before
// 	public void setup() {
// 		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
// 		validator = factory.getValidator();
// 	}

// 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 	/* Test Sprint#2 */
// 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 	/* Test FoodStockEntity */

// 	@Test
// 	public void testStockValueAll() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("Coca Cola");
// 		stock.setPrice(20L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);
// 	}

// 	@Test
// 	public void testStockNameFalsePattern() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("aSSS55");
// 		stock.setPrice(20L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);
// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();
// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test StockName False Pattern");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testFoodStockCannotBeNull() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName(null);
// 		stock.setPrice(null);
// 		stock.setAmount(null);
// 		foodStockRepository.save(stock);

// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test FoodStock is Null");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 3);
// 		}
// 	}
	

// 	@Test
// 	public void testPriceOutOfLength() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("Coca Cola");
// 		stock.setPrice(10000L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);

// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test Price Out of Length");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testStockNameSizeMin() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("Ga");
// 		stock.setPrice(100L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);

// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test StockName Min");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testStockNameSizeMax() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("Gaxxxxxxxxxxxx sssaaaa");
// 		stock.setPrice(100L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);

// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test StockName Max");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			assertEquals(violations.size(), 1);
// 		}
// 	}

// 	@Test
// 	public void testPriceNegative() {
// 		FoodStockEntity stock = new FoodStockEntity();
// 		stock.setStockName("Coca Cola");
// 		stock.setPrice(-1L);
// 		stock.setAmount(10L);
// 		foodStockRepository.save(stock);

// 		try {
// 			entityManager.persist(stock);
// 			entityManager.flush();

// 			fail("Should not pass to this line");
// 		} catch (javax.validation.ConstraintViolationException e) {
// 			System.out.println("\n\n\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
// 			System.out.println("Test Price Negative");
// 			System.out.println("Error: " + e.getMessage());
// 			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n\n\n");
// 			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
// 			assertEquals(violations.isEmpty(), false);
// 			 assertEquals(violations.size(), 1);
// 		}
//     }
// }