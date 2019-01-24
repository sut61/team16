package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.FurnitureEntity;


@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface FurnitureRepository extends JpaRepository<FurnitureEntity, Long> {
    @Query("SELECT t FROM FurnitureEntity t WHERE t.furnitureName = :Name")
    FurnitureEntity findByName(@Param("Name") String Name);
}