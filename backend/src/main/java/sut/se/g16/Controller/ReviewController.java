package sut.se.g16.Controller;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    ReservationRoomRepository reservationRoomRepository;

    public ReviewController(ReservationRoomRepository reservationRoomRepository, HotelRepository hotelRepository,
            RoomTypeRepository roomTypeRepository, CustomerRepository customerRepository,
            ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
        this.reservationRoomRepository = reservationRoomRepository;
    }

    @GetMapping("/viewreview/{memberusername}")
    public Collection<ReservationRoomEntity> getReservationTable(@PathVariable String memberusername) {
        String statusCheckoutRoom = "จ่าย";
        return reservationRoomRepository.findAllByMemberUserNameAndStatusPayment(statusCheckoutRoom, memberusername);
    }

    @GetMapping("/Reviews")
    public Collection<ReviewEntity> Reviews() {
        return reviewRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/hoetls/{hotelNameEng}")
    public HotelEntity getProvince(@PathVariable String hotelNameEng) {
        return hotelRepository.findByName(hotelNameEng);
    }
    // @GetMapping("/Review/{comment}/{score}/{userName}/{roomType}/{hotelName}/{id}")
    // public ReviewEntity newReview(@PathVariable String userName, String comment,
    // int score, String roomType,
    // String hotelName, Long id) {
    // CustomerEntity user = customerRepository.findByUser(userName);
    // RoomTypeEntity room = roomTypeRepository.findByName(roomType);
    // HotelEntity hotel = hotelRepository.findByName(hotelName);
    // PaymentEntity bill = paymentRepository.findBillById(id);
    // ReviewEntity r = new ReviewEntity(comment, score, user, room, hotel, bill);
    // return this.reviewRepository.save(r);
    // }

    @GetMapping("/review/createreview/{userName}/{billId}/{hotel}/{roomtype}/{comment}/{problem}/{service1}/{service2}/{service3}/{room1}/{room2}/{room3}/{secu1}/{secu2}/{secu3}")
    public ReviewEntity newReview2(@PathVariable String hotel, @PathVariable String roomtype,@PathVariable Long billId,
            @PathVariable String comment, @PathVariable String problem, @PathVariable int service1,
            @PathVariable int service2, @PathVariable int service3, @PathVariable int room1, @PathVariable int room2,
            @PathVariable int room3, @PathVariable int secu1, @PathVariable int secu2, @PathVariable int secu3,
            @PathVariable String userName) {
        float score = 0;
        int s[] = { service1, service2, service3, room1, room2, room3, secu1, secu2, secu3 };
        for (int i = 0; i < s.length; i++) {
            score += (float) s[i];
        }
        CustomerEntity cus = customerRepository.findByUser(userName);
        float sumscore = score / 9;
        RoomTypeEntity room = roomTypeRepository.findByName(roomtype);
        HotelEntity hotel2 = hotelRepository.findByhotelNameEng(hotel);
        ReservationRoomEntity bill = reservationRoomRepository.findRservationById(billId);
        ReviewEntity re = new ReviewEntity();
        re.setNewCustomerEntity(cus);
        re.setNewReservationRoomEntity(bill);
        re.setNewHotelEntity(hotel2);
        re.setScore(sumscore);
        re.setComment(comment);
        re.setNewRoomTypeEntity(room);
        return this.reviewRepository.save(re);
    }
}
