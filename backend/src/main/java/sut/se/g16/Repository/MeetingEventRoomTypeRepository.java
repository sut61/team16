package sut.se.g16.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.MeetingEventRoomTypeEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface MeetingEventRoomTypeRepository extends JpaRepository<MeetingEventRoomTypeEntity, Long> {
    
    @Query("SELECT t FROM MeetingEventRoomTypeEntity t WHERE t.meetingEventRoomTypeName = :meetingRoomType")
    MeetingEventRoomTypeEntity findMeetingEventRoomTypeByName(@Param("meetingRoomType")String meetingRoomType);
}
