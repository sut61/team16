package sut.se.g16.Repository;

import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface FoodTypeRepository extends JpaRepository<FoodTypeEntity, Long> {
    @Query("SELECT t FROM FoodTypeEntity t WHERE t.foodTypeName = :Name")
    FoodTypeEntity findByName(@Param("Name")String Name);

    @Query("SELECT t.foodTypeId FROM FoodTypeEntity t WHERE t.foodTypeName = :Name")
    Long findFoodTypeIdByName(@Param("Name")String Name);
}
