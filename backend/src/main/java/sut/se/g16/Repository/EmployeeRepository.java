package sut.se.g16.Repository;

import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT t FROM EmployeeEntity t WHERE t.employeeUserName = :Name")
    EmployeeEntity findByUser(@Param("Name") String Name);

    @Query("SELECT t.employeeId FROM EmployeeEntity t WHERE t.employeeUserName = :Name AND t.employeePassword = :pass")
    Long isLogin(@Param("Name") String Name, @Param("pass") String pass);

    @Query("SELECT t.employeeId FROM EmployeeEntity t WHERE t.employeeUserName = :Name")
    Long findId(@Param("Name") String Name);
    
    @Query("SELECT t.employeeId FROM EmployeeEntity t WHERE t.employeeUserName = :Name")
    Long findEmployeeIdByName(@Param("Name")String Name);

    @Query("SELECT u.employeeId FROM EmployeeEntity u WHERE u.employeeUserName = :Name and u.employeePassword = :pass")
    Long findEmployeeIdByUserNameAndPassword(@Param("Name") String Name,@Param("pass")String pass);
}