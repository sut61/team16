package sut.se.g16.Entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDate;
//import sut.se.g16.Entity.*;

@Entity
@Data @Getter @Setter
@ToString @NoArgsConstructor
@EqualsAndHashCode
public class ReservationRoomEntity {
    @Id
    @SequenceGenerator(name="resevationRoomId_seq",sequenceName="resevationRoomId_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="resevationRoomId_seq")  
    private @NonNull Long reservationRoomId;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "hotelId")
    private HotelEntity hotelEntity;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "customerId")
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "promotionId")
    private PromotionEntity promotionEntity;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name= "roomTypeId")
    private RoomTypeEntity roomTypeEntity;

    private @NonNull Date dateIn;
    private @NonNull Date dateOut;
    private @NonNull Double totalPriceReservationRoom;
    

}