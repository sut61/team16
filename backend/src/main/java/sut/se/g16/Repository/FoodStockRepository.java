package sut.se.g16.Repository;

import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface FoodStockRepository extends JpaRepository<FoodStockEntity, Long> {
    // @Query("SELECT t.price FROM FoodStockEntity t WHERE t.stockName =:name")
    // Long findPriceByListName(@Param("name")String name);


    
}