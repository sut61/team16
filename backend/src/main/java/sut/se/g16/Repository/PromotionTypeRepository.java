package sut.se.g16.Repository;

import sut.se.g16.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@EnableJpaRepositories
@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface PromotionTypeRepository extends JpaRepository<PromotionTypeEntity, Long> {
    PromotionTypeEntity findByPromotionTypeId(Long promotionTypeId);
    PromotionTypeEntity findByPromotionTypeName(String promotionTypeName);

}
