package tancredidangelo.companyTravel.travel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tancredidangelo.companyTravel.booking.Booking;
import tancredidangelo.companyTravel.booking.BookingService;
import tancredidangelo.companyTravel.booking.bookingDTO.UpdateBookingDTO;
import tancredidangelo.companyTravel.exceptions.NotFoundException;
import tancredidangelo.companyTravel.travel.travelDTO.NewTravelDTO;
import tancredidangelo.companyTravel.travel.travelDTO.UpdateTravelDTO;

import java.util.List;

@Service
@Slf4j
public class TravelService {

    /// dependency injection

    private final TravelRepository travelRepository;
    private final BookingService bookingService;

    public TravelService(TravelRepository travelRepository, BookingService bookingService) {
        this.travelRepository = travelRepository;
        this.bookingService = bookingService;
    }



    /// --------------------------- methods ------------------------------------------

    // save travel
    public Travel saveTravel(NewTravelDTO payload) {

        Travel newTravel = new Travel(payload.destination(), payload.date());

        Travel saved = this.travelRepository.save(newTravel);

        log.info("\nTravel option created. Id: {}", saved.getId());
        return saved;
    }



    // find by id
    public Travel findTravelById(Long id) {
        return this.travelRepository.findById(id).orElseThrow(()-> new NotFoundException("\nTravel not found."));
    }


    // find all
    public List<Travel> findAllTravels() {
        return this.travelRepository.findAll();
    }


    // update travel by id
    public Travel updateTravelById(Long id, UpdateTravelDTO payload) {

        Travel found = findTravelById(id);

        found.setDestination(payload.destination());
        found.setDate(payload.date());

        // updating travel means updating the relative bookings
        List<Booking> relatedBookings = this.bookingService.findByTravelId(id);

        relatedBookings.forEach(booking -> {
            UpdateBookingDTO bookingUpdate = new UpdateBookingDTO(payload.date(), booking.getNotes());
            this.bookingService.updateBooking(booking, bookingUpdate);
        });

        return found;
    }


    // update travel








}
