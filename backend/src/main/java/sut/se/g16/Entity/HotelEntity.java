package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Data @Getter @Setter
@ToString @NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Hotel")
public class HotelEntity {
    @Id
    @SequenceGenerator(name="hotel_seq",sequenceName="hotel_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hotel_seq")
    @Column(name="hotelId",unique = true, nullable = false)
    private @NotNull Long hotelId;
    @Column (unique = true)
    private @NotNull String hotelNameEng;
    private @NotNull Long villageNo;
    private @NotNull String houseNo;
    private @NotNull String building ;
    private @NotNull String village;
    private @NotNull String alleyLane;
    private @NotNull String road;
    private @NotNull String subDistrictSubArea;
    private @NotNull String districtArea;
    @Size(min = 5, max = 5)
    @Pattern (regexp = "[1-9][0-9]+")
    private @NotNull String postCode;

    @Size(min = 10, max = 10)
    @Pattern (regexp = "[0][0-9]+")
    private @NotNull String mobilePhone;

    @Size(min = 9, max = 9)
    @Pattern (regexp = "[0][0-9]+")
    private @NotNull String phone;

    @Size(min = 9, max = 9)
    @Pattern (regexp = "[0][0-9]+")
    private @NotNull String fax;

    //Many To One with ProvinceEntity
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProvinceEntity.class)
    private ProvinceEntity newProvinceEntity;

    //One To One with MemberHotelEntity
    @OneToOne(fetch = FetchType.EAGER, targetEntity = MemberHotelEntity.class)
    @JoinColumn(name = "memberHotelId")
    private MemberHotelEntity newMemberHotelEntity;

    public MemberHotelEntity getMemberHoteEntity() {
        return newMemberHotelEntity;
    }

    public void setMemberHotelEntity(MemberHotelEntity memberHotel) {
        this.newMemberHotelEntity = memberHotel;
    }

    public ProvinceEntity getProvinceEntity() {
        return newProvinceEntity;
    }

    public void setProvinceEntity(ProvinceEntity provinceEntity) {
        this.newProvinceEntity = provinceEntity;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelNameEng() {
        return hotelNameEng;
    }

    public void setHotelNameEng(String hotelNameEng) {
        this.hotelNameEng = hotelNameEng;
    }

    public Long getVillageNo() {
        return villageNo;
    }

    public void setVillageNo(Long villageNo) {
        this.villageNo = villageNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAlleyLane() {
        return alleyLane;
    }

    public void setAlleyLane(String alleyLane) {
        this.alleyLane = alleyLane;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSubDistrictSubArea() {
        return subDistrictSubArea;
    }

    public void setSubDistrictSubArea(String subDistrictSubArea) {
        this.subDistrictSubArea = subDistrictSubArea;
    }

    public String getDistrictArea() {
        return districtArea;
    }

    public void setDistrictArea(String districtArea) {
        this.districtArea = districtArea;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public ProvinceEntity getNewProvinceEntity() {
        return newProvinceEntity;
    }

    public void setNewProvinceEntity(ProvinceEntity newProvinceEntity) {
        this.newProvinceEntity = newProvinceEntity;
    }

    public MemberHotelEntity getNewMemberHotelEntity() {
        return newMemberHotelEntity;
    }

    public void setNewMemberHotelEntity(MemberHotelEntity newMemberHotelEntity) {
        this.newMemberHotelEntity = newMemberHotelEntity;
    }
}
