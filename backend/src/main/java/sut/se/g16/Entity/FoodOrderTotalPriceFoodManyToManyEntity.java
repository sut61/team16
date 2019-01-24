package sut.se.g16.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "FoodOrderTotalPriceFoodManyToMany")
public class FoodOrderTotalPriceFoodManyToManyEntity {
    @Id
    @SequenceGenerator(name="foodOrderTotalPriceFood_seq",sequenceName="foodOrderTotalPriceFood_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="foodOrderTotalPriceFood_seq")
    @Column(name="foodOrderTotalPriceFoodManyToManyId",unique = true, nullable = false)
    private @NonNull Long foodOrderTotalPriceFoodManyToManyId;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Many To One with TotalPriceFoodEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TotalPriceFoodEntity.class)
    private TotalPriceFoodEntity newTotalPriceFoodEntity;

    //Many To One with FoodOrderEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodOrderEntity.class)
    private FoodOrderEntity newFoodOrderEntity;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getFoodOrderTotalPriceFoodManyToManyId() {
		return foodOrderTotalPriceFoodManyToManyId;
	}
	public void setFoodOrderTotalPriceFoodManyToManyId(Long foodOrderTotalPriceFoodManyToManyId) {
		this.foodOrderTotalPriceFoodManyToManyId = foodOrderTotalPriceFoodManyToManyId;
    }
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* many to one get set */

    public TotalPriceFoodEntity getNewTotalPriceFoodEntity() {
		return newTotalPriceFoodEntity;
	}
	public void setNewTotalPriceFoodEntity(TotalPriceFoodEntity newTotalPriceFoodEntity) {
		this.newTotalPriceFoodEntity = newTotalPriceFoodEntity;
	}

	
	public FoodOrderEntity getNewFoodOrderEntity() {
		return newFoodOrderEntity;
	}
	public void setNewFoodOrderEntity(FoodOrderEntity newFoodOrderEntity) {
		this.newFoodOrderEntity = newFoodOrderEntity;
	}

    
}
