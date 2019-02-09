package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findBycustomerEmail(String customerEmail);

    CustomerEntity findBycustomerUsername(String customerUsername);

    CustomerEntity findByCustomerId(Long customerId);

    @Query("SELECT t FROM CustomerEntity t WHERE t.customerUsername = :name")
    CustomerEntity findCustomerByName(@Param("name") String name);

    @Query("SELECT  t FROM CustomerEntity t WHERE t.customerId = (SELECT MAX(t.customerId) FROM CustomerEntity t)")
    CustomerEntity findByrecord();

    @Query("SELECT u FROM CustomerEntity u WHERE u.customerUsername=:Name")
    CustomerEntity findByUser(@Param("Name") String Name);

    @Query("SELECT u.customerId FROM CustomerEntity u WHERE u.customerUsername = :Name and u.customerPassword = :pass")
    Long findCustomerIdByUserNameAndPassword(@Param("Name") String Name,@Param("pass")String pass);
    
}