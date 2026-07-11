package tancredidangelo.companyTravel.entities.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tancredidangelo.companyTravel.entities.booking.bookingDTO.NewBookingDTO;
import tancredidangelo.companyTravel.entities.booking.bookingDTO.UpdateBookingDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {


    /// dependency injection

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    /// ----------------------- requests ----------------------------------------------------------------


    /// GET ALL BOOKINGS
    /// GET "[...](http://localhost:PORT/bookings)" -> 200 OK
    @GetMapping
    public List<Booking> getBookings() {
        return this.bookingService.findAllBookings();
    }


    /// SAVE BOOKING
    /// POST "[...](http://localhost:PORT/bookings)" -> 201 CREATED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody NewBookingDTO payload) {
        return this.bookingService.saveBooking(payload);
    }


    /// GET BOOKING BY ID
    /// GET "[...](http://localhost:PORT/bookings/{bookingId})" -> 200 OK
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return this.bookingService.findBookingById(id);
    }


    /// GET BOOKINGS BY EMPLOYEE ID
    /// GET "[...](http://localhost:PORT/bookings/employee/{employeeId})" -> 200 OK
    @GetMapping("/employee/{employeeId}")
    public List<Booking> getBookingsByEmployeeId(@PathVariable Long employeeId) {
        return this.bookingService.findByEmployeeId(employeeId);
    }


    /// GET BOOKINGS BY TRAVEL ID
    /// GET "[...](http://localhost:PORT/bookings/travel/{travelId})" -> 200 OK
    @GetMapping("/travel/{travelId}")
    public List<Booking> getBookingsByTravelId(@PathVariable Long travelId) {
        return this.bookingService.findByTravelId(travelId);
    }


    /// GET BOOKINGS BY DATE
    /// GET "[...](http://localhost:PORT/bookings/date?date=2026-08-01)" -> 200 OK
    @GetMapping("/date")
    public List<Booking> getBookingsByDate(@RequestParam LocalDate date) {
        return this.bookingService.findByDate(date);
    }


    /// UPDATE BOOKING
    /// PUT "[...](http://localhost:PORT/bookings/{bookingId})" -> 200 OK
    @PutMapping("/{id}")
    public Booking updateBookingById(@PathVariable Long id, @RequestBody UpdateBookingDTO payload) {
        return this.bookingService.updateBookingById(id, payload);
    }


    /// DELETE BOOKING
    /// DELETE "[...](http://localhost:PORT/bookings/{bookingId})" -> 200 OK
    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable Long id) {
        this.bookingService.deleteBookingById(id);
    }


}