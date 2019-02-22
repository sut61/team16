package sut.se.g16.Controller;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class ReservationMeetingEventRoomController {
    private ReservationMeetingEventRoomRepository reservationMeetingEventRoomRepository;
    private HotelRepository hotelRepository;
    private CustomerRepository customerRepository;
    private PromotionRepository promotionRepository;
    private RoomStatusRepository roomStatusRepository;
    private MeetingEventRoomRepository meetingEventRoomRepository;
    private MeetingEventRoomTypeRepository meetingEventRoomTypeRepository;
    private TypeTimeRepository typeTimeRepository;
    private HotelMeetingEventRoomTypeManyToManyRepository hotelMeetingEventRoomTypeManyToManyRepository;
    private FunctionRepository functionRepository;
    private MeetingEventRoomTypeTimeManyToManyRepository meetingEventRoomTypeTimeManyToManyRepository;

    public ReservationMeetingEventRoomController(
            ReservationMeetingEventRoomRepository reservationMeetingEventRoomRepository,
            RoomStatusRepository roomStatusRepository, HotelRepository hotelRepository,
            CustomerRepository customerRepository, PromotionRepository promotionRepository,
            MeetingEventRoomRepository meetingEventRoomRepository,
            MeetingEventRoomTypeTimeManyToManyRepository meetingEventRoomTypeTimeManyToManyRepository,
            MeetingEventRoomTypeRepository meetingEventRoomTypeRepository, TypeTimeRepository typeTimeRepository,
            HotelMeetingEventRoomTypeManyToManyRepository hotelMeetingEventRoomTypeManyToManyRepository,
            FunctionRepository functionRepository) {
        this.reservationMeetingEventRoomRepository = reservationMeetingEventRoomRepository;
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
        this.promotionRepository = promotionRepository;
        this.roomStatusRepository = roomStatusRepository;
        this.meetingEventRoomRepository = meetingEventRoomRepository;
        this.meetingEventRoomTypeRepository = meetingEventRoomTypeRepository;
        this.typeTimeRepository = typeTimeRepository;
        this.hotelMeetingEventRoomTypeManyToManyRepository = hotelMeetingEventRoomTypeManyToManyRepository;
        this.functionRepository = functionRepository;
        this.meetingEventRoomTypeTimeManyToManyRepository = meetingEventRoomTypeTimeManyToManyRepository;
    }

    @GetMapping("/reservationmeetingeventrooms")
    public Collection<ReservationMeetingEventRoomEntity> ReservationMeetingEventRoom() {
        return reservationMeetingEventRoomRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/reservationmeetingeventroom/{customerName}")
    public Collection<ReservationMeetingEventRoomEntity> getReservationMeetingEventRoomByCustomerName(
            @PathVariable String customerName) {
        return reservationMeetingEventRoomRepository.findAllByCustomerName(customerName);
    }

    
    @GetMapping("/getmeetingeventroomtype/{hotelId}")
    public Collection<HotelMeetingEventRoomTypeManyToManyEntity> getHotelMeetingEventRoomId(
            @PathVariable Long hotelId) {

      //  System.out.println(hotelMeetingEventRoomTypeManyToManyRepository.findAllByHotelId(hotelId));
        return hotelMeetingEventRoomTypeManyToManyRepository.findAllByHotelId(hotelId);
    }

    @GetMapping("/getmeetingeventroom/{meetingEventRoomTypeId}")
    public Collection<MeetingEventRoomEntity> getMeetingEventRoomId(@PathVariable Long meetingEventRoomTypeId) {
        System.out.println("666666666666666666666666666666666666666666");
        System.out.println(meetingEventRoomRepository.findAllByMeetingEventRoomId(meetingEventRoomTypeId));
        return meetingEventRoomRepository.findAllByMeetingEventRoomId(meetingEventRoomTypeId);
    }

    @GetMapping("/meetingeventroomtypetimes/{x}")
    public Collection<MeetingEventRoomTypeTimeManyToManyEntity> test(@PathVariable String x) {
        return meetingEventRoomTypeTimeManyToManyRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/ff")
    public Collection<FunctionEntity> Function() {
        return functionRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/reservationmeetingeventroom/{reservationmeetingeventroomdatein}/{reservationmeetingeventroomdateout}/{meetingeventroomid}/{meetingeventroomtypeid}/{typetime}/{cus}/{hotel}/{detail}/{function}")
    public Boolean ReservationMeetingEventRoom(@PathVariable Date reservationmeetingeventroomdatein,
            @PathVariable Date reservationmeetingeventroomdateout, @PathVariable String meetingeventroomid,
            @PathVariable Long meetingeventroomtypeid, @PathVariable String typetime, @PathVariable Long hotel,
            @PathVariable String cus, @PathVariable String detail, @PathVariable String function) {
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        // กำหนดสถานนะการจองว่า จ่ายแล้ว เพื่อเพื่อนอีกระบบรีวิว จะได้ดึงไปทำ รีวิว
        // ซึ่งต้อง checkout ห้องออกก่อน แต่ ระบบจ่ายเงินอยู่ในส่วนของ sprint 2
        RoomStatusEntity status = roomStatusRepository.findByName("จอง");
        // System.out.println(status);
        CustomerEntity custo = customerRepository.findCustomerByName(cus);
        //System.out.println(custo);
        FunctionEntity fun = functionRepository.findByName(function);
        //System.out.println(fun);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MeetingEventRoomEntity meeting = meetingEventRoomRepository
                .findMeetingEventRoomByMeetingRoomName(meetingeventroomid);

         //System.out.println(meeting);
        MeetingEventRoomTypeEntity meetingType = meetingEventRoomTypeRepository
                .findMeetingEventRoomTypeByMeetingEventRoomTypeId(meetingeventroomtypeid);
         //System.out.println(meetingType);
        HotelEntity ho = hotelRepository.findByHotelId(hotel);
        // System.out.println(ho);
        
        TypeTimeEntity tt = typeTimeRepository.findBytypeTimeName(typetime);
        
        System.out.println("=========777777777777777777777777777777777777==========");
        System.out.println(typetime);
        System.out.println(tt);
       // System.out.println(detail);
        ReservationMeetingEventRoomEntity remeeting = new ReservationMeetingEventRoomEntity();
        remeeting.setDetail(detail);

        remeeting.setReservationMeetingEventRoomDateIn(reservationmeetingeventroomdatein);
        remeeting.setReservationMeetingEventRoomDateOut(reservationmeetingeventroomdateout);
        remeeting.setNewMeetingEventRoomTypeEntity(meetingType);
        remeeting.setNewFunctionEntity(fun);
        remeeting.setNewMeetingEventRoomEntity(meeting);

        remeeting.setNewCustomerEntity(custo);
        remeeting.setNewRoomStatusEntity(status);
        remeeting.setNewHotelEntity(ho);
        remeeting.setNewTypeTimeEntity(tt);
        reservationMeetingEventRoomRepository.save(remeeting);
        int morningStatus = 0, afternoonStatus = 0, eveningStatus = 0;

        Long metId = meetingEventRoomRepository.findMeetingEventRoomIdByMeetingRoomName(meetingeventroomid);
        Long typeTimeId = typeTimeRepository.findTypeTimeIdByName("Morning");
        //System.out.println(metId);
        //System.out.println(typeTimeId);
        MeetingEventRoomTypeTimeManyToManyEntity metmmmorning = meetingEventRoomTypeTimeManyToManyRepository
                .findByMeetingIdandTypeTime(metId, "Morning");
        //System.out.println(metmmmorning);
        MeetingEventRoomTypeTimeManyToManyEntity metmmAfternoon = meetingEventRoomTypeTimeManyToManyRepository
                .findByMeetingIdandTypeTime(metId, "Afternoon");
        //        System.out.println(metmmAfternoon);
        MeetingEventRoomTypeTimeManyToManyEntity metmEvenning = meetingEventRoomTypeTimeManyToManyRepository
                .findByMeetingIdandTypeTime(metId, "Evening");
         //       System.out.println(metmEvenning);
        if (typetime.contains("Morning") && typetime.contains("Evening")) {
            if (metmmmorning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                morningStatus = 1;
            }
            if (metmmAfternoon.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                afternoonStatus = 1;
            }
            if (metmEvenning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                eveningStatus = 1;
            }
            if (morningStatus == 1 && afternoonStatus == 1 && eveningStatus == 1) {
                metmmmorning.setNewRoomStatusEntity(status);
                metmmAfternoon.setNewRoomStatusEntity(status);
                metmEvenning.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmmorning);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmAfternoon);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmEvenning);
                return true;
            } else
                return false;
        } else if (typetime.contains("Morning") && typetime.contains("Afternoon")) {
            if (metmmmorning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                morningStatus = 1;
            }
            if (metmmAfternoon.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                afternoonStatus = 1;
            }
            if (morningStatus == 1 && afternoonStatus == 1) {
                metmmmorning.setNewRoomStatusEntity(status);
                metmmAfternoon.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmmorning);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmAfternoon);
                return true;
            } else
                return false;
        } else if (typetime.contains("Evening") && typetime.contains("Afternoon")) {
            if (metmEvenning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                eveningStatus = 1;
            }
            if (metmmAfternoon.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                afternoonStatus = 1;
            }
            if (eveningStatus == 1 && afternoonStatus == 1) {
                metmEvenning.setNewRoomStatusEntity(status);
                metmmAfternoon.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmEvenning);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmAfternoon);
                return true;
            } else
                return false;
        } else if (typetime.contains("Morning")) {
            if (metmmmorning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")){
                metmmmorning.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmmorning);
                return true;
            } else
                return false;
        } else if (typetime.contains("Afternoon")) {
            if (metmmAfternoon.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                metmmAfternoon.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmmAfternoon);
                return true;
            } else
                return false;
        } else if (typetime.contains("Evening")) {
            if (metmEvenning.getNewRoomStatusEntity().getRoomStatusName().equals("ว่าง")) {
                metmEvenning.setNewRoomStatusEntity(status);
                meetingEventRoomTypeTimeManyToManyRepository.save(metmEvenning);
                return true;
            } else
                return false;
        }

        return true;
    }

    // Promotion Controller

    @GetMapping("/meetingpromotions")
    public Collection<PromotionEntity> Promotion() {
        return promotionRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/meetingpromotion/{datestart}/{dateend}/{detail}")
    public PromotionEntity newPromotion(@PathVariable Date dateStart, @PathVariable Date dateEnd,
            @PathVariable String detail) {
        Date d = new Date();
        PromotionEntity pro = new PromotionEntity();
        pro.setDetail(detail);
        pro.setDateStart(d);
        pro.setDateEnd(d);
        return promotionRepository.save(pro);
    }

    @GetMapping("/meetingeventroomtype/{hotelId}")
    public Collection<HotelMeetingEventRoomTypeManyToManyEntity> getRoomTypeByHotelId(@PathVariable Long hotelId) {
        return hotelMeetingEventRoomTypeManyToManyRepository.findAllByHotelId(hotelId);
    }

    
}