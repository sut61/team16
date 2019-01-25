package sut.se.g16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import sut.se.g16.Entity.RoomTypeFurnitureManyToManyEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface RoomTypeFurnitureManyToManyRepository extends JpaRepository<RoomTypeFurnitureManyToManyEntity, Long> {

}

