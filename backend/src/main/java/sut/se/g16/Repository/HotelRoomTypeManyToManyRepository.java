package sut.se.g16.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.*;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface HotelRoomTypeManyToManyRepository extends JpaRepository<HotelRoomTypeManyToManyEntity, Long> {
    @Query("SELECT t FROM HotelRoomTypeManyToManyEntity t WHERE t.newHotelEntity.hotelId = :id")
    Collection<HotelRoomTypeManyToManyEntity> findAllByHotelId(@Param("id") Long id);
    
}