package sut.se.g16.Entity;
import  javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table (name = "CustomerEntity")
public class CustomerEntity {
    @Id
    @SequenceGenerator(name="customerSeq",sequenceName="customerSeq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSeq")
    @Column(name="customerId",unique = true, nullable = false)
    private @NonNull Long customerId;
    private @NonNull String customerName;
    private @NonNull Long customerIdcrad;
    private @NonNull String customerAddress;
    private @NonNull String customerEmail;
    private @NonNull String customerPhone;
    private @NonNull String customerUsername;
    private @NonNull String customerPassword;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleNameEntity.class)
    @JoinColumn(name = "titlenameId")
    @JsonIgnore
    private TitleNameEntity titleName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SexEntity.class)
    @JoinColumn(name = "sexId")
    @JsonIgnore
    private SexEntity sex;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = NationalityEntity.class)
    @JoinColumn(name = "nationalitycodeId")
    @JsonIgnore
    private NationalityEntity nationality;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerIdcrad() {
        return customerIdcrad;
    }

    public void setCustomerIdcrad(Long customerIdcrad) {
        this.customerIdcrad = customerIdcrad;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public TitleNameEntity getTitleName() {
        return titleName;
    }

    public void setTitleName(TitleNameEntity titleName) {
        this.titleName = titleName;
    }

    public SexEntity getSex() {
        return sex;
    }

    public void setSex(SexEntity sex) {
        this.sex = sex;
    }

    public NationalityEntity getNationality() {
        return nationality;
    }

    public void setNationality(NationalityEntity nationality) {
        this.nationality = nationality;
    }
}

