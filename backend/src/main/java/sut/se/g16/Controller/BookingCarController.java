package sut.se.g16.Controller;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingCarController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CarBrandRepository carBrandRepository;
    @Autowired
    private BookingCarRepository bookingCarRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public BookingCarController(BookingCarRepository bookingCarRepository , CarRepository carRepository,
                                CarTypeRepository carTypeRepository, CarBrandRepository carBrandRepository,
                                HotelRepository hotelRepository, CustomerRepository customerRepository){
        this.bookingCarRepository = bookingCarRepository;
        this.carRepository = carRepository;
        this.carTypeRepository = carTypeRepository;
        this.carBrandRepository = carBrandRepository;
        this.hotelRepository = hotelRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping(path = "/carnotnull", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CarEntity> carNotNull() {
        return carRepository.findAllNotNull(null);
    }

    @GetMapping(path = "/carnull", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CarEntity> carNull() {
        return carRepository.findAllNull(null);
    }

    @GetMapping(path = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CarEntity> CarEntity() {
        return carRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/cartype", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CarTypeEntity> CarTypeEntity() {
        return carTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/carbrand", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CarBrandEntity> CarBrandEntity() {
        return carBrandRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/bookingcar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BookingCarEntity> BookingCarEntity() {
        return bookingCarRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/bookingcar/{hotelSelect}/{carNameSelect}/{inputStartDate}/{inputEndDate}/{driverIdInput}/{inputUserName}")
    public Boolean bookingCarEntity(@PathVariable String carNameSelect, @PathVariable Date inputStartDate,
                                         @PathVariable Date inputEndDate, @PathVariable String driverIdInput,
                                         @PathVariable String hotelSelect, @PathVariable String inputUserName){
        BookingCarEntity bookingCarEntity = new BookingCarEntity();
        CarEntity car = carRepository.findBycarName(carNameSelect);
        HotelEntity hotel = hotelRepository.findByName(hotelSelect);
        CustomerEntity customer = customerRepository.findBycustomerUsername(inputUserName);
        bookingCarEntity.setDateStart(inputStartDate);
        bookingCarEntity.setDateEnd(inputEndDate);
        bookingCarEntity.setDriverId(driverIdInput);
        bookingCarEntity.setHotel(hotel);
        bookingCarEntity.setCustomer(customer);

        car.setBookingCar(bookingCarRepository.save(bookingCarEntity));
        carRepository.save(car);

        return true;
    }
    @GetMapping(path = "/bookingcar/{bookingcarId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HotelEntity findHotel(@PathVariable Long bookingcarId) {
        return hotelRepository.findByHotelId(bookingCarRepository.findHotelIdByBookingCarId(bookingcarId));
    }

}
