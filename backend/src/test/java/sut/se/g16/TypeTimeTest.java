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
public class TypeTimeTest {

    @Autowired
    private TypeTimeRepository typeTimeRepository;
    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    // Test field of TypeTimeEntity 1 field(TypeTimeName)
    // contain @Notnull
    // TestInsertValidAllData
    @Test
    public void testEnterTypeTimeValidData() {
        TypeTimeEntity tt = new TypeTimeEntity();
        tt.setTypeTimeName("Meeting");
        typeTimeRepository.save(tt);
    }

    @Test
    public void testTypeTimeNameMustNotNull() {
        TypeTimeEntity tt = new TypeTimeEntity();
        tt.setTypeTimeName(null);
        try {
            entityManager.persist(tt);
            entityManager.flush();

            fail("TypeTimeName must not null");
        } catch (javax.validation.ConstraintViolationException e) {
            System.out.println("*********************************************************************");
            System.out.println("testTypeTimeNameMustNotNull");
            System.out.println("Error message = " + e.getMessage());
            System.out.println("*********************************************************************");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
}