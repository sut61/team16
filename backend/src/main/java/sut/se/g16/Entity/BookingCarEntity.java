package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Date;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "BookingCarEntity")
public class BookingCarEntity {
    @Id
    @SequenceGenerator(name="bookingcarSeq",sequenceName="bookingcarSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookingcarSeq")
    @Column(name="bookingcarId",unique = true, nullable = false)

    private @NonNull Long bookingcarId;
    @NotNull
    private Date dateStart;
    @NotNull
    private Date dateEnd;
    @NotNull
    @Pattern(regexp = "^([0-9]+)")
    @Column(unique = true)
    @Size(min=8 , max=8)
    private String driverId;

    //One To One with CustomerEntity
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    @JoinColumn(name = "hotelId")
    @JsonIgnore
    private HotelEntity hotel;

    public Long getBookingcarId() {
        return bookingcarId;
    }

    public void setBookingcarId(Long bookingcarId) {
        this.bookingcarId = bookingcarId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getDriverId() { 
        return driverId; 
    }

    public void setDriverId(String driverId) { 
        this.driverId = driverId; 
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

}
