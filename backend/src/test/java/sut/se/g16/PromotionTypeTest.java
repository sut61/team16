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
public class PromotionTypeTest {

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    @Autowired
    private TestEntityManager entityManager;


    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testPromotionTypeEntityValueAll() {
        PromotionTypeEntity p = new PromotionTypeEntity();
        p.setPromotionTypeName("discount");
        entityManager.persist(p);
        entityManager.flush();

        System.out.println("0========testPromotionTypeEntityValueAll============");
        System.out.println("test");
        System.out.println("=====================================");
    }
    @Test
    public void testPromotionTypeEntityCannotBeNull() {
        PromotionTypeEntity p = new PromotionTypeEntity();
        p.setPromotionTypeName(null);

        try {
            entityManager.persist(p);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("1========testPromotionTypeEntityCannotBeNull============");
            System.out.println(e.getConstraintViolations());
            System.out.println("=================================================");
        }
    }
}