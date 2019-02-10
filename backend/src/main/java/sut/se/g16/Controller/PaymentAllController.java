package sut.se.g16.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import java.util.*;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentAllController {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private PaymentAllRepository paymentAllRepository;
    @Autowired
    private ReservationRoomRepository reservationRoomRepository;
    @Autowired
    private ReservationMeetingEventRoomRepository reservationMeetingEventRoomRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingCarRepository bookingCarRepository;
    @Autowired
    private RoomStatusRepository roomStatusRepository;
    @Autowired
    private RoomRepository roomRepository;






    public PaymentAllController(BankRepository bankRepository, PaymentAllRepository paymentAllRepository, CustomerRepository customerRepository, BookingCarRepository bookingCarRepository,
                                ReservationRoomRepository reservationRoomRepository,  RoomStatusRepository roomStatusRepository,
                                RoomRepository roomRepository, ReservationMeetingEventRoomRepository reservationMeetingEventRoomRepository) {
        this.bankRepository = bankRepository;
        this.paymentAllRepository = paymentAllRepository;
        this.reservationRoomRepository = reservationRoomRepository;
        this.reservationMeetingEventRoomRepository = reservationMeetingEventRoomRepository;
        this.customerRepository = customerRepository;
        this.bookingCarRepository = bookingCarRepository;
        this.roomStatusRepository = roomStatusRepository;
        this.roomRepository = roomRepository;


    }
    @GetMapping(path = "/bankEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BankEntity> bankEntity() {
        return bankRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/bankEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public BankEntity bankEntityFind(@PathVariable("id") Long id) {
        return bankRepository.findByBankId(id);

    }

    @GetMapping(path = "/paymentAllEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PaymentAllEntity> paymentAllEntity() {
        return paymentAllRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/paymentAllEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public PaymentAllEntity paymentAllEntityFind(@PathVariable("id") Long id) {
        return paymentAllRepository.findByPaymentAllId(id);

    }

    @GetMapping(path = "/reservationRoomEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ReservationRoomEntity> reservationRoomEntity() {
        return reservationRoomRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/reservationRoomEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ReservationRoomEntity reservationRoomEntityFind(@PathVariable("id") Long id) {
        return reservationRoomRepository.findByReservationRoomId(id);

    }

    @GetMapping(path = "/reservationMeetingEventRoomEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ReservationMeetingEventRoomEntity> reservationMeetingEventRoomEntity() {
        return reservationMeetingEventRoomRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/reservationMeetingEventRoomEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ReservationMeetingEventRoomEntity reservationMeetingEventRoomEntityFind(@PathVariable("id") Long id) {
        return reservationMeetingEventRoomRepository.findByReservationMeetingEventRoomId(id);

    }
    @GetMapping(path = "/bookingCarEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BookingCarEntity> bookingCarEntity() {
        return bookingCarRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/payment/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PaymentAllEntity> getPaymentByCustomer(@PathVariable String customer) {
        return paymentAllRepository.findAllByCustomerName(customer);
    }

    @GetMapping("/bookingCarEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public BookingCarEntity bookingCarEntityFind(@PathVariable("id") Long id) {
        return bookingCarRepository.findByBookingcarId(id);

    }

    @GetMapping("/payment/{bankSelect}/{roomSelect}/{DateSelect}/{cardidSelect}/{cus}/{bookingCarSelect}/{nameSelect}/{cvvSelect}/{roomMeetSelect}")
    public PaymentAllEntity Payment(@PathVariable Date DateSelect, @PathVariable String bankSelect, @PathVariable Long roomSelect,
                                    @PathVariable String cardidSelect, @PathVariable String cus, @PathVariable Long bookingCarSelect,
                                    @PathVariable String nameSelect, @PathVariable String cvvSelect, @PathVariable Long roomMeetSelect) {

        PaymentAllEntity paymentAllEntity = new PaymentAllEntity();
        BankEntity b =  bankRepository.findByBankName(bankSelect);
        ReservationRoomEntity rr = reservationRoomRepository.findByReservationRoomId(roomSelect);
        ReservationMeetingEventRoomEntity re = reservationMeetingEventRoomRepository.findByReservationMeetingEventRoomId(roomMeetSelect);
        CustomerEntity customer = customerRepository.findCustomerByName(cus);
        BookingCarEntity bb = bookingCarRepository.findByBookingcarId(bookingCarSelect);

        RoomEntity room = rr.getNewRoomEntity();
        RoomStatusEntity status = roomStatusRepository.findByName("ว่าง");
        room.setNewRoomStatusEntity(status);
        roomRepository.save(room);

//        MeetingEventRoomEntity room1 = re.getNewMeetingEventRoomEntity();
//        RoomStatusEntity statuss = roomStatusRepository.findByName("ว่าง");
//        room1.setNewRoomStatusEntity(statuss);
//        meetingEventRoomRepository.save(room1);

        paymentAllEntity.setBankEntity(b);
        paymentAllEntity.setReservationRoomEntity(rr);
        paymentAllEntity.setReservationMeetingEventRoomEntity(re);
        paymentAllEntity.setCardId(cardidSelect);
        paymentAllEntity.setDate(DateSelect);
        paymentAllEntity.setName(nameSelect);
        paymentAllEntity.setCvv(cvvSelect);
        paymentAllEntity.setCustomerEntity(customer);
        paymentAllEntity.setBookingCarEntity(bb);


        return paymentAllRepository.save(paymentAllEntity);

    }


}
