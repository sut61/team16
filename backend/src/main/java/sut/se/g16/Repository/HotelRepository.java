package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.HotelEntity;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    HotelEntity findByHotelId(Long hotelId);

    HotelEntity findByhotelNameEng(String hotelNameEng);

    @Query("SELECT t FROM HotelEntity t WHERE t.hotelNameEng = :Name")
    HotelEntity findByName(@Param("Name") String Name);

    @Query("SELECT t.hotelId FROM HotelEntity t WHERE t.hotelNameEng = :Name")
    Long findHotelIdByName(@Param("Name") String Name);

    @Query("SELECT t FROM HotelEntity t WHERE t.newMemberHotelEntity.memberHotelId  = :id")
    HotelEntity findHotelByMemberId(@Param("id") Long id);

    @Query("SELECT t.hotelId FROM HotelEntity t WHERE t.newMemberHotelEntity.memberHotelId = :id")
    Long findHotelIdByMemId(@Param("id") Long id);

    @Query("SELECT t FROM HotelEntity t WHERE t.newProvinceEntity.provinceId = :Name")
    Collection<HotelEntity> findHotelByProvinceId(@Param("Name") long provinceId);

    @Query("SELECT t.newProvinceEntity.provinceId FROM HotelEntity t WHERE t.hotelId = :hotelId")
    Long findProvinceIdByHotelId(@Param("hotelId") long hotelId);

    @Query("SELECT t.hotelId FROM HotelEntity t WHERE t.newMemberHotelEntity.memberHotelName  = :name")
    Long findHotelIdByMemberUserName(@Param("name") String name);

    @Query("SELECT t.hotelNameEng FROM HotelEntity t WHERE t.newMemberHotelEntity.memberHotelName  = :name")
    String findHotelNameEngByMemberUserName(@Param("name") String name);

    @Query("SELECT t FROM HotelEntity t WHERE t.newMemberHotelEntity.memberHotelName  = :name")
    HotelEntity findHotelByMemberUserName(@Param("name") String name);

    @Query("SELECT t FROM HotelEntity t WHERE t.hotelNameEng = :name")
    HotelEntity findHotelByName(@Param("name") String name);

    @Query("SELECT t.hotelId FROM HotelEntity t WHERE t.hotelNameEng = :Name")
    Long findByHotelName(@Param("Name") String Name);

}
