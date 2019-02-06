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
            RoomTypeRepository roomTypeRepository, RoomRepository roomRepository, AdminRepository adminRepository,
            PromotionTypeRepository promotionTypeRepository, TypeTimeRepository typeTimeRepository,
            MeetingEventRoomTypeRepository meetingEventRoomTypeRepository,
            RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository,
            FoodOrderRepository foodOrderRepository, ListRepository listRepository,
            FoodTypeRepository foodTypeRepository, TotalPriceFoodRepository totalPriceFoodRepository,
            ProvinceRepository provinceRepository, FoodPaymentRepository foodPaymentRepository,
            HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository,
            FoodOrderTotalPriceFoodManyToManyRepository foodOrderTotalPriceFoodManyToManyRepository,
            CustomerRepository customerRepository, NationalityRepository nationalityRepository,
            SexRepository sexRepository, TitleNameRepository titleNameRepository) {

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
                    rt.setNumberOfBed(1);
                    rt.setMaxPeople(2);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Suite") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(1);
                    rt.setMaxPeople(3);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Deluxe") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(1);
                    rt.setMaxPeople(3);
                    roomTypeRepository.save(rt);
                }
                if (roomType == "Superior") {
                    RoomTypeEntity rt = new RoomTypeEntity();
                    rt.setRoomTypeName(roomType);
                    rt.setBedType("Twice");
                    rt.setNumberOfBed(2);
                    rt.setMaxPeople(6);
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
            hotels.setPhone("0862505906");
            hotels.setVillage("เจริญรอด");
            hotels.setRoad("แจ้งวัฒนะ");
            hotels.setSubDistrictSubArea("ในเมือง");
            hotels.setVillageNo(20);
            hotels.setPostCode(30110);
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
            hotel.setPhone("0862505906");
            hotel.setVillage("เจริญรอด");
            hotel.setRoad("แจ้งวัฒนะ");
            hotel.setSubDistrictSubArea("ในเมือง");
            hotel.setVillageNo(20);
            hotel.setPostCode(30110);
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
                if (foodtype == "Drink") {
                    Stream.of("Cocacola", "Soda", "Beer", "whiskey").forEach(list -> {
                        if (list.equals("Cocacola")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Cocacola");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(20L);
                            listRepository.save(li);
                        }

                        if (list.equals("Soda")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Soda");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(15L);
                            listRepository.save(li);
                        }

                        if (list.equals("Beer")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Beer");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(80L);
                            listRepository.save(li);
                        }

                        if (list.equals("whiskey")) {
                            ListEntity li = new ListEntity();
                            li.setListName("whiskey");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(2000L);
                            listRepository.save(li);
                        }
                    });

                } else if (foodtype == "Food") {
                    Stream.of("Tomyumkung", "Papaya salad", "Fried rice with Crab", "Soup").forEach(list -> {
                        if (list.equals("Tomyumkung")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Tomyumkung");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(150L);
                            listRepository.save(li);
                        }

                        if (list.equals("Papaya salad")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Papaya salad");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(80L);
                            listRepository.save(li);
                        }

                        if (list.equals("Fried rice with Crab")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Fried rice with Crab");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(100L);
                            listRepository.save(li);
                        }

                        if (list.equals("Soup")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Soup");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(150L);
                            listRepository.save(li);
                        }
                    });

                } else if (foodtype == "Dessert") {
                    Stream.of("Cupcakes", "Brownies", "Cookies").forEach(list -> {
                        if (list.equals("Cupcakes")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Cupcakes");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(60L);
                            listRepository.save(li);
                        }

                        if (list.equals("Brownies")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Brownies");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(70L);
                            listRepository.save(li);
                        }

                        if (list.equals("Cookies")) {
                            ListEntity li = new ListEntity();
                            li.setListName("Cookies");
                            li.setNewFoodTypeEntity(food);
                            li.setPriceFood(50L);
                            listRepository.save(li);
                        }

                    });

                }
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
            AdminEntity e1 = new AdminEntity();
            e1.setAdminName("Tata");
            adminRepository.save(e1);

            PromotionTypeEntity teqi1 = new PromotionTypeEntity("1 day 2000");
            PromotionTypeEntity teqi2 = new PromotionTypeEntity("2 day 3000");
            PromotionTypeEntity teqi3 = new PromotionTypeEntity("3 day 4000");
            promotionTypeRepository.save(teqi1);
            promotionTypeRepository.save(teqi2);
            promotionTypeRepository.save(teqi3);

            // Create TypeTime
            Stream.of("Morning", "Afternoon", "Evening").forEach(typeTime -> {
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
        };

    }
}