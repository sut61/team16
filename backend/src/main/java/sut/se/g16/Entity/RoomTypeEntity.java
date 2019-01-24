package sut.se.g16.Entity;

import javax.persistence.*;
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
    private @NonNull Long roomTypeId;
    private @NonNull String roomTypeName;
    private @NonNull String bedType;
    private @NonNull int numberOfBed;
    private @NonNull int maxPeople;

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

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }
}
