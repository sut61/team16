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
@Table (name = "TitleNameEntity")
public class TitleNameEntity {
    @Id
    @SequenceGenerator(name="titlenameSeq",sequenceName="titlenameSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="titlenameSeq")
    @Column(name="titlenameId",unique = true, nullable = false)
    private @NonNull Long titlenameId;
    private @NonNull String titlenameType;

    public Long getTitlenameId() {
        return titlenameId;
    }

    public void setTitlenameId(Long titlenameId) {
        this.titlenameId = titlenameId;
    }

    public String getTitlenameType() {
        return titlenameType;
    }

    public void setTitlenameType(String titlenameType) {
        this.titlenameType = titlenameType;
    }
}
