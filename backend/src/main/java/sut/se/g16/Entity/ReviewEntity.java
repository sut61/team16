package sut.se.g16.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
@NoArgsConstructor
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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ReservationRoomEntity.class)
	public ReservationRoomEntity newReservationRoomEntity;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
	@JoinColumn(name = "HotelId")
	private HotelEntity newHotelEntity;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
	@JoinColumn(name = "UserId")
	private CustomerEntity newCustomerEntity;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
	@JoinColumn(name = "RoomTypeId")
	private RoomTypeEntity newRoomTypeEntity;


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

	public ReservationRoomEntity getNewReservationRoomEntity() {
		return newReservationRoomEntity;
	}

	public void setNewReservationRoomEntity(ReservationRoomEntity newReservationRoomEntity) {
		this.newReservationRoomEntity = newReservationRoomEntity;
	}

	public HotelEntity getNewHotelEntity() {
		return newHotelEntity;
	}

	public void setNewHotelEntity(HotelEntity newHotelEntity) {
		this.newHotelEntity = newHotelEntity;
	}

	public CustomerEntity getNewCustomerEntity() {
		return newCustomerEntity;
	}

	public void setNewCustomerEntity(CustomerEntity newCustomerEntity) {
		this.newCustomerEntity = newCustomerEntity;
	}

	public RoomTypeEntity getNewRoomTypeEntity() {
		return newRoomTypeEntity;
	}
	
	public void setNewRoomTypeEntity(RoomTypeEntity newRoomTypeEntity) {
		this.newRoomTypeEntity = newRoomTypeEntity;
	}
}