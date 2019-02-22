package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
    private @NotNull Long listId;

    @Column(unique = true)
    @Size(min = 3, max = 20)
    @Pattern (regexp = "[A-Z][a-z]+\\s?[A-Z]?[a-z]*")
    private @NotNull String listName;

    @Min(value = 0)
    @Max(value = 99)
    private @NotNull Long totalAmount;
    @Positive
    private @NotNull Long listPrice;
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
    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Long getListPrice() {
        return listPrice;
    }

    public void setListPrice(Long listPrice) {
        this.listPrice = listPrice;
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

