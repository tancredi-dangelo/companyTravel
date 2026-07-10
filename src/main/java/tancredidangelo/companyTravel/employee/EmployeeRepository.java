package tancredidangelo.companyTravel.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /// save, findById, findAll, deleteById

    List<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

}
