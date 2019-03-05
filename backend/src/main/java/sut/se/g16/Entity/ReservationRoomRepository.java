package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.ReservationRoomEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ReservationRoomRepository extends JpaRepository<ReservationRoomEntity, Long> {
    ReservationRoomEntity findByReservationRoomId(Long roomTypeId);

    @Query("SELECT t FROM ReservationRoomEntity t WHERE t.newCustomerEntity.customerUsername = :name")
    Collection<ReservationRoomEntity> findAllByCustomerName(@Param("name") String name);
    
    @Query("SELECT t FROM ReservationRoomEntity t WHERE t.newCustomerEntity.customerUsername = :name and t.newStatusPaymentEntity.statusPaymentTypeName = :status")
    Collection<ReservationRoomEntity> findAllByMemberUserNameAndStatusPayment(@Param("status") String status,@Param("name") String name);
    
    @Query("SELECT t FROM ReservationRoomEntity t WHERE t.reservationRoomId = :name")
    ReservationRoomEntity findRservationById(@Param("name") Long name);
    
}
