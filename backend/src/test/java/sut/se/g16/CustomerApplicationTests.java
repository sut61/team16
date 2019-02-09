package sut.se.g16;

import sut.se.g16.Entity.CustomerEntity;
import sut.se.g16.Repository.TitleNameRepository;
import sut.se.g16.Repository.SexRepository;
import sut.se.g16.Repository.NationalityRepository;
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
public class CustomerApplicationTests {

	@Autowired
	private TitleNameRepository titleNameRepository;
	@Autowired
	private SexRepository sexRepository;
	@Autowired
	private NationalityRepository nationalityRepository;
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
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestTitleNameCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(null);
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904823210");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestTitleNameCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
	
	@Test
	public void TestCustomerNameCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName(null);
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerNameCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestSexCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(null);
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestSexCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerIdcradCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(null);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerIdcradCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerAddressCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress(null);
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerAddressCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestNationalityCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(null);
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestNationalityCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerEmailCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail(null);
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerEmailCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerEmailCannotPatternEmail() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("emailnotpattern");
		customer.setCustomerPhone("0904823210");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerEmailCannotPatternEmail" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerPhoneCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone(null);
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPhoneCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerUsernameCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername(null);
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerUsernameCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerPasswordCannotBeNull() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword(null);
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPasswordCannotBeNull" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerPhoneCannotPattern() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0001234567");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPhoneCannotPattern" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
	
	@Test
	public void TestCustomerPhoneCannotPatternLongAlphabet() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("09048623212121");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPhoneCannotPatternLongAlphabet" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerUsernameCannotPattern() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0601234567");
		customer.setCustomerUsername("เวรี่กู๊ด");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerUsernameCannotPattern" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerUsernameCannotPatternLongAlphabet() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("W");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerUsernameCannotPatternLongAlphabet" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerPasswordCannotPattern() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0601234567");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("พาสเวิร์ด");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPasswordCannotPattern" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerPasswordCannotPatternLongAlphabet() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(1234567890L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp1234");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPasswordCannotPatternLongAlphabet" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void TestCustomerIdCardCannotPositive() {
		CustomerEntity customer = new CustomerEntity();
		customer.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer.setCustomerName("LOB RUCI");
		customer.setSex(sexRepository.findBysexType("Male"));
		customer.setCustomerIdcrad(00000000L);
		customer.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer.setCustomerEmail("cp9@gmail.com");
		customer.setCustomerPhone("0904862321");
		customer.setCustomerUsername("WhanWhan55");
		customer.setCustomerPassword("xp123456");
		try {
			entityManager.persist(customer);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestCustomerPasswordCannotPatternLongAlphabet" + e.getMessage());
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

}

