package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "CarEntity")
public class CarEntity {
    @Id
    @SequenceGenerator(name="carSeq",sequenceName="carSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carSeq")
    @Column(name="carId",unique = true, nullable = false)

    private @NonNull Long carId;
    @NotNull
    private  String carName;
    @NotNull 
    private Integer carPrice;

    //One To One with BookingCarEntity
    @OneToOne(fetch = FetchType.EAGER, targetEntity = BookingCarEntity.class)
    @JoinColumn(name = "bookingcarId")
    private BookingCarEntity bookingCar;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarTypeEntity.class)
    @JoinColumn(name = "cartypeId")
    @JsonIgnore
    private CarTypeEntity cartype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarBrandEntity.class)
    @JoinColumn(name = "carbrandId")
    @JsonIgnore
    private CarBrandEntity carbrand;

    public Long getCarId() {
        return carId;
    }

    public BookingCarEntity getBookingCar() {
        return bookingCar;
    }

    public void setBookingCar(BookingCarEntity bookingCar) {
        this.bookingCar = bookingCar;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Integer carPrice) {
        this.carPrice = carPrice;
    }

    public CarTypeEntity getCartype() {
        return cartype;
    }

    public void setCartype(CarTypeEntity cartype) {
        this.cartype = cartype;
    }

    public CarBrandEntity getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(CarBrandEntity carbrand) {
        this.carbrand = carbrand;
    }

}
