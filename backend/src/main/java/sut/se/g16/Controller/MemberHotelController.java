package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberHotelController{
    private HotelRepository hotelRepository;
    private MemberHotelRepository memberHotelRepository;
    public MemberHotelController (MemberHotelRepository memberHotelRepository, HotelRepository hotelRepository){
        this.memberHotelRepository = memberHotelRepository;
        this.hotelRepository = hotelRepository;
    }
    
    @GetMapping("/memberhotels")
    public Collection<MemberHotelEntity> memberhotel() {
        return memberHotelRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/memberhotel/{username}/{password}")
    public MemberHotelEntity newMember(@PathVariable String username,@PathVariable Long password){
        MemberHotelEntity member = new MemberHotelEntity();
        Long memId = memberHotelRepository.findMemberIdByName(username);
        member.setMemberHotelName(username);
        member.setMemberHotelPassword(password);
        return memberHotelRepository.save(member);
    }

    @GetMapping("/memberhotel/login/{username}/{password}")
    public Boolean isLogin(@PathVariable String username,@PathVariable Long password){
        Long m = memberHotelRepository.isLogin(username,password);
        if(m!=null)
            return true;
        return false;
    }
}