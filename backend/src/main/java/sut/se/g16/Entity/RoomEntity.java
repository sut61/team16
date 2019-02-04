package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Room")
public class RoomEntity {
    @Id
    @SequenceGenerator(name="room_seq",sequenceName="room_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_seq")
    @Column(name="roomId",unique = true, nullable = false)
    private @NotNull Long roomId;

    @Size(min = 4, max = 6)
    @Pattern (regexp = "^[A-Za-z]\\d+")
    private @NotNull String roomNumber;

    private @NotNull int roomPrice;

    //Many To One with HotelEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    private HotelEntity newHotelEntity;

    //Many To One with RoomTypeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    private RoomTypeEntity newRoomTypeEntity;

    //Many To One with RoomStatusEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomStatusEntity.class)
    private RoomStatusEntity newRoomStatusEntity;

    public HotelEntity getHotelEntity() {
        return newHotelEntity;
    }

    public void setHotelEntity(HotelEntity hotel) {
        this.newHotelEntity = hotel;
    }

    public RoomTypeEntity getRoomTypeEntity() {
        return newRoomTypeEntity;
    }

    public void setRoomTypeEntity(RoomTypeEntity roomTypeEntity) {
        this.newRoomTypeEntity = roomTypeEntity;
    }

    public RoomStatusEntity getRoomStatusEntity() {
        return newRoomStatusEntity;
    }

    public void setRoomStatusEntity(RoomStatusEntity roomStatusEntity) {
        this.newRoomStatusEntity = roomStatusEntity;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
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

    public RoomStatusEntity getNewRoomStatusEntity() {
        return newRoomStatusEntity;
    }

    public void setNewRoomStatusEntity(RoomStatusEntity newRoomStatusEntity) {
        this.newRoomStatusEntity = newRoomStatusEntity;
    }
}
