package tancredidangelo.companyTravel.entities.travel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tancredidangelo.companyTravel.entities.travel.travelDTO.NewTravelDTO;
import tancredidangelo.companyTravel.entities.travel.travelDTO.UpdateTravelDTO;

import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {


    /// dependency injection

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }


    /// ----------------------- requests ----------------------------------------------------------------


    /// GET ALL TRAVELS
    /// GET "[...](http://localhost:PORT/travels)" -> 200 OK
    @GetMapping
    public List<Travel> getTravels() {
        return this.travelService.findAllTravels();
    }


    /// SAVE TRAVEL
    /// POST "[...](http://localhost:PORT/travels)" -> 201 CREATED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Travel saveTravel(@RequestBody NewTravelDTO payload) {
        return this.travelService.saveTravel(payload);
    }


    /// GET TRAVEL BY ID
    /// GET "[...](http://localhost:PORT/travels/{travelId})" -> 200 OK
    @GetMapping("/{id}")
    public Travel getTravelById(@PathVariable Long id) {
        return this.travelService.findTravelById(id);
    }


    /// UPDATE TRAVEL
    /// PUT "[...](http://localhost:PORT/travels/{travelId})" -> 200 OK
    @PutMapping("/{id}")
    public Travel updateTravelById(@PathVariable Long id, @RequestBody UpdateTravelDTO payload) {
        return this.travelService.updateTravelById(id, payload);
    }

    /// DELETE TRAVEL
    /// DELETE "[...](http://localhost:PORT/travels/{travelId})" -> 200 OK
    @DeleteMapping("/{id}")
    public void deleteTravelById(@PathVariable Long id) {
        this.travelService.deleteTravelById(id);
    }


    /// DELETE ALL TRAVELS
    /// DELETE "[...](http://localhost:PORT/travels)" -> 200 OK
    @DeleteMapping
    public void deleteAllTravels() {
        this.travelService.deleteAllTravels();
    }


}


