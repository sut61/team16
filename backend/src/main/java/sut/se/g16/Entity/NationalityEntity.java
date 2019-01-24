package sut.se.g16.Entity;
import  javax.persistence.*;
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
@Table (name = "NationalityEntity")
public class NationalityEntity {
    @Id
    @SequenceGenerator(name="nationalitySeq",sequenceName="nationalitySeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nationalitySeq")
    @Column(name="nationalityId",unique = true, nullable = false)
    private @NonNull Long nationalityId;
    private @NonNull String nationalityName;

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
