package sut.se.g16.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "Review")
@SpringBootApplication
public class ReviewEntity {
	@Id
	@SequenceGenerator(name = "Reviews_seq", sequenceName = "Reviews_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reviews_seq")
	@Column(name = "ReviewsId", unique = true, nullable = false)
	private Long reviewId;
	private String comment;
	private float score;
	@OneToOne(cascade = CascadeType.ALL)
	public PaymentEntity paymentEntity;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
	@JoinColumn(name = "HotelId")
	private HotelEntity hotelNameEng;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
	@JoinColumn(name = "UserId")
	private CustomerEntity user;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
	@JoinColumn(name = "RoomTypeId")
	private RoomTypeEntity roomtype;

	public ReviewEntity() {
	}

	public ReviewEntity(String comment, float score, CustomerEntity user, RoomTypeEntity roomType, HotelEntity hotel,
			PaymentEntity paymentEntity) {
		this.paymentEntity = paymentEntity;
		this.comment = comment;
		this.user = user;
		this.score = score;
		this.roomtype = roomType;
		this.hotelNameEng = hotel;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public PaymentEntity getPaymentEntity() {
		return paymentEntity;
	}

	public void setPaymentEntity(PaymentEntity paymentEntity) {
		this.paymentEntity = paymentEntity;
	}

	public HotelEntity getHotelNameEng() {
		return hotelNameEng;
	}

	public void setHotelNameEng(HotelEntity hotelNameEng) {
		this.hotelNameEng = hotelNameEng;
	}

	public CustomerEntity getUser() {
		return user;
	}

	public void setUser(CustomerEntity user) {
		this.user = user;
	}

	public RoomTypeEntity getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomTypeEntity roomtype) {
		this.roomtype = roomtype;
	}
}