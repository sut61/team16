package sut.se.g16.Controller;

import sut.se.g16.Entity.RoomTypeEntity;
import sut.se.g16.Repository.RoomTypeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomTypeController {
    private RoomTypeRepository roomTypeRepository;

    public RoomTypeController (RoomTypeRepository roomTypeRepository){
        this.roomTypeRepository = roomTypeRepository;
    }
    @GetMapping("/roomtype/{rt}/{bedType}/{numberOfBed}/{maxPeople}")
    public RoomTypeEntity newRoomType(@PathVariable final String  rt,@PathVariable String bedType,@PathVariable int numberOfBed,@PathVariable int maxPeople){
        RoomTypeEntity roomType = new RoomTypeEntity();
        roomType.setRoomTypeName(rt);
        roomType.setBedType(bedType);
        roomType.setNumberOfBed(numberOfBed);
        roomType.setMaxPeople(maxPeople);
        return roomTypeRepository.save(roomType);
    }
    @GetMapping("/roomtypes")
    public Collection<RoomTypeEntity> RoomType() {
        return roomTypeRepository.findAll().stream()
                .collect(Collectors.toList());
    }
}
