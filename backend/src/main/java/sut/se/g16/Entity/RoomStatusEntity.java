package sut.se.g16.Entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "RoomStatus")
public class RoomStatusEntity {
    @Id
    @SequenceGenerator(name="roomstatus_seq",sequenceName="roomstatus_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roomstatus_seq")
    @Column(name="roomStatusId",unique = true, nullable = false)
    private Long roomStatusId;
    private String roomStatusName;

    public Long getRoomStatusId() {
        return roomStatusId;
    }

    public void setRoomStatusId(Long roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public String getRoomStatusName() {
        return roomStatusName;
    }

    public void setRoomStatusName(String roomStatusName) {
        this.roomStatusName = roomStatusName;
    }
}
