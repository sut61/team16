package sut.se.g16.Controller;

import sut.se.g16.Entity.*;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Repository.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomTypeFurnitureController {
    private RoomTypeRepository roomTypeRepository;
    private FurnitureRepository furnitureRepository;
    private RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository;
    public RoomTypeFurnitureController(RoomTypeRepository roomTypeRepository, RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository,FurnitureRepository furnitureRepository){
        this.furnitureRepository = furnitureRepository;
        this.roomTypeFurnitureManyToManyRepository = roomTypeFurnitureManyToManyRepository;
        this.roomTypeRepository = roomTypeRepository;
    }
    @GetMapping("/roomtypefurniture/{roomtype}/{furniture}")
    public RoomTypeFurnitureManyToManyEntity newRoomType(@PathVariable final String  roomtype, @PathVariable final String furniture){
        FurnitureEntity fr = furnitureRepository.findByName(furniture);
        RoomTypeEntity rt = roomTypeRepository.findByName(roomtype);
        RoomTypeFurnitureManyToManyEntity rtf = new RoomTypeFurnitureManyToManyEntity();
        rtf.setNewFurnitureRoomTypeManyEntity(fr);
        rtf.setNewRoomTypeFurnitureManyEntity(rt);
        return roomTypeFurnitureManyToManyRepository.save(rtf);
    }
    @GetMapping("/roomtypefurnitures")
    public Collection<RoomTypeFurnitureManyToManyEntity> roomtype_furniture() {
        return roomTypeFurnitureManyToManyRepository.findAll().stream().collect(Collectors.toList());
    }

}
