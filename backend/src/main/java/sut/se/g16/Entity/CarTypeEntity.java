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
@Table (name = "CarTypeEntity")
public class CarTypeEntity {
    @Id
    @SequenceGenerator(name="cartypeSeq",sequenceName="cartypeSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cartypeSeq")
    @Column(name="cartypeId",unique = true, nullable = false)

    private @NonNull Long cartypeId;
    @NotNull
    private String carType;

    public Long getCartypeId() {
        return cartypeId;
    }

    public void setCartypeId(Long cartypeId) {
        this.cartypeId = cartypeId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
