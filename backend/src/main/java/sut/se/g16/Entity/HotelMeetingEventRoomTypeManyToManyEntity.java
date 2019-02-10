package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import sut.se.g16.Entity.*;

@Entity
@Data @Getter @Setter
@ToString @NoArgsConstructor
public class HotelMeetingEventRoomTypeManyToManyEntity {
    @Id
    @SequenceGenerator(name = "hotelMeetingEventRoom_seq", sequenceName = "hotelMeetingEventRoom_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotelMeetingEventRoom_seq")
    @Column(name = "hotelMeetingEventRoomId", unique = true, nullable = false)
    private  Long hotelMeetingEventRoomId;



    // Many To One With ItemEntity
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingEventRoomTypeId")
    private MeetingEventRoomTypeEntity newMeetingEventRoomTypeEntity;
    // Many To One With HotelEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    @JoinColumn(name = "hotelId")
    private HotelEntity newHotelEntity;

    /**
     * @return the hotelMeetingEventRoomId
     */
    public Long getHotelMeetingEventRoomId() {
        return hotelMeetingEventRoomId;
    }

    /**
     * @param hotelMeetingEventRoomId the hotelMeetingEventRoomId to set
     */
    public void setHotelMeetingEventRoomId(Long hotelMeetingEventRoomId) {
        this.hotelMeetingEventRoomId = hotelMeetingEventRoomId;
    }

    /**
     * @return the newMeetingEventRoomTypeEntity
     */
    public MeetingEventRoomTypeEntity getNewMeetingEventRoomTypeEntity() {
        return newMeetingEventRoomTypeEntity;
    }

    /**
     * @param newMeetingEventRoomTypeEntity the newMeetingEventRoomTypeEntity to set
     */
    public void setNewMeetingEventRoomTypeEntity(MeetingEventRoomTypeEntity newMeetingEventRoomTypeEntity) {
        this.newMeetingEventRoomTypeEntity = newMeetingEventRoomTypeEntity;
    }

    /**
     * @return the newHotelEntity
     */
    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }

    /**
     * @param newHotelEntity the newHotelEntity to set
     */
    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }

    


}