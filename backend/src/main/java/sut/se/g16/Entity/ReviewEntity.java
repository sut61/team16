package sut.se.g16.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	private @NotNull Long reviewId;
	@Size(min=5,max=200)
	@Pattern(regexp="[A-Za-z]*")
	private  String comment;
	
	@Size (min = 3,max = 200)
	@Pattern(regexp="[A-Za-z]*")
	private  String problem;

	@Positive
	private float score;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ReservationRoomEntity.class)
	@JoinColumn(name="reservationRoomId")
	public ReservationRoomEntity newReservationRoomEntity;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = HotelEntity.class)
	@JoinColumn(name = "HotelId")
	private HotelEntity newHotelEntity;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
	@JoinColumn(name = "UserId")
	private CustomerEntity newCustomerEntity;

	@NotNull
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

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
}