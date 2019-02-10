package sut.se.g16.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;
@RepositoryRestController
@EnableJpaRepositories
@CrossOrigin(origins = "http://localhost:4200")
public interface PaymentAllRepository extends JpaRepository<PaymentAllEntity, Long>{
    PaymentAllEntity findByPaymentAllId(Long PaymentAllId);

    @Query("SELECT t FROM PaymentAllEntity t WHERE t.customerEntity.customerUsername = :name")
    Collection<PaymentAllEntity> findAllByCustomerName(@Param("name") String name);
}

