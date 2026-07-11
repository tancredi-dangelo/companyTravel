package tancredidangelo.companyTravel.entities.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /// save, findById, findAll, deleteById

    List<Employee> findByEmail(String email);

    List<Employee> findByBookingsId(Long id);

    boolean existsByEmail(String email);

}
