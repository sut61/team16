package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.RoomTypeEntity;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
    RoomTypeEntity findByRoomTypeName(String roomTypeName);

    @Query("SELECT t FROM RoomTypeEntity t WHERE t.roomTypeName = :Name")
    RoomTypeEntity findByName(@Param("Name")String Name);
    
    @Query("SELECT t.roomTypeId FROM RoomTypeEntity t WHERE t.roomTypeName = :Name")
    long findIdByName(@Param("Name")String Name);

    @Query("SELECT t FROM RoomTypeEntity t WHERE t.roomTypeName = :Name")
    RoomTypeEntity findRoomTypeByName(@Param("Name")String Name);
    
    @Query ("SELECT t FROM RoomTypeEntity t WHERE t.roomTypeId = :name")
    RoomTypeEntity findByRoomTypeId(@Param("name") Long name);
   

}
