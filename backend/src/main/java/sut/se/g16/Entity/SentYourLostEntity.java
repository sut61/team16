package sut.se.g16.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.*;
@Getter
@Setter
@Entity
@Table(name="sent")
@SpringBootApplication
public class SentYourLostEntity{
    @Id
    @SequenceGenerator(name = "Sents_seq", sequenceName = "Sents_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sents_seq")
    @Column(name = "SentsId", unique = true, nullable = false)
    @NotNull private Long sentId;

    @Size(min=10,max=60)
    @Pattern(regexp="^[A-Za-z]*")
    @NotNull private String address;

    @ManyToOne(fetch=FetchType.EAGER, targetEntity=HotelEntity.class)
    @JoinColumn(name="hotelId")
    private @NotNull HotelEntity newHotelEntity;

    @ManyToOne(fetch=FetchType.EAGER, targetEntity=RoomTypeEntity.class)
    @JoinColumn(name="roomTypeId")
    private @NotNull RoomTypeEntity newRoomTypeEntity;

    @ManyToOne(fetch=FetchType.EAGER, targetEntity=CustomerEntity.class)
    @JoinColumn(name="customerId")
    private @NotNull CustomerEntity newCustomerEntity;

    @ManyToOne(fetch=FetchType.EAGER,targetEntity=RoomEntity.class)
    @JoinColumn(name="roomId")
    private @NotNull RoomEntity newRoomEntity;



    public Long getSentId() {
        return sentId;
    }

    public void setSentId(Long sentId) {
        this.sentId = sentId;
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

    public CustomerEntity getNewCustomerEntity() {
        return newCustomerEntity;
    }

    public void setNewCustomerEntity(CustomerEntity newCustomerEntity) {
        this.newCustomerEntity = newCustomerEntity;
    }

    public RoomEntity getNewRoomEntity() {
        return newRoomEntity;
    }

    public void setNewRoomEntity(RoomEntity newRoomEntity) {
        this.newRoomEntity = newRoomEntity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}