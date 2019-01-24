package sut.se.g16.Repository;

import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface FoodPaymentRepository extends JpaRepository<FoodPaymentEntity, Long> {
    @Query("SELECT t FROM FoodPaymentEntity t WHERE t.foodPaymentStatus = :Status")
    FoodPaymentEntity findByStatus(@Param("Status")String Status);

    @Query("SELECT t.foodPaymentId FROM FoodPaymentEntity t WHERE t.foodPaymentStatus = :Status")
    Long findFoodPaymentIdByStatus(@Param("Status")String Status);

}
