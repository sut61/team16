package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import sut.se.g16.Entity.*;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ReservationMeetingEventRoomEntity {
    @Id
    @SequenceGenerator(name = "reservationMeetingEventRoomId_seq", sequenceName = "reservationMeetingEventRoomId_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationMeetingEventRoomId_seq")
    private  Long reservationMeetingEventRoomId;

    
    private Date reservationMeetingEventRoomDateIn;
    private Date reservationMeetingEventRoomDateOut;

    @Size(min = 10, max = 40)
    //@Pattern(regexp = "^[A-Za-z]+\\s?[A-Za-z]*[0-9]*[A-Za-z]*")
    @Column(unique = true)
    private String detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotelId")
    private HotelEntity newHotelEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private CustomerEntity newCustomerEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomStatusId")
    private RoomStatusEntity newRoomStatusEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingEventRoomId")
    private MeetingEventRoomEntity newMeetingEventRoomEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingEventRoomTypeId")
    private MeetingEventRoomTypeEntity newMeetingEventRoomTypeEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeTimeId")
    private TypeTimeEntity newTypeTimeEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "functionId")
    private FunctionEntity newFunctionEntity;

    /**
     * @return the reservationMeetingEventRoomId
     */
    public Long getReservationMeetingEventRoomId() {
        return reservationMeetingEventRoomId;
    }

    /**
     * @param reservationMeetingEventRoomId the reservationMeetingEventRoomId to set
     */
    public void setReservationMeetingEventRoomId(Long reservationMeetingEventRoomId) {
        this.reservationMeetingEventRoomId = reservationMeetingEventRoomId;
    }

    /**
     * @return the reservationMeetingEventRoomDateIn
     */
    public Date getReservationMeetingEventRoomDateIn() {
        return reservationMeetingEventRoomDateIn;
    }

    /**
     * @param reservationMeetingEventRoomDateIn the
     *                                          reservationMeetingEventRoomDateIn to
     *                                          set
     */
    public void setReservationMeetingEventRoomDateIn(Date reservationMeetingEventRoomDateIn) {
        this.reservationMeetingEventRoomDateIn = reservationMeetingEventRoomDateIn;
    }

    /**
     * @return the reservationMeetingEventRoomDateOut
     */
    public Date getReservationMeetingEventRoomDateOut() {
        return reservationMeetingEventRoomDateOut;
    }

    /**
     * @param reservationMeetingEventRoomDateOut the
     *                                           reservationMeetingEventRoomDateOut
     *                                           to set
     */
    public void setReservationMeetingEventRoomDateOut(Date reservationMeetingEventRoomDateOut) {
        this.reservationMeetingEventRoomDateOut = reservationMeetingEventRoomDateOut;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
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

    /**
     * @return the newCustomerEntity
     */
    public CustomerEntity getNewCustomerEntity() {
        return newCustomerEntity;
    }

    /**
     * @param newCustomerEntity the newCustomerEntity to set
     */
    public void setNewCustomerEntity(CustomerEntity newCustomerEntity) {
        this.newCustomerEntity = newCustomerEntity;
    }

    /**
     * @return the newRoomStatusEntity
     */
    public RoomStatusEntity getNewRoomStatusEntity() {
        return newRoomStatusEntity;
    }

    /**
     * @param newRoomStatusEntity the newRoomStatusEntity to set
     */
    public void setNewRoomStatusEntity(RoomStatusEntity newRoomStatusEntity) {
        this.newRoomStatusEntity = newRoomStatusEntity;
    }

    /**
     * @return the newMeetingEventRoomEntity
     */
    public MeetingEventRoomEntity getNewMeetingEventRoomEntity() {
        return newMeetingEventRoomEntity;
    }

    /**
     * @param newMeetingEventRoomEntity the newMeetingEventRoomEntity to set
     */
    public void setNewMeetingEventRoomEntity(MeetingEventRoomEntity newMeetingEventRoomEntity) {
        this.newMeetingEventRoomEntity = newMeetingEventRoomEntity;
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
     * @return the newTypeTimeEntity
     */
    public TypeTimeEntity getNewTypeTimeEntity() {
        return newTypeTimeEntity;
    }

    /**
     * @param newTypeTimeEntity the newTypeTimeEntity to set
     */
    public void setNewTypeTimeEntity(TypeTimeEntity newTypeTimeEntity) {
        this.newTypeTimeEntity = newTypeTimeEntity;
    }

    /**
     * @return the newFunctionEntity
     */
    public FunctionEntity getNewFunctionEntity() {
        return newFunctionEntity;
    }

    /**
     * @param newFunctionEntity the newFunctionEntity to set
     */
    public void setNewFunctionEntity(FunctionEntity newFunctionEntity) {
        this.newFunctionEntity = newFunctionEntity;
    }

    
  

   

}