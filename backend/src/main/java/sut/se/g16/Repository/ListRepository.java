package sut.se.g16.Repository;

import sut.se.g16.Entity.*;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ListRepository extends JpaRepository<ListEntity, Long> {
    
    @Query("SELECT t FROM ListEntity t WHERE t.listName = :Name")
    ListEntity findListByName(@Param("Name")String Name);

    @Query("SELECT t FROM ListEntity t WHERE t.newFoodTypeEntity.foodTypeId = :foodTypeId")
    Collection<ListEntity> findListByFoodTypeId(@Param("foodTypeId")long foodTypeId);

    @Query("SELECT t.priceFood FROM ListEntity t WHERE t.listName = :Name")
    Long findPriceByList(@Param("Name")String Name);

}