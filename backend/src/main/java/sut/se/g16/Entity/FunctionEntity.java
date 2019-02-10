package sut.se.g16.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Function")
public class FunctionEntity {
    @Id
    @SequenceGenerator(name="function_seq",sequenceName="function_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="function_seq")
    @Column(name="functonId",unique = true, nullable = false)
    private @NotNull Long functonId;
    private @NotNull String functonName;

    public Long getFunctonId() {
        return functonId;
    }

    public void setFunctonId(Long functonId) {
        this.functonId = functonId;
    }

    public String getFunctonName() {
        return functonName;
    }

    public void setFunctonName(String functonName) {
        this.functonName = functonName;
    }

}