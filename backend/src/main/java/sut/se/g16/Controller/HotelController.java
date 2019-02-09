package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HotelController{
    private MemberHotelRepository memberHotelRepository;
    private HotelRepository hotelRepository;
    private ProvinceRepository provinceRepository;
    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @GetMapping("/hotel/{name}/{villageNo}/{houseNo}/{building}/{village}/{alleyLane}/{road}/{subDistrictSubArea}/{districtArea}/{postCode}/{mobilePhone}/{phone}/{fax}/{province}/{memberName}")
    public HotelEntity newHotel(@PathVariable final String  name,@PathVariable String province,@PathVariable Long villageNo
    ,@PathVariable String houseNo, @PathVariable String building, @PathVariable String village, @PathVariable String alleyLane,
    @PathVariable String road, @PathVariable String subDistrictSubArea, @PathVariable String districtArea, @PathVariable String postCode,
    @PathVariable String mobilePhone, @PathVariable String phone, @PathVariable String fax,@PathVariable String memberName){
        ProvinceEntity p = provinceRepository.findByName(province);
        MemberHotelEntity member = memberHotelRepository.findByName(memberName);
        HotelEntity hotels = new HotelEntity();
        hotels.setHotelNameEng(name);
        hotels.setVillageNo(villageNo);
        hotels.setHouseNo(houseNo);
        hotels.setBuilding(building);
        hotels.setVillage(village);
        hotels.setAlleyLane(alleyLane);
        hotels.setRoad(road);
        hotels.setSubDistrictSubArea(subDistrictSubArea);
        hotels.setDistrictArea(districtArea);
        hotels.setPostCode(postCode);
        hotels.setMobilePhone(mobilePhone);
        hotels.setPhone(phone);
        hotels.setFax(fax);
        hotels.setProvinceEntity(p);
        hotels.setNewMemberHotelEntity(member);
        return hotelRepository.save(hotels);
    }
    @GetMapping("/hotels")
    public Collection<HotelEntity> hotel() {
        return hotelRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/gethotelnameeng/{memberusername}")
    public HotelEntity getHotelNameEng(@PathVariable String memberusername) {
        return hotelRepository.findHotelByMemberUserName(memberusername);
    }
}