package sut.se.g16.Controller;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodOrderController {
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private FoodTypeRepository foodTypeRepository;
    @Autowired
    private TotalPriceFoodRepository totalPriceFoodRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private FoodPaymentRepository foodPaymentRepository;
    @Autowired
    private FoodOrderTotalPriceFoodManyToManyRepository foodOrderTotalPriceFoodManyToManyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public FoodOrderController(CustomerRepository customerRepository, FoodOrderRepository foodOrderRepository, ListRepository listRepository,
            FoodTypeRepository foodTypeRepository, TotalPriceFoodRepository totalPriceFoodRepository,
            RoomRepository roomRepository, HotelRepository hotelRepository, ProvinceRepository provinceRepository,
            FoodPaymentRepository foodPaymentRepository,
            FoodOrderTotalPriceFoodManyToManyRepository foodOrderTotalPriceFoodManyToManyRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.listRepository = listRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.totalPriceFoodRepository = totalPriceFoodRepository;
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.provinceRepository = provinceRepository;
        this.foodPaymentRepository = foodPaymentRepository;
        this.foodOrderTotalPriceFoodManyToManyRepository = foodOrderTotalPriceFoodManyToManyRepository;
    }

    // /* Province */
    // @GetMapping("/provinces")
    // public Collection<ProvinceEntity> provinces() {
    //     return provinceRepository.findAll().stream().collect(Collectors.toList());
    // }

    // @GetMapping("/province/{name}")
    // public ProvinceEntity newProvince(@PathVariable String name) {
    //     ProvinceEntity p = new ProvinceEntity();
    //     p.setProvinceName(name);
    //     return this.provinceRepository.save(p);
    // }

    /* Hotal */
    // @GetMapping("/hotels")
    // public Collection<HotelEntity> hotel() {
    //     return hotelRepository.findAll().stream().collect(Collectors.toList());
    // }

    // @GetMapping("/hotel/{name}/{villageNo}/{houseNo}/{building}/{village}/{alleyLane}/{road}/{subDistrictSubArea}/{districtArea}/{postCode}/{mobilePhone}/{phone}/{fax}/{province}")
    // public HotelEntity newHotel(@PathVariable final String name, @PathVariable String province,
    //         @PathVariable int villageNo, @PathVariable String houseNo, @PathVariable String building,
    //         @PathVariable String village, @PathVariable String alleyLane, @PathVariable String road,
    //         @PathVariable String subDistrictSubArea, @PathVariable String districtArea, @PathVariable int postCode,
    //         @PathVariable String mobilePhone, @PathVariable String phone, @PathVariable String fax,
    //         @PathVariable String memberName) {
    //     ProvinceEntity p = provinceRepository.findByName(province);
    //     HotelEntity hotels = new HotelEntity();
    //     hotels.setHotelNameEng(name);
    //     hotels.setVillageNo(villageNo);
    //     hotels.setHouseNo(houseNo);
    //     hotels.setBuilding(building);
    //     hotels.setVillage(village);
    //     hotels.setAlleyLane(alleyLane);
    //     hotels.setRoad(road);
    //     hotels.setSubDistrictSubArea(subDistrictSubArea);
    //     hotels.setDistrictArea(districtArea);
    //     hotels.setPostCode(postCode);
    //     hotels.setMobilePhone(mobilePhone);
    //     hotels.setPhone(phone);
    //     hotels.setFax(fax);
    //     hotels.setNewProvinceEntity(p);
    //     return hotelRepository.save(hotels);
    // }

    // /* Room */
    // @GetMapping(path = "/rooms")
    // public Collection<RoomEntity> RoomEntity() {
    //     return roomRepository.findAll().stream().collect(Collectors.toList());
    // }

    @GetMapping(path = "/room/{hotel}")
    public Collection<RoomEntity> RoomEntity(@PathVariable String hotel) {
        Long hotelId = hotelRepository.findHotelIdByName(hotel);
        return roomRepository.findRoomByHotelId(hotelId);
    }

    // @GetMapping("/room/{roomnumber}/{roomprice}/{hotel}")
    // public RoomEntity newRoom(@PathVariable int roomnumber, @PathVariable int roomprice, @PathVariable String hotel) {
    //     RoomEntity set = new RoomEntity();
    //     HotelEntity ho = hotelRepository.findByName(hotel);
    //     set.setRoomNumber(roomnumber);
    //     set.setRoomPrice(roomprice);
    //     set.setNewHotelEntity(ho);
    //     return roomRepository.save(set);
    // }

    /* Food Type */
    @GetMapping(path = "/foodtypes")
    public Collection<FoodTypeEntity> FoodTypeEntity() {
        return foodTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/foodtype/{nametype}")
    public FoodTypeEntity newFoodType(@PathVariable String nametype) {
        FoodTypeEntity set = new FoodTypeEntity();
        set.setFoodTypeName(nametype);
        return foodTypeRepository.save(set);
    }

    /* List */
    @GetMapping(path = "/lists")
    public Collection<ListEntity> ListEntity() {
        return listRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/list/{foodType}")
    public Collection<ListEntity> ListEntity(@PathVariable String foodType) {
        Long foodTypeId = foodTypeRepository.findFoodTypeIdByName(foodType);
        return listRepository.findListByFoodTypeId(foodTypeId);
    }
    @GetMapping(path = "/listprice/{list}")
    public Long newListEntity(@PathVariable String list) {
        return listRepository.findPriceByList(list);
    }

    @GetMapping("/list/{nametype}/{foodname}/{pricefood}")
    public ListEntity newList(@PathVariable String nametype, @PathVariable String foodname,
            @PathVariable int pricefood) {
        ListEntity set = new ListEntity();
        FoodTypeEntity nt = foodTypeRepository.findByName(nametype);
        set.setNewFoodTypeEntity(nt);
        return listRepository.save(set);
    }

    /* Total Price Food */
    @GetMapping(path = "/totalpricefoods")
    public Collection<TotalPriceFoodEntity> TotalPriceFoodEntity() {
        String status = "ยังไม่จ่าย";
        return totalPriceFoodRepository.findAllByFoodPayment(status);
    }
    @GetMapping(path = "/totalprice/{totalpriceId}")
    public Long total(@PathVariable Long totalpriceId) {
        return totalPriceFoodRepository.findTotalByTotalPriceId(totalpriceId);
    }

    /* Food Order */
    @GetMapping(path = "/foodorders")
    public Collection<FoodOrderEntity> FoodOrderEntity() {
        return foodOrderRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/foodorder/{hotel}/{room}/{list}/{customer}")
    public Boolean newFoodOrder(@PathVariable String hotel, @PathVariable int room, @PathVariable String list, @PathVariable String customer) {
        CustomerEntity cus = customerRepository.findByUser(customer);
        HotelEntity ho = hotelRepository.findByName(hotel);
        ListEntity li = listRepository.findListByName(list);
        System.out.println("List Price" + li.getPriceFood());
        Long hotelId = hotelRepository.findHotelIdByName(hotel);
        Long roomId = roomRepository.findRoomIdByHotelIdAndRoomNumber(room, hotelId);

        RoomEntity rm = roomRepository.findRoomByRoomId(roomId);
        Long foodPaymentId = foodPaymentRepository.findFoodPaymentIdByStatus("ยังไม่จ่าย");
        System.out.println(foodPaymentId);
        Long totalPriceId = totalPriceFoodRepository.findTotalPriceFoodIdByRoomIdAndFoodPaymentId(roomId,
                foodPaymentId);
        System.out.print(totalPriceId);

        FoodPaymentEntity foodPay = foodPaymentRepository.findByStatus("ยังไม่จ่าย");

        FoodOrderEntity foodOrder = new FoodOrderEntity();
        foodOrder.setNewCustomerEntity(cus);
        foodOrder.setNewHotelEntity(ho);
        foodOrder.setNewListEntity(li);
        foodOrder.setNewRoomEntity(rm);
        foodOrderRepository.save(foodOrder);
        TotalPriceFoodEntity totalPrice = new TotalPriceFoodEntity();
        if (totalPriceId == null) {

            totalPrice.setNewFoodPaymentEntity(foodPay);
            totalPrice.setNewRoomEntity(rm);
            totalPrice.setTotalPrice(li.getPriceFood());
            totalPriceFoodRepository.save(totalPrice);

            FoodOrderTotalPriceFoodManyToManyEntity fopmm = new FoodOrderTotalPriceFoodManyToManyEntity();
            fopmm.setNewFoodOrderEntity(foodOrder);
            fopmm.setNewTotalPriceFoodEntity(totalPrice);
            foodOrderTotalPriceFoodManyToManyRepository.save(fopmm);
            return true;
        } else {
            totalPriceFoodRepository.findById(totalPriceId).map(totalPriceEntity -> {
                Long price = totalPriceEntity.getTotalPrice();
                price += li.getPriceFood();
                totalPriceEntity.setTotalPrice(price);
                totalPriceFoodRepository.save(totalPriceEntity);

                FoodOrderTotalPriceFoodManyToManyEntity fopmm = new FoodOrderTotalPriceFoodManyToManyEntity();
                fopmm.setNewFoodOrderEntity(foodOrder);
                fopmm.setNewTotalPriceFoodEntity(totalPriceEntity);
                foodOrderTotalPriceFoodManyToManyRepository.save(fopmm);

                return true;
            }).orElseGet(() -> {
                totalPriceFoodRepository.save(totalPrice);
                return true;
            });

        }
        return true;
    }
    @GetMapping(path = "/updatefoodstatus/{totalpriceId}")
    public Boolean updatefood(@PathVariable Long totalpriceId) {
        FoodPaymentEntity status = foodPaymentRepository.findByStatus("จ่ายแล้ว");
        TotalPriceFoodEntity totalPrice = new TotalPriceFoodEntity();
        totalPriceFoodRepository.findById(totalpriceId).map(total -> {
            total.setNewFoodPaymentEntity(status);
            totalPriceFoodRepository.save(total);
            return true;
        }).orElseGet(() -> {
            totalPriceFoodRepository.save(totalPrice);
            return true;
        });
        return true;
    }


    /* FoodOrder TotalPriceFood ManyToMany */
    @GetMapping(path = "/foodordertotalpricefoods")
    public Collection<FoodOrderTotalPriceFoodManyToManyEntity> FoodOrderTotalPriceFoodManyToManyEntity() {
        return foodOrderTotalPriceFoodManyToManyRepository.findAll().stream().collect(Collectors.toList());
    }

    /* Food Payment */
    @GetMapping(path = "/foodpayments")
    public Collection<FoodPaymentEntity> FoodPaymentEntity() {
        return foodPaymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/foodpayment/{paymentstatus}")
    public FoodPaymentEntity newfoodPayment(@PathVariable String paymentstatus) {
        FoodPaymentEntity set = new FoodPaymentEntity();
        set.setFoodPaymentStatus(paymentstatus);
        return foodPaymentRepository.save(set);
    }

}
