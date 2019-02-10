package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.HotelEntity;
import sut.se.g16.Entity.RoomEntity;
import sut.se.g16.Entity.RoomTypeEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("SELECT t.roomNumber FROM RoomEntity t WHERE t.roomNumber = :Name")
    Integer findByRoomNumber(@Param("Name")String Name);

    @Query("SELECT t.newHotelEntity.hotelId FROM RoomEntity t WHERE t.newHotelEntity.hotelId = :id and t.roomNumber = :roomNumber")
    Long findHotelIdFromHotelIdAndRoomNumber(@Param("id")Long id,@Param("roomNumber")String roomNumber);

    @Query("SELECT t FROM RoomEntity t WHERE t.newHotelEntity.hotelId = :hotelId and t.newRoomTypeEntity.roomTypeId = :roomTypeId and t.newRoomStatusEntity.roomStatusId = :roomStatusId")
    Collection<RoomEntity> findRoomByHotelIdRoomTypeId(@Param("hotelId")long hotelId,@Param("roomTypeId")long roomTypeId,@Param("roomStatusId")long roomStatusId);

    @Query("SELECT t.roomPrice FROM RoomEntity t WHERE t.roomId = :Name")
    int findRoomPriceById(@Param("Name")long Name);

    @Query("SELECT t.roomNumber FROM RoomEntity t WHERE t.roomId = :id")
    String findRoomNumberById(@Param("id")long id);

    @Query("SELECT t.roomId FROM RoomEntity t WHERE t.roomNumber = :roomnumber and t.newHotelEntity.hotelId = :hotelId")
    Long findRoomIdByHotelIdAndRoomNumber(@Param("roomnumber")String roomnumber,@Param("hotelId") Long hotelId);

    @Query("SELECT t FROM RoomEntity t WHERE t.roomId = :Name")
    RoomEntity findRoomByRoomId(@Param("Name")Long Name);

    @Query("SELECT t FROM RoomEntity t WHERE t.newHotelEntity.hotelId = :hotelId")
    Collection<RoomEntity> findRoomByHotelId(@Param("hotelId")long hotelId);

    @Query("SELECT t FROM RoomEntity t WHERE t.newRoomTypeEntity.roomTypeId = :roomTypeId and t.newRoomStatusEntity.roomStatusId = :roomStatusId")
    Collection<RoomEntity> findRoomByRoomTypeId(@Param("roomTypeId")long roomTypeId,@Param("roomStatusId") Long roomStatusId);

    @Query("SELECT t FROM RoomEntity t WHERE t.roomNumber = :Name")
    RoomEntity findRoomEntityByRoomNumber(@Param("Name")String Name);
    
}
