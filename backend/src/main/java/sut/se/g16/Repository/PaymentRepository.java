package sut.se.g16.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.*;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    // @Query("SELECT t FROM PaymentEntity t WHERE t.name = :billName")
    // PaymentEntity findByBillName(@Param("billName") String billName);

    @Query("SELECT t FROM PaymentEntity t WHERE t.billId = :x")
    PaymentEntity findBillById(@Param("x") Long x);
}
