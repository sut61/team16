package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
@Table (name = "FoodStockEntity")
public class FoodStockEntity {
    @Id
    @SequenceGenerator(name="foodStockSeq",sequenceName="foodStockSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="foodStockSeq")
    @Column(name="foodStockId",unique = true, nullable = false)
    private @NotNull Long foodStockId;

    @Size(min = 3, max = 20)
    @Pattern (regexp = "[A-Z][a-z]+\\s?[A-Z]?[a-z]*")
    private @NotNull String stockName;

    @Max(value = 9999)
    @Positive
    private @NotNull Long price;

    @Min(value = 1)
    @Max(value = 99)
    @Positive
    private @NotNull Long amount ;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Many To One with HotelEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    private HotelEntity newHotelEntity;

    //Many To One with EmployeeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmployeeEntity.class)
    private EmployeeEntity newEmployeeEntity;

    //Many To One with FoodTypeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodTypeEntity.class)
    private FoodTypeEntity newFoodTypeEntity;

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getFoodStockId() {
        return foodStockId;
    }

    public void setFoodStockId(Long foodStockId) {
        this.foodStockId = foodStockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set many to one*/
    
    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }

    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }

    public EmployeeEntity getNewEmployeeEntity() {
        return newEmployeeEntity;
    }

    public void setNewEmployeeEntity(EmployeeEntity newEmployeeEntity) {
        this.newEmployeeEntity = newEmployeeEntity;
    }

    public FoodTypeEntity getNewFoodTypeEntity() {
        return newFoodTypeEntity;
    }

    public void setNewFoodTypeEntity(FoodTypeEntity newFoodTypeEntity) {
        this.newFoodTypeEntity = newFoodTypeEntity;
    }


    

}
