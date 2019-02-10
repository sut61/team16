package sut.se.g16.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface BookingCarRepository extends JpaRepository<BookingCarEntity, Long>{
    @Query("SELECT t.hotel.hotelId FROM BookingCarEntity t WHERE t.bookingcarId = :id")
    Long findHotelIdByBookingCarId(@Param("id")Long id);

   
}
