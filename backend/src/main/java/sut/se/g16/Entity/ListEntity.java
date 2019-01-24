package sut.se.g16.Entity;
import  javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "ListEntity")
public class ListEntity {
    @Id
    @SequenceGenerator(name="listSeq",sequenceName="listSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="listSeq")
    @Column(name="listId",unique = true, nullable = false)
    private @NonNull Long listId;
    private @NonNull String listName;
    private @NonNull Long priceFood;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Many To One with FoodTypeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodTypeEntity.class)
    private FoodTypeEntity newFoodTypeEntity;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(Long priceFood) {
        this.priceFood = priceFood;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* many to one get set */

    public FoodTypeEntity getNewFoodTypeEntity() {
        return newFoodTypeEntity;
    }
    public void setNewFoodTypeEntity(FoodTypeEntity newFoodTypeEntity) {
        this.newFoodTypeEntity = newFoodTypeEntity;
    }

}

