package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Table(name ="PromotionTypeEntity")
public class PromotionTypeEntity {
    @Id
    @SequenceGenerator(name = "promotionTypeEntity_seq", sequenceName = "promotionTypeEntity_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotionTypeEntity_seq")
    @Column(name = "PromotionTypeEntity_id", unique = true, nullable = false)
    private @NonNull
    Long PromotionTypeEntity_id;

    private String PromotionTypeEntity_name;


    public Long getId() {
        return PromotionTypeEntity_id;
    }
    public void setId(Long promotionTypeEntity_id) {
        this.PromotionTypeEntity_id = promotionTypeEntity_id;
    }


    public String getPromotionTypeEntity_name() {
        return PromotionTypeEntity_name;
    }

    public void setPromotionTypeEntity_name(String PromotionTypeEntity_name) {
        this.PromotionTypeEntity_name = PromotionTypeEntity_name;
    }



    public PromotionTypeEntity() {
    }

    public PromotionTypeEntity(String PromotionTypeEntity_name) {  //constructor

        this.PromotionTypeEntity_name = PromotionTypeEntity_name;

    }
}


