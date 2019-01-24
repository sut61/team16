package sut.se.g16.Entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Province")
public class ProvinceEntity {
    @Id
    @SequenceGenerator(name="province_seq",sequenceName="province_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="province_seq")
    @Column(name="provinceId",unique = true, nullable = false)
    private Long provinceId;
    private @NonNull String provinceName;
    
    public Long getProvinceId(){
        return this.provinceId;
    }

    public void setProvinceName(String name){
        this.provinceName = name;
    }

    public String getProvinceName(){
        return this.provinceName;
    }
}
