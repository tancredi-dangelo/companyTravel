package tancredidangelo.companyTravel.entities.travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    /// save, findById, findAll, deleteById

    List<Travel> findByBookingsId(Long id);

    List<Travel> findByStatus(String status);


}

