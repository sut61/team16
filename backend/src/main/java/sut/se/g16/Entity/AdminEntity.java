package sut.se.g16.Entity;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Table(name ="AdminEntity" +
        "")
public class AdminEntity {
    //@Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="Admin_seq",sequenceName="Admin_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Admin_seq")
    @Column(name = "adminID")
    @Id private @NonNull Long adminID;
    private String adminName;


    public Long adminID() {
        return adminID;
    }

    public void adminID(Long adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    public AdminEntity(){}
    public AdminEntity(String adminName) {  //constructor
        this.adminName = adminName;

    }


}
