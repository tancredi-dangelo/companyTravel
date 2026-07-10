package tancredidangelo.companyTravel.travel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository {

    /// save, findById, findAll, deleteById


    List<Travel> findByBookingsId(Long id);

    List<Travel> findByStatus(String status);


}

