package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Setter
@Getter
@Table(name="Item")
@Entity
public class ItemEntity{
    @SequenceGenerator(name = "Items_seq", sequenceName = "Items_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_seq")
    @Column(name = "itemsId", unique = true, nullable = false)
    @Id
    @NotNull private Long itemId;
    @NotNull private String itemName;
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}