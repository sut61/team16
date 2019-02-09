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
public class RoomTest {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomStatusRepository roomStatusRepository;
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private MemberHotelRepository memberHotelRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private FurnitureRepository furnitureRepository;
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Start Test Sprint #1
	// Test field 2 field of RoomEntity Contain annotation @NotNull @Size @Pattern
	@Test
	public void testVadidValueAll() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A407");
		room.setRoomPrice(650L);
		roomRepository.save(room);
	}

	@Test
	public void testRoomNumberCannotBeNull() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber(null);
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberCannotBeNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength4To6LessThan4() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A47");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();
			fail("RoomNumber Should 4-6 character ");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength4To6LessThan4");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberLength4To6MoreThan6() {
		;
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A400007");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();
			fail("RoomNumber Should 4-6 character ");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberLength4To6MoreThan6");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomNumberStartWithAToZ() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("#40007");
		room.setRoomPrice(650L);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("First RoomNumber must A-Z or a-z and follow by digit");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomNumberStartWithAToZ");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomPriceCannotBeNull() {
		RoomEntity room = new RoomEntity();
		room.setRoomNumber("A407");
		room.setRoomPrice(null);

		try {
			entityManager.persist(room);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomPriceCannotBeNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test 4 filed of RoomTypeEntity contain NotNull And and @Column(unique = true)
	@Test
	public void testEnterDataRoomTypeEntity() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");
		roomTypeRepository.save(rt);
	}

	@Test
	public void testRoomTypeNameMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName(null);

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomTypeNameMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomTypeNameMustUnique() {
		RoomTypeEntity rt1 = new RoomTypeEntity();
		rt1.setBedType("Twice");
		rt1.setMaxPeople(3L);
		rt1.setNumberOfBed(1L);
		rt1.setRoomTypeName("VIP");
		entityManager.persist(rt1);
		entityManager.flush();

		RoomTypeEntity rt2 = new RoomTypeEntity();
		rt2.setBedType("Single");
		rt2.setMaxPeople(3L);
		rt2.setNumberOfBed(1L);
		rt2.setRoomTypeName("VIP");

		try {
			entityManager.persist(rt2);
			entityManager.flush();

			fail("RoomTypeName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomTypeNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testRoomTypeNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testBedTypeMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType(null);
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testBedTypeMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMaxPeopleMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(null);
		rt.setNumberOfBed(1L);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMaxPeopleMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testNumberOfBedMustNotNull() {
		RoomTypeEntity rt = new RoomTypeEntity();
		rt.setBedType("Single");
		rt.setMaxPeople(3L);
		rt.setNumberOfBed(null);
		rt.setRoomTypeName("Standard");

		try {
			entityManager.persist(rt);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testNumberOfBedMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test 1 filed of RoomStatusEntity contain NotNull And and @Column(unique =
	// true)

	@Test
	public void testEnterDataRoomStatusEntity() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName("ว่าง");
		roomStatusRepository.save(rs);
	}

	@Test
	public void testRoomStatusNameMusNotNull() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName(null);

		try {
			entityManager.persist(rs);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomStatusNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoomStatusNameMustUnique() {
		RoomStatusEntity rs = new RoomStatusEntity();
		rs.setRoomStatusName("ไม่ว่าง");
		entityManager.persist(rs);
		entityManager.flush();

		RoomStatusEntity rs2 = new RoomStatusEntity();
		rs2.setRoomStatusName("ไม่ว่าง");

		try {
			entityManager.persist(rs2);
			entityManager.flush();

			fail("RoomStatusName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoomStatusNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testRoomStatusNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	// Test 2 filed of MemberHotelEntity contain NotNull And and @Column(unique =
	// true)
	@Test
	public void testEnterDataMemberHotelEntity() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Aphirat");
		mem.setMemberHotelPassword(1234L);
		memberHotelRepository.save(mem);
	}

	@Test
	public void testMemberHotelNameMusNotNull() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName(null);
		mem.setMemberHotelPassword(1234L);

		try {
			entityManager.persist(mem);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMemberHotelNameMustUnique() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Somsak");
		mem.setMemberHotelPassword(1234L);
		entityManager.persist(mem);
		entityManager.flush();

		MemberHotelEntity mem2 = new MemberHotelEntity();
		mem2.setMemberHotelName("Somsak");
		mem2.setMemberHotelPassword(1234L);

		try {
			entityManager.persist(mem2);
			entityManager.flush();

			fail("MemberHotelName must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelNameMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testMemberHotelNameMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testMemberHotelPasswordMustNotNull() {
		MemberHotelEntity mem = new MemberHotelEntity();
		mem.setMemberHotelName("Aphirat");
		mem.setMemberHotelPassword(null);

		try {
			entityManager.persist(mem);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMemberHotelPasswordMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test 1 filed of ProvinceEntity contain NotNull
	@Test
	public void testEnterDataProvinceEntity() {
		ProvinceEntity p = new ProvinceEntity();
		p.setProvinceName("NakhonRatchasima");
		provinceRepository.save(p);
	}

	@Test
	public void testProvinceNameMustNotNull() {
		ProvinceEntity p = new ProvinceEntity();
		p.setProvinceName(null);

		try {
			entityManager.persist(p);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testProvinceNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test 1 filed of FurnitureEntity contain NotNull
	@Test
	public void testEnterDataFurnitureEntity() {
		FurnitureEntity f = new FurnitureEntity();
		f.setFurnitureName("Table");
		furnitureRepository.save(f);
	}

	@Test
	public void testFurnitureNameMustNotNull() {
		FurnitureEntity f = new FurnitureEntity();
		f.setFurnitureName(null);

		try {
			entityManager.persist(f);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testFurnitureNameMusNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// Test 13 filed of HotelEntity contain NotNull @Size @Pattern and @Column (unique = true)
	@Test
	public void testEnterDataHotelEntity() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("Phimai");
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");
		hotelRepository.save(hotels);
	}

	@Test
	public void testHotelNameEngMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng(null);
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("Phimai");
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086250596");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testHotelNameEngMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testotelNameEngMustUnique() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("Suranaree");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("Phimai");
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086255906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");
		entityManager.persist(hotels);
		entityManager.flush();

		HotelEntity hotel2 = new HotelEntity();
		hotel2.setHotelNameEng("Suranaree");
		hotel2.setAlleyLane("-");
		hotel2.setBuilding("-");
		hotel2.setDistrictArea("Phimai");
		hotel2.setFax("044004422");
		hotel2.setHouseNo("403");
		hotel2.setPhone("086255906");
		hotel2.setVillage("เจริญรอด");
		hotel2.setRoad("แจ้งวัฒนะ");
		hotel2.setSubDistrictSubArea("ในเมือง");
		hotel2.setVillageNo(20L);
		hotel2.setPostCode("30110");
		hotel2.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotel2);
			entityManager.flush();

			fail("HotelNameEng must unique");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testotelNameEngMustUnique");
			System.out.print("Error message = ");
			e.printStackTrace();
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println("testotelNameEngMustUnique");
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	@Test
	public void testAlleyLaneMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane(null);
		hotels.setBuilding("-");
		hotels.setDistrictArea("Phimai");
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086255906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testAlleyLaneMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testBuildingMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding(null);
		hotels.setDistrictArea("Phimai");
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086255906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testBuildingMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testDistrictAreaMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea(null);
		hotels.setFax("044004422");
		hotels.setHouseNo("403");
		hotels.setPhone("086205906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testDistrictAreaMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testFaxMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax(null);
		hotels.setHouseNo("403");
		hotels.setPhone("086250506");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testDistrictAreaMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testHouseNoMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo(null);
		hotels.setPhone("086250906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testHouseNoMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPhoneMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone(null);
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPhoneMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testVillageMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage(null);
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testVillageMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testRoadMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad(null);
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testRoadMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testSubDistrictSubAreaMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea(null);
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testSubDistrictSubAreaMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testVillageNoMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("Phimai");
		hotels.setVillageNo(null);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testVillageNoMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPostCodeMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("Phimai");
		hotels.setVillageNo(20L);
		hotels.setPostCode(null);
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testVillageNoMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMobilePhoneMustNotNull() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("022456456");
		hotels.setHouseNo("403");
		hotels.setPhone("022488448");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("Phimai");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone(null);

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMobilePhoneMustNotNull");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testFaxMustStartWith0() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("123456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250506");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testFaxMustNotStartWith0");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testFaxLengthLessThan9() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("01234567");
		hotels.setHouseNo("403");
		hotels.setPhone("086250506");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testFaxLengthLessThan9");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testFaxLengthMoreThan9() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax(null);
		hotels.setHouseNo("403");
		hotels.setPhone("086250506");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");


		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testFaxLengthMoreThan9");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPhoneLengthMoreThan9() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("0862505906");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPhoneLengthMoreThan9");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPhoneLengthLessThan9() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("08625059");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPhoneLengthLessThan9");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPhoneMustStartWith0() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("123456789");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPhoneMustNotStartWith0");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMobliePhoneMusStartWith0() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("023456789");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("1903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMobliePhoneMustNotStartWith0");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMobilePhoneLengthMoreThan10() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("01234567890");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMobilePhoneLengthMoreThan10");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testMobilePhoneLengthLessThan10() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("30110");
		hotels.setMobilePhone("090376890");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testMobilePhoneLengthLessThan10");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPostCodeLengthLessThan5() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("3011");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPostCodeLengthLessThan5");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testPostCodeLengthMoreThan5() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("301100");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPostCodeLengthMoreThan5");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	
	@Test
	public void testPostCodeMustNotStartWith0() {
		HotelEntity hotels = new HotelEntity();
		hotels.setHotelNameEng("PhimaiIn");
		hotels.setAlleyLane("-");
		hotels.setBuilding("-");
		hotels.setDistrictArea("PhimaiIn");
		hotels.setFax("023456789");
		hotels.setHouseNo("403");
		hotels.setPhone("086250590");
		hotels.setVillage("เจริญรอด");
		hotels.setRoad("แจ้งวัฒนะ");
		hotels.setSubDistrictSubArea("ในเมือง");
		hotels.setVillageNo(20L);
		hotels.setPostCode("01234");
		hotels.setMobilePhone("0903768901");

		try {
			entityManager.persist(hotels);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			System.out.println("*********************************************************************");
			System.out.println("testPostCodeMustNotStartWith0");
			System.out.println("Error message = " + e.getMessage());
			System.out.println("*********************************************************************");
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
}
