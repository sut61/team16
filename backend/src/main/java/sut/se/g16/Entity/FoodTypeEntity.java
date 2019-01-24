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
@Table (name = "FoodTypeEntity")
public class FoodTypeEntity {
    @Id
    @SequenceGenerator(name="foodTypeSeq",sequenceName="foodTypeSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="foodTypeSeq")
    @Column(name="foodTypeId",unique = true, nullable = false)
    private @NonNull Long foodTypeId;
    private @NonNull String foodTypeName;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */


    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }
}
