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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodOrderEntity.class)
    @JoinColumn(name = "foodOrderId")
    @JsonIgnore
    private FoodOrderEntity foodOrder;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodTypeEntity.class)
    @JoinColumn(name = "foodTypeId")
    @JsonIgnore
    private FoodTypeEntity foodType;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // public FoodOrderEntity getTitleName() {
    //     return titleName;
    // }

    // public void setTitleName(FoodOrderEntity titleName) {
    //     this.titleName = titleName;
    // }

    public FoodTypeEntity getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodTypeEntity foodType) {
        this.foodType = foodType;
    }

}

