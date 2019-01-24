package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.MemberHotelEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface MemberHotelRepository extends JpaRepository<MemberHotelEntity, Long> {
    @Query("SELECT t FROM MemberHotelEntity t WHERE t.memberHotelName = :Name")
    MemberHotelEntity findByName(@Param("Name") String Name);

    @Query("SELECT t.memberHotelId FROM MemberHotelEntity t WHERE t.memberHotelName = :Name AND t.memberHotelPassword = :pass")
    Long isLogin(@Param("Name") String Name, @Param("pass") Long pass);

    @Query("SELECT t.memberHotelId FROM MemberHotelEntity t WHERE t.memberHotelName = :Name")
    Long findId(@Param("Name") String Name);
    
    @Query("SELECT t.memberHotelId FROM MemberHotelEntity t WHERE t.memberHotelName = :Name")
    Long findMemberIdByName(@Param("Name")String Name);
}