package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "EmployeeEntity")
public class EmployeeEntity {
    @Id
    @SequenceGenerator(name="employeeSeq",sequenceName="employeeSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employeeSeq")
    @Column(name="employeeId",unique = true, nullable = false)
     private @NotNull Long employeeId;

     @Column(unique = true)
     @Pattern (regexp = "[A-Z]\\d{4}")
     private @NotNull String employeeUserName;

     @Size(min = 8, max = 16)
     @Pattern (regexp = "[A-Za-z0-9]\\w+")
     private @NotNull String employeePassword;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Many To One with HotelEntity
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
    private HotelEntity newHotelEntity;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set */

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* get set many to one*/

    public HotelEntity getNewHotelEntity() {
        return newHotelEntity;
    }

    public void setNewHotelEntity(HotelEntity newHotelEntity) {
        this.newHotelEntity = newHotelEntity;
    }

}