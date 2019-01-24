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
public interface TotalPriceFoodRepository extends JpaRepository<TotalPriceFoodEntity, Long> {

    @Query("SELECT t.totalPriceFoodId FROM TotalPriceFoodEntity t WHERE t.newRoomEntity.roomId = :roomid AND t.newFoodPaymentEntity.foodPaymentId = :statusId")
    Long findTotalPriceFoodIdByRoomIdAndFoodPaymentId(@Param("roomid") Long roomid,@Param("statusId") Long statusId);

    @Query("SELECT t.totalPrice FROM TotalPriceFoodEntity t WHERE t.newRoomEntity.roomId = :roomid AND t.newFoodPaymentEntity.foodPaymentId = :statusId")
    Long findTotalPriceFoodByRoomIdAndFoodPaymentId(@Param("roomid") Long roomid,@Param("statusId") Long statusId);

    @Query("SELECT t.totalPrice FROM TotalPriceFoodEntity t WHERE t.totalPriceFoodId = :totalPriceId")
    Long findTotalByTotalPriceId(@Param("totalPriceId") Long totalPriceId);

    @Query("SELECT t FROM TotalPriceFoodEntity t WHERE t.newFoodPaymentEntity.foodPaymentStatus = :status")
    Collection<TotalPriceFoodEntity> findAllByFoodPayment(@Param("status") String status);
    
}
