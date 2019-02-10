package sut.se.g16.Repository;

import sut.se.g16.Entity.*;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query("SELECT t FROM ItemEntity t WHERE t.itemName=:name")
    ItemEntity findByName(@Param("name") String name);
}