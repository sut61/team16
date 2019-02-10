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
public class PromotionController {

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    public PromotionController(PromotionTypeRepository promotionTypeRepository,
            HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository,
            PromotionRepository promotionRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.promotionRepository = promotionRepository;
        this.promotionTypeRepository = promotionTypeRepository;
        this.hotelRepository = hotelRepository;
    }

    @GetMapping(path = "/hotelEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<HotelEntity> hotelEntity() {
        return hotelRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/hotelEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public HotelEntity hotelEntityFind(@PathVariable("id") Long id) {
        return hotelRepository.findByHotelId(id);

    }

    @GetMapping(path = "/promotionTypeEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionTypeEntity> promotionTypeEntity() {
        return promotionTypeRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/promotionTypeEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public PromotionTypeEntity promotionTypeEntityFind(@PathVariable("id") Long id) {
        return promotionTypeRepository.findByPromotionTypeId(id);

    }


    @GetMapping(path = "/roomTypeEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RoomTypeEntity> roomTypeEntity() {
        return roomTypeRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/roomTypeEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public RoomTypeEntity roomTypeEntityFind(@PathVariable("id") Long id) {
        return roomTypeRepository.findByRoomTypeId(id);

    }

    @GetMapping(path = "/promotionEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionEntity> promotionEntity() {
        return promotionRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/promotionEntity-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public PromotionEntity promotionEntityFind(@PathVariable("id") Long id) {
        return promotionRepository.findByPromotionId(id);

    }

    @GetMapping("/promotionEntity/{dateStartInput}/{dateEndInput}/{detail}/{hotelselecter}/{roomTypeselecter}/{promotiondelecter}/{promotionname}")
    public PromotionEntity promotionEntity(@PathVariable Date dateStartInput, @PathVariable Date dateEndInput,
            @PathVariable String detail, @PathVariable String hotelselecter, @PathVariable String roomTypeselecter,
            @PathVariable String promotiondelecter, @PathVariable String promotionname) {

        PromotionEntity promotionEntity = new PromotionEntity();
        HotelEntity h = hotelRepository.findByName(hotelselecter);
        PromotionTypeEntity p = promotionTypeRepository.findByPromotionTypeName(promotiondelecter);
        RoomTypeEntity r = roomTypeRepository.findByRoomTypeName(roomTypeselecter);

        promotionEntity.setNewHotelEntity(h);
        promotionEntity.setNewPromotionTypeEntity(p);
        promotionEntity.setNewRoomTypeEntity(r);
        promotionEntity.setPromotionName(promotionname);
        promotionEntity.setDateStart(dateStartInput);
        promotionEntity.setDateEnd(dateEndInput);
        promotionEntity.setDetail(detail);

        return promotionRepository.save(promotionEntity);

    }

    @GetMapping("/promotion/{hotelId}")
    public Collection<PromotionEntity> getPromotionByHotelId(@PathVariable Long hotelId) {
        return promotionRepository.getPromotionByHotelId(hotelId);

    }


}
