package tancredidangelo.companyTravel.entities.booking;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "bookings")
public class Booking {

    /// attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private Long employeeId;

    @Column
    private Long TravelId;

    @Column
    private LocalDate date;

    @Column
    private String notes;


    /// constructor
    public Booking(Long employeeId, Long travelId, LocalDate date, String notes) {
        this.employeeId = employeeId;
        this.TravelId = travelId;
        this.date = date;
        this.notes = notes;
    }


    /// to string
    @Override
    public String toString() {
        return "Booking{" +
                "employeeId=" + employeeId +
                ", TravelId=" + TravelId +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}
