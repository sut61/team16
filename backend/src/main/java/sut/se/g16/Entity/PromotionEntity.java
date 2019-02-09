package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class PromotionEntity {
   
    @Id  
    @SequenceGenerator(name="promotionId_seq",sequenceName="promotionId_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="promotionId_seq") 
    private @NotNull Long promotionId;
    private @NotNull String promotionName;
    private @NotNull Date dateStart;
    private @NotNull Date dateEnd;
    private @NotNull String detail;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PromotionTypeEntity.class)
    @JoinColumn(name = "PromotionType_ID", insertable = true)
    private  PromotionTypeEntity newPromotionTypeEntity;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = AdminEntity.class)
    @JoinColumn(name = "Member_ID", insertable = true)
    private AdminEntity newAdminEntity;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    @JoinColumn(name = "Hotel_ID", insertable = true)
    private  HotelEntity newHotelEntity;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    @JoinColumn(name = "RoomType_ID", insertable = true)
    private  RoomTypeEntity newRoomTypeEntity;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public PromotionTypeEntity getNewPromotionTypeEntity() {
        return newPromotionTypeEntity;
    }
    public void setNewPromotionTypeEntity(PromotionTypeEntity newPromotionTypeEntity) {
        this.newPromotionTypeEntity = newPromotionTypeEntity;
    }
    public AdminEntity getNewAdminEntity() {
        return newAdminEntity;
    }
    public void setNewAdminEntity(AdminEntity newAdminEntity) {
        this.newAdminEntity = newAdminEntity;
    }

    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }

    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }
    public RoomTypeEntity getNewRoomTypeEntity() {
        return newRoomTypeEntity;
    }

    public void setNewRoomTypeEntity(RoomTypeEntity newRoomTypeEntity) {
        this.newRoomTypeEntity = newRoomTypeEntity;
    }


}