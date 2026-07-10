package tancredidangelo.companyTravel.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tancredidangelo.companyTravel.booking.bookingDTO.NewBookingDTO;
import tancredidangelo.companyTravel.booking.bookingDTO.UpdateBookingDTO;
import tancredidangelo.companyTravel.employee.EmployeeService;
import tancredidangelo.companyTravel.exceptions.BadRequestException;
import tancredidangelo.companyTravel.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookingService {

    /// dependency injection

    private final BookingRepository bookingRepository;
    private final EmployeeService employeeService;

    public BookingService(BookingRepository bookingRepository, EmployeeService employeeService) {
        this.bookingRepository = bookingRepository;
        this.employeeService = employeeService;
    }



    /// ------------------------ methods -----------------------------------------------------------

    // save booking
    public Booking saveBooking(NewBookingDTO payload) {

        // check if employee has other bookings for the same date
        List<Booking> employeeBookings = this.employeeService.findEmployeeById(payload.employeeId()).getBookings();
        if (employeeBookings.stream().anyMatch(booking -> booking.getDate().isEqual(payload.date()))) {
            throw new BadRequestException("This user already has a travel booked for that day. Please choose another day.");
        }

        Booking newBooking = new Booking(payload.employeeId(), payload.travelId(), payload.date(), payload.notes());

        Booking saved = this.bookingRepository.save(newBooking);

        log.info("Booking successfully created. Id: {}", saved.getId());

        return saved;
    }


    // find all bookings
    public List<Booking> findAllEmployees() {
        return this.bookingRepository.findAll();
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
    // update booking by id
    public Booking updateBookingById(Long id, UpdateBookingDTO payload) {

        Booking found = findBookingById(id);

        found.setDate(payload.date());
        found.setNotes(payload.notes());

        log.info("\nBooking updated!");
        System.out.println(found);

        return this.bookingRepository.save(found);
    }


    // update booking
    public Booking updateBooking(Booking booking, UpdateBookingDTO payload) {

        booking.setDate(payload.date());
        booking.setNotes(payload.notes());

        log.info("\nBooking updated!");
        System.out.println(booking);

        return this.bookingRepository.save(booking);
    }


}
