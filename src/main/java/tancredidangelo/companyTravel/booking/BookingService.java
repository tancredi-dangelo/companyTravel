package tancredidangelo.companyTravel.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tancredidangelo.companyTravel.booking.bookingDTO.NewBookingDTO;
import tancredidangelo.companyTravel.booking.bookingDTO.UpdateBookingDTO;
import tancredidangelo.companyTravel.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }



    /// ------------------------ methods -----------------------------------------------------------

    // save booking
    public Booking saveBooking(NewBookingDTO payload) {

        Booking newBooking = new Booking(payload.employeeId(), payload.travelId(), payload.date(), payload.notes());

        Booking saved = this.bookingRepository.save(newBooking);

        log.info("Booking successfully created. Id: {}", saved.getId());

        return saved;
    }


    // find by id
    public Booking findBookingById(Long id) {
        return this.bookingRepository.findById(id).orElseThrow(()-> new NotFoundException("\nBooking not found."));
    }


    // find by employee id
    public List<Booking> findByEmployeeId(Long employeeId) {
        return this.bookingRepository.findByEmployeeId(employeeId);
    }


    // find by travel id
    public List<Booking> findByTravelId(Long travelId) {
        return this.bookingRepository.findByTravelId(travelId);
    }


    // find by date
    public List<Booking> findByDate(LocalDate date) {
        return this.bookingRepository.findByDate(date);
    }


    /// TODO: update booking should depend on Travel:
    /// TODO: travel date changes -> booking date changes OR it's prompted to be canceled
    // update booking
    public Booking updateBookingById(Long id, UpdateBookingDTO payload) {

        Booking found = findBookingById(id);

        found.setDate(payload.date());
        found.setNotes(payload.notes());

        log.info("\nBooking updated!");
        System.out.println(found);

        return this.bookingRepository.save(found);
    }


}
