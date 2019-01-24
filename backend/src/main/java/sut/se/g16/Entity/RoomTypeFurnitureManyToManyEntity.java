package sut.se.g16.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "RoomTypeFurnitureManyToMany")
public class RoomTypeFurnitureManyToManyEntity {
    @Id
    @SequenceGenerator(name="roomtypeFurniture_seq",sequenceName="roomtypeFurniture_seq")               
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roomtypeFurniture_seq")  
    @Column(name="roomTypeFurnitureId",unique = true, nullable = false)
    private long roomTypeFurnitureId;

    //Many To One With RoomTypeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    @JoinColumn(name = "roomtypeId")
    private RoomTypeEntity newRoomTypeFurnitureManyEntity;

    //Many To One with FurnitureEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FurnitureEntity.class)
    @JoinColumn(name = "furnitureId")
    private FurnitureEntity newFurnitureRoomTypeManyEntity;

    public RoomTypeEntity getRoomTypeFurnitureManyEntity() {
        return newRoomTypeFurnitureManyEntity;
    }
    public void setRoomTypeFurnitureManyEntity(RoomTypeEntity roomTypeFurnitureMany) {
        this.newRoomTypeFurnitureManyEntity = roomTypeFurnitureMany;
    }
    public FurnitureEntity getFurnitureRoomTypeManyEntity() {
        return newFurnitureRoomTypeManyEntity;
    }
    public void setFurnitureRoomTypeMany(FurnitureEntity furnitureRoomTypeMany) {
        this.newFurnitureRoomTypeManyEntity = furnitureRoomTypeMany;
    }

    public long getRoomTypeFurnitureId() {
        return roomTypeFurnitureId;
    }

    public void setRoomTypeFurnitureId(long roomTypeFurnitureId) {
        this.roomTypeFurnitureId = roomTypeFurnitureId;
    }

    public RoomTypeEntity getNewRoomTypeFurnitureManyEntity() {
        return newRoomTypeFurnitureManyEntity;
    }

    public void setNewRoomTypeFurnitureManyEntity(RoomTypeEntity newRoomTypeFurnitureManyEntity) {
        this.newRoomTypeFurnitureManyEntity = newRoomTypeFurnitureManyEntity;
    }

    public FurnitureEntity getNewFurnitureRoomTypeManyEntity() {
        return newFurnitureRoomTypeManyEntity;
    }

    public void setNewFurnitureRoomTypeManyEntity(FurnitureEntity newFurnitureRoomTypeManyEntity) {
        this.newFurnitureRoomTypeManyEntity = newFurnitureRoomTypeManyEntity;
    }
}
