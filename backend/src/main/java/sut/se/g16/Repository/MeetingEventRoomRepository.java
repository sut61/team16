package sut.se.g16.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.MeetingEventRoomEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface MeetingEventRoomRepository extends JpaRepository<MeetingEventRoomEntity, Long> {
    @Query("SELECT t.newHotelEntity.hotelId FROM MeetingEventRoomEntity t WHERE t.newHotelEntity.hotelId = :id and t.meetingEventRoomNumber = :roomNumber")
    Long findHotelIdFromHotelIdAndMeetingEventRoomNumber(@Param("id")Long id,@Param("roomNumber")String roomNumber);

    @Query("SELECT t.meetingEventRoomNumber FROM MeetingEventRoomEntity t WHERE t.meetingEventRoomId = :id")
    String findRoomNumberById(@Param("id")Long id);

    @Query("SELECT t.newHotelEntity.hotelId FROM MeetingEventRoomEntity t WHERE t.newHotelEntity.hotelId = :id and t.meetingEventRoomNumber = :roomNumber")
    Long findHotelIdFromHotelIdAndRoomNumber(@Param("id")Long id,@Param("roomNumber")String roomNumber);
    
    @Query("SELECT t FROM MeetingEventRoomEntity t WHERE t.meetingEventRoomId = :id")
    MeetingEventRoomEntity findMeetingEventRoomByMeetingEventRoomId(@Param("id")Long id);

    @Query("SELECT t FROM MeetingEventRoomEntity t WHERE t.meetingEventRoomId = :Name")
    MeetingEventRoomEntity findMeetingEventRoomByMeetingRoomId(@Param("Name")Long Name);

    @Query("SELECT t FROM MeetingEventRoomEntity t WHERE t.meetingEventRoomName = :Name")
    MeetingEventRoomEntity findMeetingEventRoomByMeetingRoomName(@Param("Name")String Name);

    @Query("SELECT t.meetingEventRoomId FROM MeetingEventRoomEntity t WHERE t.meetingEventRoomName = :Name")
    Long findMeetingEventRoomIdByMeetingRoomName(@Param("Name")String Name);


    @Query("SELECT t FROM MeetingEventRoomEntity t WHERE t.newMeetingEventRoomTypeEntity.meetingEventRoomTypeId = :id")
    Collection<MeetingEventRoomEntity> findAllByMeetingEventRoomId(@Param("id") Long id);
}
