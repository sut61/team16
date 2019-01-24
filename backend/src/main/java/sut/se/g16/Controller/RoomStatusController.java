package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import sut.se.g16.Repository.RoomStatusRepository;
import sut.se.g16.Entity.RoomStatusEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomStatusController{
    private RoomStatusRepository roomStatusRepository;
    
    public RoomStatusController (RoomStatusRepository roomStatusRepository){
        this.roomStatusRepository = roomStatusRepository;
    }

    @GetMapping("/roomstatusesforroom")
    public Collection<RoomStatusEntity> roomStatusForRoom() {
        return roomStatusRepository.findAllForRoom(1L,2L,3L);
    }

    @GetMapping("/roomstatusesformeetingroom")
    public Collection<RoomStatusEntity> roomStatusForMeetingRoom() {
        return roomStatusRepository.findAllForRoom(1L,2L,4L);
    }
    
    @GetMapping("/roomstatuses")
    public Collection<RoomStatusEntity> roomStatus() {
        return roomStatusRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/roomstatus/{name}")
    public RoomStatusEntity newRoomStatus(@PathVariable String name) {
        RoomStatusEntity rst = new RoomStatusEntity();
        rst.setRoomStatusName(name);
        return this.roomStatusRepository.save(rst);
    }
}