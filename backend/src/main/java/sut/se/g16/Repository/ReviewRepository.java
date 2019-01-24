package sut.se.g16.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.*;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT re FROM ReviewEntity re WHERE re.reviewId=:reviewId")
    ReviewEntity findByReview(@Param("reviewId") Long reviewId);
}