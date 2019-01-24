package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.PromotionEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
    @Query ("SELECT t FROM PromotionEntity t WHERE t.promotionId = :name")
    PromotionEntity findByPromotionId(@Param("name") Long name);

    @Query ("SELECT t FROM PromotionEntity t WHERE t.promotionName = :name")
    PromotionEntity findByPromotionName(@Param("name") String name);
    
    @Query ("SELECT t FROM PromotionEntity t WHERE t.newHotelEntity.hotelId = :hotelId")
    Collection<PromotionEntity> getPromotionByHotelId(@Param("hotelId") Long hotelId);
}
