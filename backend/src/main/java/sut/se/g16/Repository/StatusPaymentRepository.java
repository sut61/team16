package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.StatusPaymentEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface StatusPaymentRepository extends JpaRepository<StatusPaymentEntity, Long> {

     @Query ("SELECT t FROM StatusPaymentEntity t WHERE t.statusPaymentId = :name")
     StatusPaymentEntity findByStatusPaymentId(@Param("name") Long name);
   
    @Query ("SELECT t FROM StatusPaymentEntity t WHERE t.statusPaymentTypeName = :name")
    StatusPaymentEntity findByStatusPaymentTypeName(@Param("name") String name);


}