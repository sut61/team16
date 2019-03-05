package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;
//import sut.se.g16.Entity.*;

@Entity
@Data @Getter @Setter
@ToString @NoArgsConstructor
@EqualsAndHashCode
public class ReservationRoomEntity {
    @Id
    @SequenceGenerator(name="resevationRoomId_seq",sequenceName="resevationRoomId_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="resevationRoomId_seq")  
    private @NotNull Long reservationRoomId;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "hotelId")
    private HotelEntity newHotelEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "customerId")
    private CustomerEntity newCustomerEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "promotionId")
    private PromotionEntity newPromotionEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "roomTypeId")
    private RoomTypeEntity newRoomTypeEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "statusPaymentId")
    private StatusPaymentEntity newStatusPaymentEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "roomId")
    private RoomEntity newRoomEntity;

    @NotNull
    private @NonNull Date dateIn;
    
    @NotNull
    private @NonNull Date dateOut;


    @NotNull
    @Size(min = 10, max = 40)
    @Pattern(regexp = "^[A-Za-z]+\\s?[A-Za-z]*[0-9]*[A-Za-z]*")
    @Column(unique = true)
    private @NonNull String commentReserroom;

    /**
     * @return the reservationRoomId
     */
    public Long getReservationRoomId() {
        return reservationRoomId;
    }

    /**
     * @param reservationRoomId the reservationRoomId to set
     */
    public void setReservationRoomId(Long reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
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
     * @return the newPromotionEntity
     */
    public PromotionEntity getNewPromotionEntity() {
        return newPromotionEntity;
    }

    /**
     * @param newPromotionEntity the newPromotionEntity to set
     */
    public void setNewPromotionEntity(PromotionEntity newPromotionEntity) {
        this.newPromotionEntity = newPromotionEntity;
    }

    /**
     * @return the newRoomTypeEntity
     */
    public RoomTypeEntity getNewRoomTypeEntity() {
        return newRoomTypeEntity;
    }

    /**
     * @param newRoomTypeEntity the newRoomTypeEntity to set
     */
    public void setNewRoomTypeEntity(RoomTypeEntity newRoomTypeEntity) {
        this.newRoomTypeEntity = newRoomTypeEntity;
    }

    /**
     * @return the newStatusPaymentEntity
     */
    public StatusPaymentEntity getNewStatusPaymentEntity() {
        return newStatusPaymentEntity;
    }

    /**
     * @param newStatusPaymentEntity the newStatusPaymentEntity to set
     */
    public void setNewStatusPaymentEntity(StatusPaymentEntity newStatusPaymentEntity) {
        this.newStatusPaymentEntity = newStatusPaymentEntity;
    }

    /**
     * @return the newRoomEntity
     */
    public RoomEntity getNewRoomEntity() {
        return newRoomEntity;
    }

    /**
     * @param newRoomEntity the newRoomEntity to set
     */
    public void setNewRoomEntity(RoomEntity newRoomEntity) {
        this.newRoomEntity = newRoomEntity;
    }

    /**
     * @return the dateIn
     */
    public Date getDateIn() {
        return dateIn;
    }

    /**
     * @param dateIn the dateIn to set
     */
    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    /**
     * @return the dateOut
     */
    public Date getDateOut() {
        return dateOut;
    }

    /**
     * @param dateOut the dateOut to set
     */
    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }


    /**
     * @return the commentReserroom
     */
    public String getCommentReserroom() {
        return commentReserroom;
    }

    /**
     * @param commentReserroom the commentReserroom to set
     */
    public void setCommentReserroom(String commentReserroom) {
        this.commentReserroom = commentReserroom;
    }

    

    
}