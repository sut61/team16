package sut.se.g16.Controller;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.Return;

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
    private FoodStockRepository foodStockRepository;
    @Autowired
    private FoodPaymentRepository foodPaymentRepository;
    @Autowired
    private FoodOrderTotalPriceFoodManyToManyRepository foodOrderTotalPriceFoodManyToManyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public FoodOrderController(CustomerRepository customerRepository, FoodOrderRepository foodOrderRepository,
            ListRepository listRepository, FoodTypeRepository foodTypeRepository,
            TotalPriceFoodRepository totalPriceFoodRepository, RoomRepository roomRepository,
            HotelRepository hotelRepository, ProvinceRepository provinceRepository,
            FoodPaymentRepository foodPaymentRepository, FoodStockRepository foodStockRepository,
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

    @GetMapping(path = "/room/{hotel}")
    public Collection<RoomEntity> RoomEntity(@PathVariable String hotel) {
        Long hotelId = hotelRepository.findHotelIdByName(hotel);
        return roomRepository.findRoomByHotelId(hotelId);
    }

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

    @GetMapping(path = "/listprice/{listname}")
    public Long newStockEntity(@PathVariable String listname) {
        return listRepository.findPriceByListName(listname);
    }

    // @GetMapping("/list/{nametype}/{foodname}/{pricefood}/{amount}")
    // public ListEntity newList(@PathVariable String nametype, @PathVariable String
    // foodname,
    // @PathVariable int pricefood, @PathVariable Long amount) {
    // ListEntity set = new ListEntity();
    // FoodTypeEntity nt = foodTypeRepository.findByName(nametype);
    // set.setNewFoodTypeEntity(nt);
    // return listRepository.save(set);
    // }

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

    @GetMapping(path = "/foodorder/{hotel}/{room}/{list}/{customer}/{amount}")
    public Boolean newFoodOrder(@PathVariable String hotel, @PathVariable String room, @PathVariable String list,
            @PathVariable String customer, @PathVariable Long amount) {
        ListEntity li = listRepository.findListByName(list);
        // Long li = listRepository.findListIdByName(list);
        Long ftid = listRepository.findFoodTypeIdByName(list);
        FoodTypeEntity fte = foodTypeRepository.findAllByFoodTypeId(ftid);

        CustomerEntity cus = customerRepository.findByUser(customer);
        HotelEntity ho = hotelRepository.findByName(hotel);
        Long listPrice = listRepository.findPriceByListName(list);
        Long hotelId = hotelRepository.findHotelIdByName(hotel);
        Long roomId = roomRepository.findRoomIdByHotelIdAndRoomNumber(room, hotelId);

        RoomEntity rm = roomRepository.findRoomByRoomId(roomId);
        Long foodPaymentId = foodPaymentRepository.findFoodPaymentIdByStatus("ยังไม่จ่าย");
        System.out.println(foodPaymentId);
        Long totalPriceId = totalPriceFoodRepository.findTotalPriceFoodIdByRoomIdAndFoodPaymentId(roomId,
                foodPaymentId);
        System.out.print(totalPriceId);

        FoodPaymentEntity foodPay = foodPaymentRepository.findByStatus("ยังไม่จ่าย");

        if (fte.getFoodTypeName().equals("Drink")) {
            if (li.getTotalAmount() - amount >= 0) {
                FoodOrderEntity foodOrder = new FoodOrderEntity();
                foodOrder.setTotalProiceOrder(listPrice * amount);
                foodOrder.setNewCustomerEntity(cus);
                foodOrder.setNewHotelEntity(ho);
                foodOrder.setNewListEntity(li);
                foodOrder.setNewRoomEntity(rm);
                foodOrderRepository.save(foodOrder);

                li.setTotalAmount(li.getTotalAmount()-amount);
                listRepository.save(li);

                TotalPriceFoodEntity totalPrice = new TotalPriceFoodEntity();
                if (totalPriceId == null) {
                    totalPrice.setNewFoodPaymentEntity(foodPay);
                    totalPrice.setNewRoomEntity(rm);
                    totalPrice.setTotalPrice(amount * listPrice);
                    totalPriceFoodRepository.save(totalPrice);

                    FoodOrderTotalPriceFoodManyToManyEntity fopmm = new FoodOrderTotalPriceFoodManyToManyEntity();
                    fopmm.setNewFoodOrderEntity(foodOrder);
                    fopmm.setNewTotalPriceFoodEntity(totalPrice);
                    foodOrderTotalPriceFoodManyToManyRepository.save(fopmm);
                    return true;
                } else {
                    totalPriceFoodRepository.findById(totalPriceId).map(totalPriceEntity -> {
                        Long price = totalPriceEntity.getTotalPrice();
                        price += (amount * listPrice);
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
            return false;
        }
        else{
            FoodOrderEntity foodOrder = new FoodOrderEntity();
                foodOrder.setTotalProiceOrder(listPrice * amount);
                foodOrder.setNewCustomerEntity(cus);
                foodOrder.setNewHotelEntity(ho);
                foodOrder.setNewListEntity(li);
                foodOrder.setNewRoomEntity(rm);
                foodOrderRepository.save(foodOrder);
                TotalPriceFoodEntity totalPrice = new TotalPriceFoodEntity();
                if (totalPriceId == null) {
                    totalPrice.setNewFoodPaymentEntity(foodPay);
                    totalPrice.setNewRoomEntity(rm);
                    totalPrice.setTotalPrice(amount * listPrice);
                    totalPriceFoodRepository.save(totalPrice);

                    FoodOrderTotalPriceFoodManyToManyEntity fopmm = new FoodOrderTotalPriceFoodManyToManyEntity();
                    fopmm.setNewFoodOrderEntity(foodOrder);
                    fopmm.setNewTotalPriceFoodEntity(totalPrice);
                    foodOrderTotalPriceFoodManyToManyRepository.save(fopmm);
                    return true;
                } else {
                    totalPriceFoodRepository.findById(totalPriceId).map(totalPriceEntity -> {
                        Long price = totalPriceEntity.getTotalPrice();
                        price += (amount * listPrice);
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
