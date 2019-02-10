package sut.se.g16.Entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Table(name ="BankEntity")
public class BankEntity {
    @Id
    @SequenceGenerator(name = "bankId_seq", sequenceName = "bankId_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankId_seq")
    private @NotNull Long bankId;
    private @NotNull String bankName;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public BankEntity() {
    }

    public BankEntity(String bankName) {

        this.bankName = bankName;

    }
}
