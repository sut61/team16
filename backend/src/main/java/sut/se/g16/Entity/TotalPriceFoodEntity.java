package sut.se.g16.Entity;

import  javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table (name = "TotalPriceFoodEntity")
public class TotalPriceFoodEntity {
    @Id
    @SequenceGenerator(name="totalPriceFoodSeq",sequenceName="totalPriceFoodSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="totalPriceFoodSeq")
    @Column(name="totalPriceFoodId",unique = true, nullable = false)
    private @NonNull Long totalPriceFoodId;
    private @NonNull Long totalPrice;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Many To One with RoomEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomEntity.class)
    private RoomEntity newRoomEntity;

    //Many To One with FoodPaymentEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodPaymentEntity.class)
    private FoodPaymentEntity newFoodPaymentEntity;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getTotalPriceFoodId() {
        return totalPriceFoodId;
    }

    public void setTotalPriceFoodId(Long totalPriceFoodId) {
        this.totalPriceFoodId = totalPriceFoodId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* many to one get set */

	public RoomEntity getNewRoomEntity() {
		return newRoomEntity;
	}
	public void setNewRoomEntity(RoomEntity newRoomEntity) {
		this.newRoomEntity = newRoomEntity;
	}

	
	public FoodPaymentEntity getNewFoodPaymentEntity() {
		return newFoodPaymentEntity;
	}
	public void setNewFoodPaymentEntity(FoodPaymentEntity newFoodPaymentEntity) {
		this.newFoodPaymentEntity = newFoodPaymentEntity;
	}
    
}
