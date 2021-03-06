package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.TypeTimeEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface TypeTimeRepository extends JpaRepository<TypeTimeEntity, Long> {

    TypeTimeEntity findBytypeTimeName(String typeTimeName);
    
    @Query("SELECT t FROM TypeTimeEntity t WHERE t.typeTimeName = :Name")
    TypeTimeEntity findTypeTimeByName(@Param("Name")String Name);

    @Query("SELECT t.typeTimeId FROM TypeTimeEntity t WHERE t.typeTimeName = :Name")
    Long findTypeTimeIdByName(@Param("Name")String Name);

    @Query("SELECT t FROM TypeTimeEntity t WHERE t.typeTimeId = :id")
    TypeTimeEntity findTypeTimeById(@Param("id")Long id);

    
}
