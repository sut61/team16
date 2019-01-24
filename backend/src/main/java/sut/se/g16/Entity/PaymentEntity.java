package sut.se.g16.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Payment")
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PaymentEntity {
    @Id
    @SequenceGenerator(name = "bill_seq", sequenceName = "bill_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    @Column(name = "BillId", unique = true, nullable = false)
    private @NonNull Long billId;
    public PaymentEntity(){}

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long bill_id) {
        this.billId = bill_id;
    }

}
