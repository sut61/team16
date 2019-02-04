package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "TypeTime")
public class TypeTimeEntity {
    @Id
    @SequenceGenerator(name="meetingeventroomtypetime_seq",sequenceName="meetingeventroomtypetime_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="meetingeventroomtypetime_seq")
    @Column(name="meetingEventRoomTypeTimeManyToManyId",unique = true, nullable = false)
    private @NotNull Long typeTimeId;
    private @NotNull String typeTimeName;

    public Long getTypeTimeId() {
        return typeTimeId;
    }

    public void setTypeTimeId(Long typeTimeId) {
        this.typeTimeId = typeTimeId;
    }

    public String getTypeTimeName() {
        return typeTimeName;
    }

    public void setTypeTimeName(String typeTimeName) {
        this.typeTimeName = typeTimeName;
    }
}
