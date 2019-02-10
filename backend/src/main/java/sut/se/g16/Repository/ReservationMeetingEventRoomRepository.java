package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.ReservationMeetingEventRoomEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ReservationMeetingEventRoomRepository extends JpaRepository<ReservationMeetingEventRoomEntity, Long> {
    ReservationMeetingEventRoomEntity findByReservationMeetingEventRoomId(Long roomTypeId);

    @Query("SELECT t FROM ReservationMeetingEventRoomEntity t WHERE t.newCustomerEntity.customerUsername = :name")
    Collection<ReservationMeetingEventRoomEntity> findAllByCustomerName(@Param("name") String name);
    
    

    
}