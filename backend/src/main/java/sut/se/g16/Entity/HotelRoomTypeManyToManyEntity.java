package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDate;
//import sut.se.g16.Entity.*;

@Entity
@Data @Getter @Setter
@ToString @NoArgsConstructor
public class HotelRoomTypeManyToManyEntity {
    @Id
    @SequenceGenerator(name = "hotelRoomType_seq", sequenceName = "hotelRoomType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotelRoomType_seq")
    @Column(name = "hotelRoomTypeId", unique = true, nullable = false)
    private @NonNull Long hotelRoomTypeId;

    // Many To One With ItemEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    @JoinColumn(name = " roomtypeId")
    private RoomTypeEntity newRoomTypeEntity;

    // Many To One With HotelEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    @JoinColumn(name = " hotelId")
    private HotelEntity newHotelEntity;


    public Long getHotelRoomTypeId() {
        return hotelRoomTypeId;
    }

  
    public void setHotelRoomTypeId(Long hotelRoomTypeId) {
        this.hotelRoomTypeId = hotelRoomTypeId;
    }

  
    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }

    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }


    public RoomTypeEntity getNewRoomTypeEntity() {
        return newRoomTypeEntity;
    }

    public void setNewRoomTypeEntity(RoomTypeEntity newRoomTypeEntity) {
        this.newRoomTypeEntity = newRoomTypeEntity;
    }

}