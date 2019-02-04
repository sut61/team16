package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data @Getter @Setter
@NoArgsConstructor
@ToString
@Table(name = "MeetingEventRoomType")
public class MeetingEventRoomTypeEntity {
    @Id
    @SequenceGenerator(name="meetingeventroomtype_seq",sequenceName="meetingeventroomtype_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="meetingeventroomtype_seq")
    @Column(name="meetingEventRoomTypeId",unique = true, nullable = false)
    private @NotNull Long meetingEventRoomTypeId;
    private @NotNull String meetingEventRoomTypeName;

    public Long getMeetingEventRoomTypeId() {
        return meetingEventRoomTypeId;
    }

    public void setMeetingEventRoomTypeId(Long meetingEventRoomTypeId) {
        this.meetingEventRoomTypeId = meetingEventRoomTypeId;
    }

    public String getMeetingEventRoomTypeName() {
        return meetingEventRoomTypeName;
    }

    public void setMeetingEventRoomTypeName(String meetingEventRoomTypeName) {
        this.meetingEventRoomTypeName = meetingEventRoomTypeName;
    }
}
