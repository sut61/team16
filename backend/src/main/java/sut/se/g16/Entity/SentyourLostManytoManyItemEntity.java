package sut.se.g16.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Setter
@Getter
@Table(name = "SentItem")
@SpringBootApplication
public class SentyourLostManytoManyItemEntity {
    @Id
    @SequenceGenerator(name = "SentsItem_seq", sequenceName = "SentsItem_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SentsItem_seq")
    @Column(name = "SentsItemId", unique = true, nullable = false)
    @NotNull private Long sentItemId;

    @ManyToOne(fetch=FetchType.EAGER,targetEntity=SentYourLostEntity.class)
    @JoinColumn(name="sentId")
    private SentYourLostEntity newSentYourLostEntity;

    @ManyToOne(fetch=FetchType.EAGER,targetEntity=ItemEntity.class)
    @JoinColumn(name="itemId")
    private ItemEntity newItemEntity;

    public Long getSentItem() {
        return sentItemId;
    }

    public void setSentItem(Long sentItemId) {
        this.sentItemId = sentItemId;
    }

    public SentYourLostEntity getNewSentYourLostEntity() {
        return newSentYourLostEntity;
    }

    public void setNewSentYourLostEntity(SentYourLostEntity newSentYourLostEntity) {
        this.newSentYourLostEntity = newSentYourLostEntity;
    }

    public ItemEntity getNewItemEntity() {
        return newItemEntity;
    }

    public void setNewItemEntity(ItemEntity newItemEntity) {
        this.newItemEntity = newItemEntity;
    }
}