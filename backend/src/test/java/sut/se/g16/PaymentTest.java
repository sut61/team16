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
public class PaymentTest {

    @Autowired
    private PaymentAllRepository PaymentAllRepository;

    @Autowired
    private  BankRepository bankRepository;
 

    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;

   

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testVadidValueAll() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("0000000000000000");
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));
        entityManager.persist(p);
        entityManager.flush();

        System.out.println("0========testVadidValueAll============");
        System.out.println("test");
        System.out.println("=====================================");
    }
    @Test
    public void testPaymentCannotBeNull() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId(null);
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));

        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("1========testPaymentCannotBeNull============");
            System.out.println(e.getConstraintViolations());
            System.out.println("=================================================");
        }
    }
    @Test
    public void testPaymentLengmin() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("12345");
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("2=========testPaymentLengmin==========");
            System.out.println(e.getConstraintViolations());
            System.out.println("=======================================");

        }
    }
    @Test
    public void testPaymentLengmax() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("12345123451234512345333");
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));

        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("3==========testPaymentLengmax================");
            System.out.println(e.getConstraintViolations());
            System.out.println("===============================================");

        }
    }
    @Test
    public void testPaymentpatern() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("paymentppppppppppppppppppppppppppppppppppppppp");
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("4=========testPaymentpatern===========");
            System.out.println(e.getConstraintViolations());
            System.out.println("4====================");

        }
    }
     @Test
    public void testBankEntitynull() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName(null));
        p.setCardId("paymentppppppppppppppppppppppppppppppppppppppp");
         p.setName("ping");
         p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("4=========testPaymentpatern===========");
            System.out.println(e.getConstraintViolations());
            System.out.println("4====================");

        }
    }
    @Test
    public void testNamenull() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("0000000000000000");
        p.setName(null);
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("5=========testNamenull==========");
            System.out.println(e.getConstraintViolations());
            System.out.println("=======================================");

        }
    }
    @Test
    public void testCvvnull() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("0000000000000000");
        p.setName("ping");
        p.setCvv(null);
        p.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("6=========testNamenull==========");
            System.out.println(e.getConstraintViolations());
            System.out.println("=======================================");

        }
    }


    @Test
    public void testPaymentUni() {
        PaymentAllEntity p = new PaymentAllEntity();
        p.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        p.setCardId("1234567890123456");
        p.setName("ping");
        p.setCvv("123");
        p.setDate(new Date(2018-1900,2-1,1));
        entityManager.persist(p);
        entityManager.flush();


        PaymentAllEntity pp = new PaymentAllEntity();
        pp.setBankEntity(bankRepository.findByBankName("กรุงไทย"));
        pp.setCardId("1234567890123456");
        pp.setName("ping");
        pp.setCvv("123");
        pp.setDate(new Date(2018-1900,2-1,1));


        try {
            entityManager.persist(pp);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("7=========testPaymentuni===========");
            e.printStackTrace();
            System.out.println("====================");

        }
        catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
            System.out.println("7====================");
            System.out.println(e);
            e.printStackTrace();
            System.out.println("====================");

        }
    }

}