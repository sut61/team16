package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.HotelRepository;
import sut.se.g16.Repository.MeetingEventRoomRepository;
import sut.se.g16.Repository.MeetingEventRoomTypeRepository;
import sut.se.g16.Repository.MeetingEventRoomTypeTimeManyToManyRepository;
import sut.se.g16.Repository.MemberHotelRepository;
import sut.se.g16.Repository.RoomStatusRepository;
import sut.se.g16.Repository.TypeTimeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MeetingEventRoomController {
    int count = 0;
    int status = 0;
    private MeetingEventRoomRepository meetingEventRoomRepository;
    private MeetingEventRoomTypeRepository meetingEventRoomTypeRepository;
    private HotelRepository hotelRepository;
    private MemberHotelRepository memberHotelRepository;
    private RoomStatusRepository roomStatusRepository;
    private TypeTimeRepository typeTimeRepository;
    private MeetingEventRoomTypeTimeManyToManyRepository meetingEventRoomTypeTimeManyToManyRepository;
    public MeetingEventRoomController(MeetingEventRoomRepository meetingEventRoomRepository,
            MeetingEventRoomTypeRepository meetingEventRoomTypeRepository, MemberHotelRepository memberHotelRepository,
            HotelRepository hotelRepository, RoomStatusRepository roomStatusRepository,
            TypeTimeRepository typeTimeRepository,
            MeetingEventRoomTypeTimeManyToManyRepository meetingEventRoomTypeTimeManyToManyRepository) {
        this.meetingEventRoomRepository = meetingEventRoomRepository;
        this.meetingEventRoomTypeRepository = meetingEventRoomTypeRepository;
        this.roomStatusRepository = roomStatusRepository;
        this.typeTimeRepository = typeTimeRepository;
        this.hotelRepository = hotelRepository;
        this.memberHotelRepository = memberHotelRepository;
        this.meetingEventRoomTypeTimeManyToManyRepository = meetingEventRoomTypeTimeManyToManyRepository;
    }

    @GetMapping("/meetingeventrooms")
    public Collection<MeetingEventRoomEntity> meeting() {
        return meetingEventRoomRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/meetingeventroom/{name}/{meetingRoomType}/{morning}/{afternoon}/{evening}/{number}/{price}/{memberUserName}")
    public Boolean addMeetingEventRoom(@PathVariable String name, @PathVariable String meetingRoomType,
            @PathVariable String morning, @PathVariable String afternoon, @PathVariable String evening,
            @PathVariable String number, @PathVariable Long price, @PathVariable String memberUserName) {
        if (count != 0) {
            Long mem = memberHotelRepository.findId(memberUserName);
            System.out.print(mem);
            Long hotelIdFromName = hotelRepository.findHotelIdByMemId(mem);
            System.out.print(hotelIdFromName);
            Long hotelIdFromeId = meetingEventRoomRepository.findHotelIdFromHotelIdAndMeetingEventRoomNumber(hotelIdFromName, number);
            if (hotelIdFromeId != null)
                status = 1;
            else
                status = 0;

            System.out.print("Do");
        }
        if (status == 0) {
            MeetingEventRoomEntity mer = new MeetingEventRoomEntity();
            MeetingEventRoomTypeEntity mert = meetingEventRoomTypeRepository
                    .findMeetingEventRoomTypeByName(meetingRoomType);
            System.out.println(mert);
            System.out.println("Hello");
            Long mem = memberHotelRepository.findId(memberUserName);
            System.out.println("Hello");
            System.out.println(mem);
            RoomStatusEntity rst1 = roomStatusRepository.findByName(morning);
            RoomStatusEntity rst2 = roomStatusRepository.findByName(afternoon);
            RoomStatusEntity rst3 = roomStatusRepository.findByName(evening);
            HotelEntity hotels = hotelRepository.findHotelByMemberId(mem);

            System.out.println(hotels);
            mer.setMeetingEventRoomNumber(number);
            mer.setMeetingEventRoomName(name);
            mer.setMeetingEventRoomPrice(price);
            mer.setMeetingEventRoomTypeEntity(mert);
            mer.setHotel(hotels);

            TypeTimeEntity typeTime1 = typeTimeRepository.findTypeTimeByName("Morning");
            TypeTimeEntity typeTime2 = typeTimeRepository.findTypeTimeByName("Afternoon");
            TypeTimeEntity typeTime3 = typeTimeRepository.findTypeTimeByName("Evening");

            MeetingEventRoomEntity meetingEventRoom = meetingEventRoomRepository.save(mer);
            // Morning ว่าง, พัก, จอง
            MeetingEventRoomTypeTimeManyToManyEntity mertmtm1 = new MeetingEventRoomTypeTimeManyToManyEntity();
            mertmtm1.setNewMeetingEventRoomEntity(meetingEventRoom);
            mertmtm1.setNewTypeTimeEntity(typeTime1);
            mertmtm1.setNewRoomStatusEntity(rst1);
            meetingEventRoomTypeTimeManyToManyRepository.save(mertmtm1);
            // Afternoon ว่าง, พัก, จอง
            MeetingEventRoomTypeTimeManyToManyEntity mertmtm2 = new MeetingEventRoomTypeTimeManyToManyEntity();
            mertmtm2.setNewMeetingEventRoomEntity(meetingEventRoom);
            mertmtm2.setNewTypeTimeEntity(typeTime2);
            mertmtm2.setNewRoomStatusEntity(rst2);
            meetingEventRoomTypeTimeManyToManyRepository.save(mertmtm2);
            // Evening ว่าง, พัก, จอง
            MeetingEventRoomTypeTimeManyToManyEntity mertmtm3 = new MeetingEventRoomTypeTimeManyToManyEntity();
            mertmtm3.setNewMeetingEventRoomEntity(meetingEventRoom);
            mertmtm3.setNewTypeTimeEntity(typeTime3);
            mertmtm3.setNewRoomStatusEntity(rst3);
            meetingEventRoomTypeTimeManyToManyRepository.save(mertmtm3);
            count++;
            return true;
        }
        return false;
    }
    @GetMapping("/updatemeetingeventroomstatus/{id}/{name}/{meetingRoomType}/{morning}/{afternoon}/{evening}/{number}/{price}/{memberUserName}/{id1}/{id2}/{id3}")
    public Boolean updateMeetingRoom(@PathVariable Long id, @PathVariable String name, @PathVariable String meetingRoomType,
    @PathVariable String morning, @PathVariable String afternoon, @PathVariable String evening,
    @PathVariable String number, @PathVariable Long price, @PathVariable String memberUserName,
    @PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3) {

        System.out.println("Update");
        int checkDoubly = 0;
        MeetingEventRoomEntity mter = new MeetingEventRoomEntity();
        Long hotelId = hotelRepository.findHotelIdByMemberUserName(memberUserName);
        String meetingEventRoomNumber = meetingEventRoomRepository.findRoomNumberById(id);
        System.out.println(meetingEventRoomNumber);
        Long hotelIdByMeetingRoomNumber = meetingEventRoomRepository.findHotelIdFromHotelIdAndRoomNumber(hotelId,number); 
        System.out.println(hotelIdByMeetingRoomNumber);
        MeetingEventRoomTypeEntity  mert = meetingEventRoomTypeRepository.findMeetingEventRoomTypeByName(meetingRoomType);
        System.out.println(mert);

        //Find rst
        RoomStatusEntity rst1 = roomStatusRepository.findByName(morning);
        RoomStatusEntity rst2 = roomStatusRepository.findByName(afternoon);
        RoomStatusEntity rst3 = roomStatusRepository.findByName(evening);

        //Create mtm
        MeetingEventRoomTypeTimeManyToManyEntity mtm = new MeetingEventRoomTypeTimeManyToManyEntity();

        if (meetingEventRoomNumber.equals(number)) {
            checkDoubly = 0;
        } else {
            if (hotelIdByMeetingRoomNumber != null)
                return false;
        }
        if (checkDoubly == 0) {
            meetingEventRoomRepository.findById(id).map(roomedit -> {
                roomedit.setMeetingEventRoomNumber(number);
                roomedit.setMeetingEventRoomPrice(price);
                roomedit.setMeetingEventRoomTypeEntity(mert);
                roomedit.setMeetingEventRoomName(name);
                meetingEventRoomRepository.save(roomedit);
                return true;
            }).orElseGet(() -> {
                meetingEventRoomRepository.save(mter);
                return true;
            });

            meetingEventRoomTypeTimeManyToManyRepository.findById(id1).map(mtm1 -> {
                mtm1.setNewRoomStatusEntity(rst1);
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm1);
                return true;
            }).orElseGet(() -> {
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm);
                return true;
            });

            meetingEventRoomTypeTimeManyToManyRepository.findById(id2).map(mtm2 -> {
                mtm2.setNewRoomStatusEntity(rst2);
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm2);
                return true;
            }).orElseGet(() -> {
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm);
                return true;
            });

            meetingEventRoomTypeTimeManyToManyRepository.findById(id3).map(mtm3 -> {
                mtm3.setNewRoomStatusEntity(rst3);
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm3);
                return true;
            }).orElseGet(() -> {
                meetingEventRoomTypeTimeManyToManyRepository.save(mtm);
                return true;
            });
        }
        return true;
    }

    @GetMapping("/meetingeventroomtype/{name}")
    public MeetingEventRoomTypeEntity addMeetingRoomType(@PathVariable String name) {
        MeetingEventRoomTypeEntity mert = new MeetingEventRoomTypeEntity();
        mert.setMeetingEventRoomTypeName(name);
        return meetingEventRoomTypeRepository.save(mert);
    }

    @GetMapping("/meetingeventroomtypes")
    public Collection<MeetingEventRoomTypeEntity> meetingRoomType() {
        return meetingEventRoomTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/meetingeventroomstatus/{id}")
    public Collection<MeetingEventRoomTypeTimeManyToManyEntity> getmeetingStatus(@PathVariable Long id) {
        return meetingEventRoomTypeTimeManyToManyRepository.findRoomStatusNameByMeetingRoomId(id);
    }

    @GetMapping("/meetingeventmtmidandrstid/{id}")
    public Collection<MeetingEventRoomTypeTimeManyToManyEntity> getMtMIdmeetingStatus(@PathVariable Long id) {
        return meetingEventRoomTypeTimeManyToManyRepository.findAllByMeetingRoomId(id);
    }

    @GetMapping("/meetingeventroomtypetimes")
    public Collection<MeetingEventRoomTypeTimeManyToManyEntity> meetingtypetimemany() {
        return meetingEventRoomTypeTimeManyToManyRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping ("/typetime/{name}")
    public TypeTimeEntity addTypeTime(@PathVariable String name){
        TypeTimeEntity typeTime = new TypeTimeEntity();
        typeTime.setTypeTimeName(name);
        return typeTimeRepository.save(typeTime);
    }

    @GetMapping("/typetimes")
    public Collection<TypeTimeEntity> typetime() {
        return typeTimeRepository.findAll().stream().collect(Collectors.toList());
    }


}