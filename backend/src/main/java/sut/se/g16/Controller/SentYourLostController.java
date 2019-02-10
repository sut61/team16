package sut.se.g16.Controller;

import sut.se.g16.Repository.*;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import sut.se.g16.Entity.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SentYourLostController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SentYourLostRepository sentYourLostRepository;
    @Autowired
    private SentYourLostManytoManyItemRepository sentYourLostManytoManyItemRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public SentYourLostController(ItemRepository itemRepository, RoomRepository roomRepository,
            RoomTypeRepository roomTypeRepository, SentYourLostRepository sentYourLostRepository,
            SentYourLostManytoManyItemRepository sentYourLostManytoManyItemRepository, HotelRepository hotelRepository,
            CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.hotelRepository = hotelRepository;
        this.itemRepository = itemRepository;
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.sentYourLostManytoManyItemRepository = sentYourLostManytoManyItemRepository;
        this.sentYourLostRepository = sentYourLostRepository;
    }

    @GetMapping("/items")
    public Collection<ItemEntity> ItemEntity() {
        return itemRepository.findAll().stream().collect(Collectors.toList());
    }

    


    @GetMapping("/sent-your-lost/{customer}/{item}/{hotels}/{rooms}/{roomtypes}/{address}")
    public Boolean SentYourLostEntity2(@PathVariable String customer, @PathVariable String item,
            @PathVariable String hotels, @PathVariable String rooms, @PathVariable String roomtypes,
            @PathVariable String address) {
        SentYourLostEntity sent = new SentYourLostEntity();
        CustomerEntity customer2 = customerRepository.findCustomerByName(customer);
        System.out.println(customer2);
        ItemEntity item2 = itemRepository.findByName(item);
        System.out.println(item2);
        RoomEntity room2 = roomRepository.findRoomEntityByRoomNumber(rooms);
        System.out.println(room2);
        HotelEntity hotel2 = hotelRepository.findByhotelNameEng(hotels);
        System.out.println(hotel2);
        RoomTypeEntity roomType2 = roomTypeRepository.findByName(roomtypes);
        sent.setAddress(address);
        System.out.println(address);
        sent.setNewCustomerEntity(customer2);
        System.out.println(customer2);
        sent.setNewHotelEntity(hotel2);
        System.out.println(hotel2);
        sent.setNewRoomEntity(room2);
        System.out.println(room2);
        sent.setNewRoomTypeEntity(roomType2);
        System.out.println(roomType2);
        this.sentYourLostRepository.save(sent);

        SentyourLostManytoManyItemEntity sentItem=new SentyourLostManytoManyItemEntity();
        sentItem.setNewItemEntity(item2);
        sentItem.setNewSentYourLostEntity(sent);
        this.sentYourLostManytoManyItemRepository.save(sentItem);
        return true;

    }
    @GetMapping(path = "/sentitems")
    public Collection<SentyourLostManytoManyItemEntity> sentyourLostManytoManyItemEntity() {
        return sentYourLostManytoManyItemRepository.findAll().stream().collect(Collectors.toList());
    }

}