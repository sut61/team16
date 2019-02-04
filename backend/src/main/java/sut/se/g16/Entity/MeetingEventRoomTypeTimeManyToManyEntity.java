package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "MeetingEventRoomTypeTimeManyToMany")
public class MeetingEventRoomTypeTimeManyToManyEntity {
    @Id
    @SequenceGenerator(name="meetingeventroomtyptime_seq",sequenceName="meetingeventroomtyptime_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="meetingeventroomtyptime_seq")
    @Column(name="meetingEventRoomTypeTimeManyToManyId",unique = true, nullable = false)
    private @NotNull Long meetingEventRoomTypeTimeManyToManyId;

    //Many To One with MeetingEventRoomEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MeetingEventRoomEntity.class)
    private MeetingEventRoomEntity newMeetingEventRoomEntity;

    //Many To One with TypeTimeEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeTimeEntity.class)
    private TypeTimeEntity newTypeTimeEntity;

    //Many To One with RoomStatusEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomStatusEntity.class)
    private RoomStatusEntity newRoomStatusEntity;

    public Long getMeetingEventRoomTypeTimeManyToManyId() {
        return meetingEventRoomTypeTimeManyToManyId;
    }

    public void setMeetingEventRoomTypeTimeManyToManyId(Long meetingEventRoomTypeTimeManyToManyId) {
        this.meetingEventRoomTypeTimeManyToManyId = meetingEventRoomTypeTimeManyToManyId;
    }

    public MeetingEventRoomEntity getNewMeetingEventRoomEntity() {
        return newMeetingEventRoomEntity;
    }

    public void setNewMeetingEventRoomEntity(MeetingEventRoomEntity newMeetingEventRoomEntity) {
        this.newMeetingEventRoomEntity = newMeetingEventRoomEntity;
    }

    public TypeTimeEntity getNewTypeTimeEntity() {
        return newTypeTimeEntity;
    }

    public void setNewTypeTimeEntity(TypeTimeEntity newTypeTimeEntity) {
        this.newTypeTimeEntity = newTypeTimeEntity;
    }

    public RoomStatusEntity getNewRoomStatusEntity() {
        return newRoomStatusEntity;
    }

    public void setNewRoomStatusEntity(RoomStatusEntity newRoomStatusEntity) {
        this.newRoomStatusEntity = newRoomStatusEntity;
    }
}
