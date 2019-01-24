package sut.se.g16.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.RoomStatusEntity;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface RoomStatusRepository extends JpaRepository<RoomStatusEntity, Long> {
    @Query("SELECT t FROM RoomStatusEntity t WHERE t.roomStatusName = :Name")
    RoomStatusEntity findByName(@Param("Name")String Name);

    @Query("SELECT t FROM RoomStatusEntity t WHERE t.roomStatusId = :id1 or t.roomStatusId = :id2 or t.roomStatusId = :id3")
    Collection<RoomStatusEntity> findAllForRoom(@Param("id1")Long id1,@Param("id2")Long id2,@Param("id3")Long id3);
    
    @Query("SELECT t.roomStatusId FROM RoomStatusEntity t WHERE t.roomStatusName = :Name")
    Long findIdByName(@Param("Name")String Name);
    
}
