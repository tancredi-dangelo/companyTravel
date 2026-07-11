package tancredidangelo.companyTravel.entities.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /// save, findById, findAll, deleteById

    List<Booking> findByEmployeeId(Long employeeId);

    List<Booking> findByTravelId(Long travelId);

    List<Booking> findByDate(LocalDate date);

}
