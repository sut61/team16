package sut.se.g16;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(MemberHotelRepository memberHotelRepository, RoomStatusRepository roomStatusRepository,
            HotelRepository hotelRepository, PromotionRepository promotionRepository,
            StatusPaymentRepository statusPaymentRepository, FurnitureRepository furnitureRepository,
            RoomTypeRepository roomTypeRepository, RoomRepository roomRepository,FunctionRepository functionRepository,
            PromotionTypeRepository promotionTypeRepository, TypeTimeRepository typeTimeRepository,
            MeetingEventRoomTypeRepository meetingEventRoomTypeRepository,BankRepository bankRepository,
            RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository,
            FoodOrderRepository foodOrderRepository, ListRepository listRepository,ItemRepository itemRepository,
            FoodTypeRepository foodTypeRepository, TotalPriceFoodRepository totalPriceFoodRepository,
            ProvinceRepository provinceRepository, FoodPaymentRepository foodPaymentRepository,
            HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository,HotelMeetingEventRoomTypeManyToManyRepository hotelMeetingEventRoomTypeManyToManyRepository,
            FoodOrderTotalPriceFoodManyToManyRepository foodOrderTotalPriceFoodManyToManyRepository,
            CustomerRepository customerRepository, NationalityRepository nationalityRepository,
            SexRepository sexRepository, TitleNameRepository titleNameRepository, CarBrandRepository carBrandRepository,
            CarTypeRepository carTypeRepository, CarRepository carRepository, EmployeeRepository employeeRepository) {

        return args -> {

            // Create RoomStatusEntity
            Stream.of("ว่าง", "จอง", "พัก", "ใช้สถานที่").forEach(roomstatus -> {
                RoomStatusEntity rst = new RoomStatusEntity();
                rst.setRoomStatusName(roomstatus);
                roomStatusRepository.save(rst);
            });

            // Create Provinces
            Stream.of("กรุงเทพมหานคร", "กระบี่", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี",
                    "ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด",
                    "ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี",
                    "นราธิวาส", "น่าน", "บึงกาฬ", "บุรีรัมย์", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
                    "พระนครศรีอยุธยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่",
                    "พะเยา", "ภูเก็ต", "มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน", "ยะลา", "ยโสธร", "ร้อยเอ็ด", "ระนอง",
                    "ระยอง", "ราชบุรี", "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล",
                    "สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย",
                    "สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย", "หนองบัวลำภู", "อ่างทอง", "อุดรธานี",
                    "อุทัยธานี", "อุตรดิตถ์", "อุบลราชธานี", "อำนาจเจริญ").forEach(province -> {
                        ProvinceEntity provinces = new ProvinceEntity();
                        provinces.setProvinceName(province);
                        provinceRepository.save(provinces);
                    });

            // Create Furniture
            Stream.of("Table", "OfficeTable", "Microwave", "Sofa").forEach(furniture -> {
                FurnitureEntity fr = new FurnitureEntity();
                fr.setFurnitureName(furniture);
                furnitureRepository.save(fr);
            });

            // Create RoomType
            Stream.of("Standard", "Suite", "Deluxe", "Superior").forEach(roomType -> {
                if (roomType == "Standard") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Single");
                    rt.setNumberOfBed(1L);
                    rt.setMaxPeople(2L);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Suite") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(1L);
                    rt.setMaxPeople(3L);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Deluxe") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(1L);
                    rt.setMaxPeople(3L);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Superior") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(2L);
                    rt.setMaxPeople(6L);
                    roomTypeRepository.save(rt);
                }
            });

            // Create RoomTypeNameManyToMany
            Stream.of("Table", "OfficeTable", "Microwave", "Sofa").forEach(furniture -> {
                Stream.of("Standard", "Suite", "Deluxe", "Superior").forEach(roomType -> {
                    FurnitureEntity fr = furnitureRepository.findByName(furniture);
                    RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
                    RoomTypeFurnitureManyToManyEntity rtf = new RoomTypeFurnitureManyToManyEntity();
                    rtf.setNewFurnitureRoomTypeManyEntity(fr);
                    rtf.setNewRoomTypeFurnitureManyEntity(rt);
                    roomTypeFurnitureManyToManyRepository.save(rtf);
                });
            });

            // Create Member
            MemberHotelEntity mem = new MemberHotelEntity();
            mem.setMemberHotelName("Aphirat");
            mem.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem);

            MemberHotelEntity mem2 = new MemberHotelEntity();
            mem2.setMemberHotelName("Pitchayut");
            mem2.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem2);

            // Create Hotel
            ProvinceEntity p = provinceRepository.findByName("นครราชสีมา");
            MemberHotelEntity m = memberHotelRepository.findByName("Aphirat");
            MemberHotelEntity m2 = memberHotelRepository.findByName("Pitchayut");
            HotelEntity hotels = new HotelEntity();
            hotels.setHotelNameEng("PhimaiIn");
            hotels.setAlleyLane("-");
            hotels.setBuilding("-");
            hotels.setDistrictArea("Phimai");
            hotels.setFax("044004422");
            hotels.setNewProvinceEntity(p);
            hotels.setHouseNo("403");
            hotels.setNewMemberHotelEntity(m);
            hotels.setPhone("086250506");
            hotels.setVillage("เจริญรอด");
            hotels.setRoad("แจ้งวัฒนะ");
            hotels.setSubDistrictSubArea("ในเมือง");
            hotels.setVillageNo(20L);
            hotels.setPostCode("30110");
            hotels.setMobilePhone("0903768901");
            hotelRepository.save(hotels);

            HotelEntity hotel = new HotelEntity();
            hotel.setHotelNameEng("Amathara");
            hotel.setAlleyLane("-");
            hotel.setBuilding("-");
            hotel.setDistrictArea("Phimai");
            hotel.setFax("044004422");
            hotel.setNewProvinceEntity(p);
            hotel.setHouseNo("403");
            hotel.setNewMemberHotelEntity(m2);
            hotel.setPhone("086255906");
            hotel.setVillage("เจริญรอด");
            hotel.setRoad("แจ้งวัฒนะ");
            hotel.setSubDistrictSubArea("ในเมือง");
            hotel.setVillageNo(20L);
            hotel.setPostCode("30110");
            hotel.setMobilePhone("0903768901");
            hotelRepository.save(hotel);

            // Create Status
            Stream.of("จ่ายแล้ว", "ยังไม่จ่าย").forEach(status -> {
                FoodPaymentEntity f = new FoodPaymentEntity();
                f.setFoodPaymentStatus(status);
                foodPaymentRepository.save(f);
            });

            // Create Food Type
            Stream.of("Drink", "Food", "Dessert").forEach(foodtype -> {
                FoodTypeEntity food = new FoodTypeEntity();
                food.setFoodTypeName(foodtype);
                foodTypeRepository.save(food);
            });

            Stream.of("Male", "Female").forEach(sex -> {
                if (sex.equals("Male")) {
                    SexEntity sexEntity = new SexEntity();
                    sexEntity.setSexType("Male");
                    sexRepository.save(sexEntity);
                }
                if (sex.equals("Female")) {
                    SexEntity sexEntity = new SexEntity();
                    sexEntity.setSexType("Female");
                    sexRepository.save(sexEntity);
                }
            });

            Stream.of("Miss", "Mrs.", "Mr.").forEach(title -> {
                if (title.equals("Miss")) {
                    TitleNameEntity titleNameEntity = new TitleNameEntity();
                    titleNameEntity.setTitlenameType("Miss");
                    titleNameRepository.save(titleNameEntity);
                }
                if (title.equals("Mrs.")) {
                    TitleNameEntity titleNameEntity = new TitleNameEntity();
                    titleNameEntity.setTitlenameType("Mrs.");
                    titleNameRepository.save(titleNameEntity);
                }
                if (title.equals("Mr.")) {
                    TitleNameEntity titleNameEntity = new TitleNameEntity();
                    titleNameEntity.setTitlenameType("Mr.");
                    titleNameRepository.save(titleNameEntity);
                }
            });

            Stream.of("Australian", "Belgian", "Brazilian", "Cambodian", "Canadian", "Chinese", "Danish", "Egyptian",
                    "English", "Finnish", "French", "German", "Greek", "Indonesian", "Iranian", "Irish", "Israeli",
                    "Italian", "Laotian", "Malaysian", "Mexican", "Dutch", "New Zealander", "Norwegian", "Filipino",
                    "Portuguese", "Romanian", "Russian", "Korean", "Spanish", "Norwegian", "Swedish", "Swiss", "Thai",
                    "British", "American", "Vietnamese").forEach(nationality -> {
                        if (nationality.equals("Australian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Australian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Belgian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Belgian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Brazilian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Brazilian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Cambodian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Cambodian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Canadian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Canadian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Chinese")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Chinese");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Danish")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Danish");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Egyptian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Egyptian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("English")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("English");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Finnish")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Finnish");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("French")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("French");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("German")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("German");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Greek")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Greek");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Indonesian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Indonesian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Irish")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Irish");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Israeli")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Israeli");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Italian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Italian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Japanese")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Japanese");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Laotian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Laotian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Malaysian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Malaysian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Mexican")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Mexican");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Dutch")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Dutch");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("New Zealander")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("New Zealander");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Norwegian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Norwegian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Romanian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Romanian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Russian")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Russian");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Korean")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Korean");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Spanish")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Spanish");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Swedish")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Swedish");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Swiss")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Swiss");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Thai")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Thai");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("British")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("British");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("American")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("American");
                            nationalityRepository.save(nationalityEntity);
                        }
                        if (nationality.equals("Vietnamese")) {
                            NationalityEntity nationalityEntity = new NationalityEntity();
                            nationalityEntity.setNationalityName("Vietnamese");
                            nationalityRepository.save(nationalityEntity);
                        }
                    });

            // Sumitra
            StatusPaymentEntity statusPayment1 = new StatusPaymentEntity();
            statusPayment1.setStatusPaymentTypeName("จ่าย");
            statusPaymentRepository.save(statusPayment1);

            StatusPaymentEntity statusPayment2 = new StatusPaymentEntity();
            statusPayment2.setStatusPaymentTypeName("ไม่จ่าย");
            statusPaymentRepository.save(statusPayment2);

            // Create Hotel RoomType Many
            Stream.of("Standard", "Suite", "Deluxe", "Superior").forEach(roomType -> {
                RoomTypeEntity rt = roomTypeRepository.findRoomTypeByName(roomType);
                HotelEntity phimainin = hotelRepository.findByhotelNameEng("PhimaiIn");
                HotelRoomTypeManyToManyEntity s = new HotelRoomTypeManyToManyEntity();
                s.setNewHotelEntity(phimainin);
                s.setNewRoomTypeEntity(rt);
                hotelRoomTypeManyToManyRepository.save(s);
            });

            Stream.of("Standard", "Suite", "Deluxe").forEach(roomType -> {
                RoomTypeEntity rt = roomTypeRepository.findRoomTypeByName(roomType);
                HotelEntity phimainin = hotelRepository.findByhotelNameEng("Amathara");
                HotelRoomTypeManyToManyEntity s = new HotelRoomTypeManyToManyEntity();
                s.setNewHotelEntity(phimainin);
                s.setNewRoomTypeEntity(rt);
                hotelRoomTypeManyToManyRepository.save(s);
            });
            // Pup

            PromotionTypeEntity teqi1 = new PromotionTypeEntity("1 day 2000");
            PromotionTypeEntity teqi2 = new PromotionTypeEntity("2 day 3000");
            PromotionTypeEntity teqi3 = new PromotionTypeEntity("3 day 4000");
            promotionTypeRepository.save(teqi1);
            promotionTypeRepository.save(teqi2);
            promotionTypeRepository.save(teqi3);

            // Create TypeTime
            Stream.of("Morning", "Afternoon", "Evening", "Morning-Afternoon","Morning-Evenning","Afternoon-Evening").forEach(typeTime -> {
                TypeTimeEntity tt = new TypeTimeEntity();
                tt.setTypeTimeName(typeTime);
                typeTimeRepository.save(tt);

            });

            // Create MeetingRoomType
            Stream.of("ประชุม", "สัมมนา", "จัดงานเลี้ยง").forEach(meetingEventRoomType -> {
                MeetingEventRoomTypeEntity mert = new MeetingEventRoomTypeEntity();
                mert.setMeetingEventRoomTypeName(meetingEventRoomType);
                meetingEventRoomTypeRepository.save(mert);

            });
            Stream.of("ชุดโต๊ะประชุม","เก้าอี้","เวที").forEach(function -> {
                FunctionEntity func = new FunctionEntity();
                func.setFunctonName(function);
                functionRepository.save(func);
            });

            //SP2 Reservation MeetingRoom
            // Create Hotel RoomType Many

            Stream.of("ประชุม", "สัมมนา", "จัดงานเลี้ยง").forEach(DD -> {
                MeetingEventRoomTypeEntity mrt = meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName(DD);
                HotelEntity phimainin = hotelRepository.findByhotelNameEng("PhimaiIn");
                HotelMeetingEventRoomTypeManyToManyEntity s = new HotelMeetingEventRoomTypeManyToManyEntity();
                s.setNewHotelEntity(phimainin);
                s.setNewMeetingEventRoomTypeEntity(mrt);
                hotelMeetingEventRoomTypeManyToManyRepository.save(s);
            });
            Stream.of("ประชุม", "สัมมนา").forEach(meetingRoomType -> {
                MeetingEventRoomTypeEntity mrt = meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName(meetingRoomType);
                HotelEntity Amathara = hotelRepository.findByhotelNameEng("Amathara");
                HotelMeetingEventRoomTypeManyToManyEntity s = new HotelMeetingEventRoomTypeManyToManyEntity();
                s.setNewHotelEntity(Amathara);
                s.setNewMeetingEventRoomTypeEntity(mrt);
                hotelMeetingEventRoomTypeManyToManyRepository.save(s);
            });
  
            			//BookingCar
			//CarType
			Stream.of("City", "Sedan", "SUVs", "Van", "Fourwheeler", "Other").forEach(carType -> {
				CarTypeEntity carTypeEntity = new CarTypeEntity();
				carTypeEntity.setCarType(carType);
				carTypeRepository.save(carTypeEntity);
			});
			//CarBrand
			Stream.of("MiniCooper", "Toyota", "BMW", "Honda", "MG", "Nissan", "Mitsubishi", "Mazda", "Suzuki", "Other").forEach(carBrand -> {
				CarBrandEntity carBrandEntity = new CarBrandEntity();
				carBrandEntity.setCarBrand(carBrand);
				carBrandRepository.save(carBrandEntity);
			});
			//Car
			Stream.of("Mitsubishi Attrage 2018", "Mazda 2 2010", "Toyota Altis 2012", "Toyota Sienta 2019", "Mitsubishi Pajero sport 2011", "Suzuki Ciaz 2018"
					, "Honda City 2012", "MG 3 2018", "Toyota Yaris 2016", "Toyota Vios 2018", "Toyota Innova 2015", "Toyota Fortuner 2012"
					, "BMW X1 2012", "Toyota Alphard 2014", "Nissan Juke 2015", "Mini Cooper Hatch 3 door 2008", "BMW 520i 2013", "Mini Cooper Hatch 3 door 2015"
					, "Aston martin Vanquish 1972", "BMW Series 5 2018", "Hyundai H1 2013", "Audi TT S 2009", "Chevrolet Camaro 2017", "Volvo S90 2018").forEach(car -> {
				if (car.equals("Mitsubishi Attrage 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mitsubishi Attrage 2018");
					carEntity.setCarPrice(650);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(1L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(7L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Mazda 2 2010")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mazda 2 2010");
					carEntity.setCarPrice(550);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(1L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(8L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Altis 2012")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Altis 2012");
					carEntity.setCarPrice(970);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Sienta 2019")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Sienta 2019");
					carEntity.setCarPrice(1500);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(3L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Mitsubishi Pajero sport 2011")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mitsubishi Pajero sport 2011");
					carEntity.setCarPrice(1500);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(3L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(7L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Suzuki Ciaz 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Suzuki Ciaz 2018");
					carEntity.setCarPrice(900);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(9L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Honda City 2012")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Honda City 2012");
					carEntity.setCarPrice(899);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(4L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("MG 3 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("MG 3 2018");
					carEntity.setCarPrice(1000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(1L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(5L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Yaris 2016")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Yaris 2016");
					carEntity.setCarPrice(960);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(1L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Vios 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Vios 2018");
					carEntity.setCarPrice(1250);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Innova 2015")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Innova 2015");
					carEntity.setCarPrice(2000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(4L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Fortuner 2012")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Fortuner 2012");
					carEntity.setCarPrice(1900);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(5L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("BMW X1 2012")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("BMW X1 2012");
					carEntity.setCarPrice(3500);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(3L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(3L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Toyota Alphard 2014")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Toyota Alphard 2014");
					carEntity.setCarPrice(5500);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(4L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(2L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Nissan Juke 2015")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Nissan Juke 2015");
					carEntity.setCarPrice(1700);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(3L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(6L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Mini Cooper Hatch 3 door 2008")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mini Cooper Hatch 3 door 2008");
					carEntity.setCarPrice(2900);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(6L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(1L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("BMW 520i 2013")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("BMW 520i 2013");
					carEntity.setCarPrice(6500);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(3L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Mini Cooper Hatch 3 door 2015")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mini Cooper Hatch 3 door 2015");
					carEntity.setCarPrice(5000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(1L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(1L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Aston martin Vanquish 1972")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Aston martin Vanquish 1972");
					carEntity.setCarPrice(200000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(3L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("BMW Series 5 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("BMW Series 5 2018");
					carEntity.setCarPrice(12000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(3L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Mercedes Benz E Coupe 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Mercedes Benz E Coupe 2018");
					carEntity.setCarPrice(16900);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(6L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Hyundai H1 2013")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Hyundai H1 2013");
					carEntity.setCarPrice(3000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(6L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Audi TT S 2009")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Audi TT S 2009");
					carEntity.setCarPrice(7000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(6L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Chevrolet Camaro 2017")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Chevrolet Camaro 2017");
					carEntity.setCarPrice(15000);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(6L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}

				if (car.equals("Volvo S90 2018")) {
					CarEntity carEntity = new CarEntity();
					carEntity.setCarName("Volvo S90 2018");
					carEntity.setCarPrice(9900);
					CarTypeEntity carTypeEntity = carTypeRepository.findBycartypeId(2L);
					carEntity.setCartype(carTypeEntity);
					CarBrandEntity carBrandEntity = carBrandRepository.findBycarbrandId(10L);
					carEntity.setCarbrand(carBrandEntity);
					carRepository.save(carEntity);
				}
            });
                //Create Employee
			    EmployeeEntity emp = new EmployeeEntity();
			    emp.setEmployeeUserName("E1234");
			    emp.setEmployeePassword("asdf1234");
			    emp.setNewHotelEntity(hotels);
                employeeRepository.save(emp);
            
                Stream.of("มือถือ","กระเป๋า","เสื้อ","การเกง").forEach(itemName->{
                    ItemEntity item2=new ItemEntity();
                    item2.setItemName(itemName);
                    itemRepository.save(item2);
                });

                BankEntity b1 = new BankEntity("กรุงไทย");
                BankEntity b2 = new BankEntity("ไทยพาณิชย์");
                BankEntity b3 = new BankEntity("กสิกรไทย");
                bankRepository.save(b1);
                bankRepository.save(b2);
                bankRepository.save(b3);
        };

    }
}