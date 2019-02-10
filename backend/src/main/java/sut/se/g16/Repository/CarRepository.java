package sut.se.g16.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CarRepository extends JpaRepository<CarEntity, Long>{
    @Query("SELECT t FROM CarEntity t WHERE t.carName = :carName")
    CarEntity findBycarName (@Param("carName")String carName);

    @Query("SELECT n FROM CarEntity n WHERE n.bookingCar.bookingcarId IS NULL")
    Collection<CarEntity> findAllNull(@Param("bookingcarId")Long NULL);

    @Query("SELECT nn FROM CarEntity nn WHERE nn.bookingCar.bookingcarId IS NOT NULL")
    Collection<CarEntity> findAllNotNull(@Param("bookingcarId")Long bookingcarId);
}
