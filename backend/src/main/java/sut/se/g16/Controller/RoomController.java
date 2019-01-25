package sut.se.g16.Controller;

import sut.se.g16.Repository.*;
import sut.se.g16.Entity.*;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.classic.turbo.TurboFilter;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomController {
    int count = 0;
    int status = 0;
    private MemberHotelRepository memberHotelRepository;
    private HotelRepository hotelRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomStatusRepository roomStatusRepository;
    private RoomRepository roomsrepository;
    private HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository;

    public RoomController(HotelRoomTypeManyToManyRepository hotelRoomTypeManyToManyRepository, RoomRepository roomRepository, MemberHotelRepository memberHotelRepository,
            HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository,
            RoomStatusRepository roomStatusRepository) {
        this.roomsrepository = roomRepository;
        this.roomStatusRepository = roomStatusRepository;
        this.memberHotelRepository = memberHotelRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomTypeManyToManyRepository = hotelRoomTypeManyToManyRepository;
    }

    @GetMapping("/room/{roomType}/{roomstatus}/{number}/{price}/{memberUserName}")
    public Boolean update(@PathVariable String memberUserName, @PathVariable String roomType,
            @PathVariable String roomstatus, @PathVariable final int number, @PathVariable int price) {
        if (count != 0) {
            Long mem = memberHotelRepository.findId(memberUserName);
            Long hotelIdFromName = hotelRepository.findHotelIdByMemId(mem);
            Long hotelIdFromeId = roomsrepository.findHotelIdFromHotelIdAndRoomNumber(hotelIdFromName, number);
            if (hotelIdFromeId != null)
                status = 1;
            else
                status = 0;
        }
        if (status == 0) {
            Long mem = memberHotelRepository.findId(memberUserName);
            RoomStatusEntity rst = roomStatusRepository.findByName(roomstatus);
            HotelEntity hotels = hotelRepository.findHotelByMemberId(mem);
            RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
            RoomEntity no1 = new RoomEntity();
            no1.setRoomNumber(number);
            no1.setRoomPrice(price);
            no1.setNewHotelEntity(hotels);
            no1.setRoomTypeEntity(rt);
            no1.setRoomStatusEntity(rst);
            roomsrepository.save(no1);
            roomsrepository.findAll().stream().collect(Collectors.toList());
            count++;
            return true;
        }
        return false;
    }

    @GetMapping("/rooms")
    public Collection<RoomEntity> Rooms() {
        return roomsrepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/roomtype/bymemberusername/{memberUserName}")
    public Collection<HotelRoomTypeManyToManyEntity> getRoomTypeByMemberUserName(@PathVariable String memberUserName) {
        Long hotelId = hotelRepository.findHotelIdByMemberUserName(memberUserName);
        return  hotelRoomTypeManyToManyRepository.findAllByHotelId(hotelId);
    }

    @GetMapping("/rooms/{memberUserName}")
    public Collection<RoomEntity> getRoomByHotel(@PathVariable String memberUserName) {
        Long hotelId = hotelRepository.findHotelIdByMemberUserName(memberUserName);
        System.out.print(hotelId);
        return roomsrepository.findRoomByHotelId(hotelId);
    }

    @GetMapping("/updateroomstatus/{roomId}/{hotel}/{roomType}/{roomstatus}/{number}/{price}")
    public Boolean editRoom(@PathVariable Long roomId, @PathVariable String hotel, @PathVariable String roomType,
            @PathVariable String roomstatus, @PathVariable final int number, @PathVariable int price) {
        int checkDoubly = 0;
        RoomEntity room = new RoomEntity();
        HotelEntity ho = hotelRepository.findByName(hotel);
        RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
        RoomStatusEntity rst = roomStatusRepository.findByName(roomstatus);
        int roomNumber = roomsrepository.findRoomNumberById(roomId);
        Long hotelIdFromName = hotelRepository.findHotelIdByName(hotel);
        Long hotelIdFromeId = roomsrepository.findHotelIdFromHotelIdAndRoomNumber(hotelIdFromName, number);
        if (roomNumber == number) {
            checkDoubly = 0;
        } else {
            if (hotelIdFromeId != null)
                return false;
        }
        if (checkDoubly == 0) {
            roomsrepository.findById(roomId).map(roomedit -> {
                roomedit.setRoomNumber(number);
                roomedit.setRoomPrice(price);
                roomedit.setRoomStatusEntity(rst);
                roomedit.setNewHotelEntity(ho);
                roomedit.setRoomTypeEntity(rt);
                roomsrepository.save(roomedit);
                return true;
            }).orElseGet(() -> {
                roomsrepository.save(room);
                return true;
            });
        }
        return true;
    }

}