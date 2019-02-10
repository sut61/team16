package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Table (name = "PaymentAllEntity")
public class PaymentAllEntity {
    @Id
    @SequenceGenerator(name="paymentAllId_seq",sequenceName="paymentAllId_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="paymentAllId_seq")
    private @NotNull Long paymentAllId;
    @Pattern(regexp = "[0-9]{16}")
    @Size(min = 16,max = 16)
    @Column(unique = true)
    private @NotNull String cardId;
    private @NotNull Date date;
    private @NotNull String cvv;
    private @NotNull String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BankEntity.class)
    @JoinColumn(name = "Bank_ID", insertable = true)
    private  BankEntity bankEntity;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ReservationRoomEntity.class)
    @JoinColumn(name = "ReservationRoom_ID", insertable = true)
    private ReservationRoomEntity reservationRoomEntity;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ReservationMeetingEventRoomEntity.class)
    @JoinColumn(name = "ReservationMeetingEventRoom_ID", insertable = true)
    private ReservationMeetingEventRoomEntity reservationMeetingEventRoomEntity;


//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomStatusEntity.class)
//    @JoinColumn(name = "roomStatus_ID", insertable = true)
//    private RoomStatusEntity newroomStatusEntity;






    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_ID", insertable = true)
    private CustomerEntity customerEntity;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BookingCarEntity.class)
    @JoinColumn(name = "BookingCar_ID", insertable = true)
    private BookingCarEntity bookingCarEntity;



    public ReservationMeetingEventRoomEntity getReservationMeetingEventRoomEntity() {
        return reservationMeetingEventRoomEntity;
    }

    public void setReservationMeetingEventRoomEntity(ReservationMeetingEventRoomEntity reservationMeetingEventRoomEntity) {
        this.reservationMeetingEventRoomEntity = reservationMeetingEventRoomEntity;
    }


    public Long getPaymentAllId() {
        return paymentAllId;
    }

    public void setPaymentAllId(Long paymentAllId) {
        this.paymentAllId = paymentAllId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public ReservationRoomEntity getReservationRoomEntity() {
        return reservationRoomEntity;
    }

    public void setReservationRoomEntity(ReservationRoomEntity reservationRoomEntity) {
        this.reservationRoomEntity = reservationRoomEntity;
    }
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public BookingCarEntity getBookingCarEntity() {
        return bookingCarEntity;
    }

    public void setBookingCarEntity(BookingCarEntity bookingCarEntity) {
        this.bookingCarEntity = bookingCarEntity;
    }
//    public RoomStatusEntity getNewroomStatusEntity() {
//        return newroomStatusEntity;
//    }
//
//    public void setNewroomStatusEntity(RoomStatusEntity newroomStatusEntity) {
//        this.newroomStatusEntity = newroomStatusEntity;
//    }
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
