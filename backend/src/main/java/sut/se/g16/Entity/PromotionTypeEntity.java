package sut.se.g16.Entity;

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
    @SequenceGenerator(name = "promotionType_seq", sequenceName = "promotionType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotionType_seq")
    @Column(name = "promotionTypeId", unique = true, nullable = false)
    private @NonNull
    Long promotionTypeId;

    private String promotionTypeName;


    public Long getPromotionTypeId() {
        return promotionTypeId;
    }
    public void setPromotionTypeId(Long promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }


    public String getPromotionTypeName() {
        return promotionTypeName;
    }

    public void setPromotionTypeName(String promotionTypeName) {
        this.promotionTypeName = promotionTypeName;
    }



    public PromotionTypeEntity() {
    }

    public PromotionTypeEntity(String promotionTypeName) {  //constructor

        this.promotionTypeName = promotionTypeName;

    }
}


