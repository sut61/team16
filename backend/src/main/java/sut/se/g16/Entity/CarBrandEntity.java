package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "CarBrandEntity")
public class CarBrandEntity {
    @Id
    @SequenceGenerator(name="carbrandSeq",sequenceName="carbrandSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carbrandSeq")
    @Column(name="carbrandId",unique = true, nullable = false)
    
    private @NonNull Long carbrandId;
    @NotNull
    private String carBrand;

    public Long getCarbrandId() {
        return carbrandId;
    }

    public void setCarbrandId(Long carbrandId) {
        this.carbrandId = carbrandId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
