package tancredidangelo.companyTravel.travel;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tancredidangelo.companyTravel.booking.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "travels")
public class Travel {

    /// attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private String destination;

    @Column
    private LocalDate date;

    @Column
    private String state;

    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<Booking> bookings;


    /// constructor
    public Travel(String destination, LocalDate date) {
        this.destination = destination;
        this.date = date;
        this.state = "Programmed";
        this.bookings = new ArrayList<>();
    }


    /// to string
    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
