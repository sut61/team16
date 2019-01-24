package sut.se.g16.Repository;

import sut.se.g16.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface FoodOrderTotalPriceFoodManyToManyRepository extends JpaRepository<FoodOrderTotalPriceFoodManyToManyEntity, Long> {
    // @Query("SELECT t.newRoomStatusEntity.roomStatusName FROM MeetingEventRoomTypeTimeManyToManyEntity t WHERE t.newMeetingEventRoomEntity.meetingEventRoomId = :id")
    // Collection<MeetingEventRoomTypeTimeManyToManyEntity> findRoomStatusNameByMeetingRoomId(@Param("id")Long id);

    // @Query("SELECT t.meetingEventRoomTypeTimeManyToManyId , t.newRoomStatusEntity.roomStatusName FROM MeetingEventRoomTypeTimeManyToManyEntity t WHERE t.newMeetingEventRoomEntity.meetingEventRoomId = :id")
    // Collection<MeetingEventR0oo0mTypeTimeManyToManyEntity> findAllByMeetingRoomId(@Param("id")Long id);
}
