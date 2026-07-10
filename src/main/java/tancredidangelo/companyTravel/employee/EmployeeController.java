package tancredidangelo.companyTravel.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tancredidangelo.companyTravel.employee.employeeDTO.NewEmployeeDTO;
import tancredidangelo.companyTravel.employee.employeeDTO.UpdateEmployeeDTO;
import tancredidangelo.companyTravel.employee.employeeDTO.UpdateEmployeeEmailDTO;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    /// dependency injection

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /// ----------------------- requests ----------------------------------------------------------------


    /// GET ALL EMPLOYEES
    /// GET "[...](http://localhost:PORT/employees)" -> 200 OK
    @GetMapping
    public List<Employee> getEmployees() {
        return this.employeeService.findAllEmployees();
    }


    /// SAVE EMPLOYEE
    /// POST "[...](http://localhost:PORT/employees/{payload})" -> 201 CREATED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody NewEmployeeDTO payload) {
        return this.employeeService.saveEmployee(payload);
    }


    /// GET EMPLOYEE BY ID
    /// GET "[...](http://localhost:PORT/employees/{employeeId})" -> 200 OK
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return this.employeeService.findEmployeeById(id);

    }


    /// UPDATE EMPLOYEE
    /// PUT "[...](http://localhost:PORT/employees/{employeeId})" -> 200 OK
    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody UpdateEmployeeDTO payload) {
        return this.employeeService.updateEmployeeById(id, payload);
    }


    /// UPDATE EMAIL
    /// PATCH "[...](http://localhost:PORT/employees/{employeeId})" -> 200 OK
    @PatchMapping("/{id}")
    public Employee updateEmployeeEmailById(@PathVariable Long id, @RequestBody UpdateEmployeeEmailDTO payload) {
        return this.employeeService.updateEmailById(id, payload);
    }


    /// DELETE EMPLOYEE
    /// DELETE "[...](http://localhost:PORT/employees/{employeeId})" -> 200 OK
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        this.employeeService.deleteEmployeeById(id);
    }


    /// UPDATE AVATAR
    /// PATCH "[...](http://localhost:PORT/employees/{employeeId})" -> 200 OK
    @PatchMapping("/{id}/avatar")
    public Employee updateEmployeeAvatar(@PathVariable Long id, @RequestParam("profile_picture") MultipartFile file) {
        return this.employeeService.updateAvatar(id, file);
    }






}
