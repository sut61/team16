package sut.se.g16.Controller;



import java.util.Collection;

import java.util.Date;

import java.util.stream.Collectors;



import javax.xml.crypto.Data;



import sut.se.g16.Entity.*;

import sut.se.g16.Repository.*;



import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:4200")

@RestController

public class ReservationRoomController{

    private ReservationRoomRepository reservationRoomRepository;

    private RoomTypeRepository roomTypeRepository;

    private HotelRepository hotelRepository;

    private CustomerRepository customerRepository;

    private PromotionRepository promotionRepository;

    private StatusPaymentRepository statusPaymentRepository;

    private HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository;

    private RoomRepository roomRepository;

    private RoomStatusRepository roomStatusRepository;

    public ReservationRoomController(RoomStatusRepository roomStatusRepository,RoomRepository roomRepository,HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository,ReservationRoomRepository reservationRoomRepository, RoomTypeRepository roomTypeRepository, HotelRepository hotelRepository, CustomerRepository customerRepository, PromotionRepository promotionRepository,StatusPaymentRepository statusPaymentRepository) {

        this.reservationRoomRepository = reservationRoomRepository;

        this.roomTypeRepository = roomTypeRepository;

        this.hotelRepository = hotelRepository;

        this.customerRepository = customerRepository;

        this.promotionRepository = promotionRepository;

        this.statusPaymentRepository = statusPaymentRepository;

        this.hotelRoomTypeManyToManyRepository = hotelRoomTypeManyToManyRepository;

        this.roomRepository = roomRepository;

        this.roomStatusRepository = roomStatusRepository;

    }



    @GetMapping("/reservationrooms")

    public Collection<ReservationRoomEntity> ReservationRoom() {

        return reservationRoomRepository.findAll().stream()

                .collect(Collectors.toList());

    }



    @GetMapping("/reservationroom/{customerName}")

    public Collection<ReservationRoomEntity> getReservationByCustomerName(@PathVariable String customerName) {

        return reservationRoomRepository.findAllByCustomerName(customerName);

    }
//   reservationroom/inputStartDate/inputEndDate/roomTypeSelect/cus/.promotionSelect /hotelSelect /roomSelect /comment


    @GetMapping("/reservationroom/{datein}/{dateout}/{roomType}/{cus}/{pro}/{hotel}/{roomId}/{comment}")

    public ReservationRoomEntity ReservationRoom(@PathVariable Long roomId, @PathVariable String datein,@PathVariable String dateout,@PathVariable long roomType,@PathVariable Long hotel,@PathVariable String cus, @PathVariable Long pro,@PathVariable String comment){

        //กำหนดสถานนะการจองว่า จ่ายแล้ว เพื่อเพื่อนอีกระบบรีวิว จะได้ดึงไปทำ รีวิว ซึ่งต้อง checkout ห้องออกก่อน แต่ ระบบจ่ายเงินอยู่ในส่วนของ sprint 2 

        StatusPaymentEntity status = statusPaymentRepository.findByStatusPaymentTypeName("จ่าย");



        RoomEntity room1 = roomRepository.findRoomByRoomId(roomId);



        //เมื่อกดจองห้อง ห้องจะมีสถานนะว่าจอง

        RoomStatusEntity rst = roomStatusRepository.findByName("จอง");

        room1.setNewRoomStatusEntity(rst);

        roomRepository.save(room1);

        Date d1 =new Date();

        ReservationRoomEntity reserRoom = new ReservationRoomEntity();

        reserRoom.setDateIn(d1);

        reserRoom.setDateOut(d1);
        reserRoom.setCommentReserroom(comment);

        reserRoom.setNewRoomEntity(room1);

        // reserRoom.setTotalPriceReservationRoom(totalPriceReservationRoom);

        HotelEntity h = hotelRepository.findByHotelId(hotel);

        System.out.println(h);

        reserRoom.setNewHotelEntity(h);

        RoomTypeEntity room = roomTypeRepository.findByRoomTypeId(roomType);

        reserRoom.setNewRoomTypeEntity(room);

        CustomerEntity customer = customerRepository.findCustomerByName(cus);

        reserRoom.setNewCustomerEntity(customer);

        PromotionEntity promotion = promotionRepository.findByPromotionId(pro);

        reserRoom.setNewPromotionEntity(promotion);

        reserRoom.setNewStatusPaymentEntity(status);

        return reservationRoomRepository.save(reserRoom);

      



    }

    //Promotion Controller



    @GetMapping("/promotions")

    public Collection<PromotionEntity> Promotion() {

        return promotionRepository.findAll().stream()

                .collect(Collectors.toList());

    }





    @GetMapping("/promotion/{datestart}/{dateend}/{detail}")

    public PromotionEntity newPromotion(@PathVariable Date dateStart,@PathVariable Date dateEnd,@PathVariable String detail) {

        Date d = new Date(); 

        PromotionEntity pro = new PromotionEntity();

        pro.setDetail(detail);

        pro.setDateStart(d);

        pro.setDateEnd(d);

        return  promotionRepository.save(pro);

    }



    //RoomType Controller



    @GetMapping("/roomtype/{name}/{bed}/{number}/{people}")

    public RoomTypeEntity RoomType(@PathVariable String name,@PathVariable String bed,@PathVariable Long number,@PathVariable Long people) {

        RoomTypeEntity set = new RoomTypeEntity();

        set.setBedType(bed);

        set.setMaxPeople(people);

        set.setRoomTypeName(name);

        set.setNumberOfBed(number);

        return roomTypeRepository.save(set);

    }



    //StatusPayment Controller

    @GetMapping("/statuspayments")

    public Collection<StatusPaymentEntity> StatusPayment() {

        return statusPaymentRepository.findAll().stream()

                .collect(Collectors.toList());

    }





    @GetMapping("/statuspayment/{status}")

    public StatusPaymentEntity newStatusPaymentEntity(@PathVariable String status) {

       StatusPaymentEntity statusPayment = new StatusPaymentEntity();

       statusPayment.setStatusPaymentTypeName(status);

        return  statusPaymentRepository.save(statusPayment);

    }

    

    @GetMapping("/roomtype/{hotelId}")

    public Collection<HotelRoomTypeManyToManyEntity> getRoomTypeByHotelId(@PathVariable Long hotelId) {

        return  hotelRoomTypeManyToManyRepository.findAllByHotelId(hotelId);

    }



    @GetMapping("/getroombyroomtype/{roomTypeId}")

    public Collection<RoomEntity> getRoomByRoomTypeId(@PathVariable Long roomTypeId) {

        String rst = "ว่าง";

        Long roomStatusId = roomStatusRepository.findIdByName(rst);

        return  roomRepository.findRoomByRoomTypeId(roomTypeId,roomStatusId);

    }

    







    

    

}