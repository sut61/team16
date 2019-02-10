package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "FoodStockListManyToManyEntity")
public class FoodStockListManyToManyEntity {
  @Id
  @SequenceGenerator(name = "foodStockList_seq", sequenceName = "foodStockList_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodStockList_seq")
  @Column(name = "foodStockListId", unique = true, nullable = false)
  private @NotNull Long foodStockListId;

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // Many To One with FoodStockEntity
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = FoodStockEntity.class)
  private FoodStockEntity newFoodStockEntity;

  // Many To One with ListEntity
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = ListEntity.class)
  private ListEntity newListEntity;

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /* get set */

  public Long getFoodStockListId() {
    return foodStockListId;
  }

  public void setFoodStockListId(Long foodStockListId) {
    this.foodStockListId = foodStockListId;
  }
  

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /* many to one get set */

  public FoodStockEntity getNewFoodStockEntity() {
    return newFoodStockEntity;
  }

  public void setNewFoodStockEntity(FoodStockEntity newFoodStockEntity) {
    this.newFoodStockEntity = newFoodStockEntity;
  }

  public ListEntity getNewListEntity() {
    return newListEntity;
  }

  public void setNewListEntity(ListEntity newListEntity) {
    this.newListEntity = newListEntity;
  }

  
}
