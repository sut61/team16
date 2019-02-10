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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PromotionTest {

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private TestEntityManager entityManager;

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	@Test
	public void testVadidValueAll() {
		PromotionEntity p = new PromotionEntity();
		p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("A4899");
		p.setDetail("gropkp");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));
		entityManager.persist(p);
		entityManager.flush();

		System.out.println("0========testVadidValueAll============");
		System.out.println("test");
		System.out.println("=====================================");
	}
	@Test
	public void testPromotionnameCannotBeNull() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName(null);
		p.setDetail("gropkp");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("1========testPromotionnameCannotBeNull============");
			System.out.println(e.getConstraintViolations());
			System.out.println("=================================================");
		}
	}
	@Test
	public void testPromotionLengmin() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p1");
		p.setDetail("gropkp");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("2=========testPromotionLengmin==========");
			System.out.println(e.getConstraintViolations());
			System.out.println("=======================================");

		}
	}
	@Test
	public void testPromotionLengmax() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p11111111111111111111111111111111111111111111");
		p.setDetail("lpkko");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("3==========testPromotionLengmax================");
			System.out.println(e.getConstraintViolations());
			System.out.println("===============================================");

		}
	}
	@Test
	public void testPromotonpatern() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("3SSsssss");
		p.setDetail("lpkko");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("4=========testPromotonpatern===========");
			System.out.println(e.getConstraintViolations());
			System.out.println("4====================");

		}
	}
	@Test
	public void testdetailnull() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p12345");
		p.setDetail(null);
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}
	@Test
	public void testDateStartninull() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p12345");
		p.setDetail("oiugj");
		p.setDateStart(null);
		p.setDateEnd(new Date(2018-1900,2-1,1));

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}
	@Test
	public void testDateEndnull() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p12345");
		p.setDetail("oiugj");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(null);

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}
	@Test
	public void testNewPromotionTypeEntitynull() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName(null));
		p.setPromotionName("p12345");
		p.setDetail("oiugj");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(null);

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}
	@Test
	public void testPromotionnameuni() {
		PromotionEntity p = new PromotionEntity();
        p.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		p.setPromotionName("p11111");
		p.setDetail("gropkp");
		p.setDateStart(new Date(2018-1900,2-1,1));
		p.setDateEnd(new Date(2018-1900,2-1,1));
		entityManager.persist(p);
		entityManager.flush();


		PromotionEntity pp = new PromotionEntity();
        pp.setNewPromotionTypeEntity(promotionTypeRepository.findByPromotionTypeName("1 day 2000"));
		pp.setPromotionName("p11111");
		pp.setDetail("gropkp");
		pp.setDateStart(new Date(2018-1900,2-1,1));
		pp.setDateEnd(new Date(2018-1900,2-1,1));


		try {
				entityManager.persist(pp);
				entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("5=========testPromotionnameuni===========");
			e.printStackTrace();
			System.out.println("====================");

		}
	 catch (javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		 System.out.println("5====================");
		 System.out.println(e);
		 e.printStackTrace();
		 System.out.println("====================");

	}
	}

}