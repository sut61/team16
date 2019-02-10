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
public interface SentYourLostManytoManyItemRepository extends JpaRepository<SentyourLostManytoManyItemEntity, Long> {
    @Query("SELECT t FROM SentyourLostManytoManyItemEntity t WHERE t.newSentYourLostEntity.sentId = :id")
    SentyourLostManytoManyItemEntity findAllBySentId(@Param("id") Long id);

    @Query("SELECT t FROM SentyourLostManytoManyItemEntity t WHERE t.newItemEntity.itemId=:id")
    SentyourLostManytoManyItemEntity findAllByItemId(@Param("id")Long id);
    
}