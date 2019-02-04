package sut.se.g16.Repository;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.MeetingEventRoomTypeTimeManyToManyEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface MeetingEventRoomTypeTimeManyToManyRepository extends JpaRepository<MeetingEventRoomTypeTimeManyToManyEntity, Long> {
    @Query("SELECT t.newRoomStatusEntity.roomStatusName FROM MeetingEventRoomTypeTimeManyToManyEntity t WHERE t.newMeetingEventRoomEntity.meetingEventRoomId = :id")
    Collection<MeetingEventRoomTypeTimeManyToManyEntity> findRoomStatusNameByMeetingRoomId(@Param("id")Long id);

    @Query("SELECT t.meetingEventRoomTypeTimeManyToManyId , t.newRoomStatusEntity.roomStatusName FROM MeetingEventRoomTypeTimeManyToManyEntity t WHERE t.newMeetingEventRoomEntity.meetingEventRoomId = :id")
    Collection<MeetingEventRoomTypeTimeManyToManyEntity> findAllByMeetingRoomId(@Param("id")Long id);

    // @Query("SELECT  t FROM MeetingEventRoomTypeTimeManyToManyEntity t WHERE t.meetingEventRoomTypeTimeManyToManyId = (SELECT MAX(t.meetingEventRoomTypeTimeManyToManyId) FROM MeetingEventRoomTypeTimeManyToManyEntity t)")
    // MeetingEventRoomTypeTimeManyToManyEntity findTest();
}
