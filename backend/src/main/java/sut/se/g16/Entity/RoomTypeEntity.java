package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "RoomType")
public class RoomTypeEntity {
    @Id
    @SequenceGenerator(name="roomtype_seq",sequenceName="roomtype_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roomtype_seq")
    @Column(name="roomTypeId",unique = true, nullable = false)
    private @NotNull Long roomTypeId;

    @Column(unique = true)
    private @NotNull String roomTypeName;
    
    private @NotNull String bedType;
    private @NotNull Long numberOfBed;
    private @NotNull Long maxPeople;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public Long getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Long numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public Long getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Long maxPeople) {
        this.maxPeople = maxPeople;
    }
}
