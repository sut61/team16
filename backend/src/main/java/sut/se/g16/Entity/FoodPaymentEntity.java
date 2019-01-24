package sut.se.g16.Entity;

import  javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "FoodPaymentEntity")
public class FoodPaymentEntity {
    @Id
    @SequenceGenerator(name="foodPaymentSeq",sequenceName="foodPaymentSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="foodPaymentSeq")
    @Column(name="foodPaymentId",unique = true, nullable = false)
    private @NonNull Long foodPaymentId;
    private @NonNull String foodPaymentStatus;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */


    public Long getFoodPaymentId() {
        return foodPaymentId;
    }

    public void setFoodPaymentId(Long foodPaymentId) {
        this.foodPaymentId = foodPaymentId;
    }

    public String getFoodPaymentStatus() {
        return foodPaymentStatus;
    }

    public void setFoodPaymentStatus(String foodPaymentStatus) {
        this.foodPaymentStatus = foodPaymentStatus;
    }
}
