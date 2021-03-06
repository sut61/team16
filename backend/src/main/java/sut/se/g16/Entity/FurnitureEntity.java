package sut.se.g16.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Furniture")
public class FurnitureEntity {
    @Id
    @SequenceGenerator(name="furniture_seq",sequenceName="furniture_seq")               
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="furniture_seq")  
    @Column(name="furnitureId",unique = true, nullable = false)
    private @NotNull Long furnitureId;
    private @NotNull String furnitureName;

    public Long getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Long furnitureId) {
        this.furnitureId = furnitureId;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }
}