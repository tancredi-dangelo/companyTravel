package tancredidangelo.companyTravel.employee;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tancredidangelo.companyTravel.employee.employeeDTO.NewEmployeeDTO;
import tancredidangelo.companyTravel.employee.employeeDTO.UpdateEmployeeDTO;
import tancredidangelo.companyTravel.employee.employeeDTO.UpdateEmployeeEmailDTO;
import tancredidangelo.companyTravel.exceptions.BadRequestException;
import tancredidangelo.companyTravel.exceptions.InternalServerErrorException;
import tancredidangelo.companyTravel.exceptions.NotFoundException;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmployeeService {

    /// dependency injection
    private final EmployeeRepository employeeRepository;
    private final Cloudinary fileUploader;

    public EmployeeService(EmployeeRepository employeeRepository, Cloudinary fileUploader) {
        this.employeeRepository = employeeRepository;
        this.fileUploader = fileUploader;
    }


    /// ---------------------------------- methods -------------------------------------------------------

    // save
    public Employee saveEmployee(NewEmployeeDTO payload) {

        if (this.employeeRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("\nThis email is already in use.");
        }

        Employee newEmployee = new Employee(payload.name(), payload.surname(), payload.email());
        return this.employeeRepository.save(newEmployee);

    }


    // find by id
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("\nEmployee with id " + id + " not found."));
    }


    // find all
    public List<Employee> findAllEmployees() {

        List<Employee> all = this.employeeRepository.findAll();

        if (all.isEmpty()) {
            log.info("\nNo employees registered in database.");
            return List.of();
        }

        else return all;
    }


    // update by Id
    public Employee updateEmployeeById(Long id, UpdateEmployeeDTO payload) {

        Employee found = findEmployeeById(id);

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setUsername(payload.username());

        return this.employeeRepository.save(found);

    }



    // update email by id
    public Employee updateEmailById(Long id, UpdateEmployeeEmailDTO payload) {

        if (employeeRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("\nThis email is already in use");
        }

        Employee found = findEmployeeById(id);

        found.setEmail(payload.email());

        return this.employeeRepository.save(found);

    }


    // update avatarUrl by id
    public Employee updateAvatar(Long id, MultipartFile file) {

        // TODO: check for file related issues (eg. size, format ...)

        Employee found = findEmployeeById(id);

        try {
            // upload file on Cloudinary
            Map result = fileUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            log.info("\nAvatar uploaded successfully.");

            // get file url back from Cloudinary
            String url = (String) result.get("secure_url");

            // set the result url as "avatarUrl" of the author
            found.setAvatarUrl(url);
            log.info("User {}: Avatar updated!", id);

            return this.employeeRepository.save(found);

        } catch (IOException ex) {
            throw new InternalServerErrorException("Unknown Error: Failed to upload avatar image.");
        }
    }


}
