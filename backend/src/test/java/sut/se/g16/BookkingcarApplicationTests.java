package sut.se.g16;

import sut.se.g16.Entity.BookingCarEntity;
import sut.se.g16.Entity.CustomerEntity;
import sut.se.g16.Repository.CustomerRepository;
import sut.se.g16.Repository.HotelRepository;
import sut.se.g16.Repository.TitleNameRepository;
import sut.se.g16.Repository.SexRepository;
import sut.se.g16.Repository.NationalityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Set;
import java.util.Date;
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
public class BookkingcarApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
    private HotelRepository hotelRepository;
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("12345678");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
			entityManager.flush();

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
    }
    
    @Test
	public void TestHotelCannotBeNull() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(null);
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("12345678");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestHotelCannotBeNull" + e.getMessage());
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
	public void TestDateStartCannotBeNull() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(null);
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("12345678");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDateStartCannotBeNull" + e.getMessage());
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
	public void TestDateEndCannotBeNull() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(null);
		bookingcar.setDriverId("12345678");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDateEndCannotBeNull" + e.getMessage());
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
	public void TestDriverIdCannotBeNull() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId(null);
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDriverIdCannotBeNull" + e.getMessage());
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
	public void TestDriverIdCannotPattern() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("ASD1150S");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDriverIdCannotPattern" + e.getMessage());
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
	public void TestDriverIdCannotPatternShortAlphabet() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("123456");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDriverIdCannotPatternLongAlphabet" + e.getMessage());
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
	public void TestDriverIdCannotPatternLongAlphabet() {
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
        customerRepository.save(customer);
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("1234567890");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		try {
			entityManager.persist(bookingcar);
            entityManager.flush();
            
            fail("Should not pass to this line");

		} catch(javax.validation.ConstraintViolationException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("TestDriverIdCannotPatternLongAlphabet" + e.getMessage());
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
    public void testDriverIdMustBeUnique() {
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
		customerRepository.save(customer);
		
		BookingCarEntity bookingcar = new BookingCarEntity();
		bookingcar.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar.setDriverId("12345678");
		bookingcar.setCustomer(customerRepository.findBycustomerUsername("WhanWhan55"));
		entityManager.persist(bookingcar);
        entityManager.flush();

        CustomerEntity customer2 = new CustomerEntity();
		customer2.setTitleName(titleNameRepository.findBytitlenameType("Mr."));
		customer2.setCustomerName("LOB RUCI");
		customer2.setSex(sexRepository.findBysexType("Male"));
		customer2.setCustomerIdcrad(1234567890L);
		customer2.setCustomerAddress("Tourism Authority of Thailand 11600  New Petchaburi Rd. Makkasan Subarea Ratchathewi Area Krung Thep Maha Nakhon 10400 Thailand");
		customer2.setNationality(nationalityRepository.findBynationalityName("Thai"));
		customer2.setCustomerEmail("cp9@gmail.com");
		customer2.setCustomerPhone("0904862321");
		customer2.setCustomerUsername("WhanWhan44");
        customer2.setCustomerPassword("xp123456");
		customerRepository.save(customer2);

		BookingCarEntity bookingcar2 = new BookingCarEntity();
		bookingcar2.setHotel(hotelRepository.findByhotelNameEng("PhimaiIn"));
		bookingcar2.setDateStart(new Date(2018-1900,2-1,1));
		bookingcar2.setDateEnd(new Date(2018-1900,2-1,1));
		bookingcar2.setDriverId("12345678");
		bookingcar2.setCustomer(customerRepository.findBycustomerUsername("WhanWhan44"));
		
		try{
			entityManager.persist(bookingcar2);
			entityManager.flush();
			fail("Should not pass to this line");
		}  catch (javax.persistence.PersistenceException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println("testDriverIdMustBeUnique" + e.getStackTrace());
			e.printStackTrace();
			System.out.println("[SHOW] ------------------------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		} 
    }
}

