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
@Table (name = "FoodOrderEntity")
public class FoodOrderEntity {
    @Id
    @SequenceGenerator(name="foodOrderSeq",sequenceName="foodOrderSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="foodOrderSeq")
    @Column(name="foodOrderId",unique = true, nullable = false)
    private @NonNull Long foodOrderId;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Many To One with RoomEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomEntity.class)
    private RoomEntity newRoomEntity;

    //Many To One with ListEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ListEntity.class)
    private ListEntity newListEntity;


    //Many To One with HotelEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    private HotelEntity newHotelEntity;

    //Many To One with CustomerEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
    private CustomerEntity newCustomerEntity;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(Long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* many to one get set */

    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }
    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }

    

    
    public ListEntity getNewListEntity() {
        return newListEntity;
    }
    public void setNewListEntity(ListEntity newListEntity) {
        this.newListEntity = newListEntity;
    }


    public RoomEntity getNewRoomEntity() {
        return newRoomEntity;
    }


    public void setNewRoomEntity(RoomEntity newRoomEntity) {
        this.newRoomEntity = newRoomEntity;
    }

    public CustomerEntity getNewCustomerEntity() {
        return newCustomerEntity;
    }

    public void setNewCustomerEntity(CustomerEntity newCustomerEntity) {
        this.newCustomerEntity = newCustomerEntity;
    }

}
