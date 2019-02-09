package sut.se.g16.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class StatusPaymentEntity {
   
    @Id  
    @SequenceGenerator(name="statusPayment_seq",sequenceName="statusPayment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="statusPayment_seq")  
    @Column(name="statusPaymentId",unique = true, nullable = false)
    private @NotNull Long statusPaymentId;
    private @NotNull String statusPaymentTypeName;


    public Long getStatusPaymentId() {
        return statusPaymentId;
    }


    public void setStatusPaymentId(Long statusPaymentId) {
        this.statusPaymentId = statusPaymentId;
    }


    public String getStatusPaymentTypeName() {
        return statusPaymentTypeName;
    }

    public void setStatusPaymentTypeName(String statusPaymentTypeName) {
        this.statusPaymentTypeName = statusPaymentTypeName;
    }

   
}