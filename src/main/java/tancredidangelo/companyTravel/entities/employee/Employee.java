package tancredidangelo.companyTravel.entities.employee;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tancredidangelo.companyTravel.entities.booking.Booking;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "employees")
public class Employee {

    /// attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String avatarUrl;

    @OneToMany
    @JoinColumn(name = "employeeId")
    private List<Booking> bookings;


    /// constructor
    public Employee(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.username = name + "_" + surname;
        this.email = email;
        this.avatarUrl = "https://picsum.photos/200/200";
    }


    /// to string
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
