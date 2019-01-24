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
@Table (name = "SexEntity")
public class SexEntity {
    @Id
    @SequenceGenerator(name="sexSeq",sequenceName="sexSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sexSeq")
    @Column(name="sexId",unique = true, nullable = false)
    private @NonNull Long sexId;
    private @NonNull String sexType;

    public Long getSexId() {
        return sexId;
    }

    public void setSexId(Long sexId) {
        this.sexId = sexId;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }
}
