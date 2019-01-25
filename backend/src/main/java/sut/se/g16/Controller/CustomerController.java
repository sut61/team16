package sut.se.g16.Controller;
import org.springframework.web.bind.annotation.*;
import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    @Autowired
    private NationalityRepository nationalityRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private TitleNameRepository titleNameRepository;

    public CustomerController(CustomerRepository customerRepository , NationalityRepository nationalityRepository,
                             SexRepository sexRepository, TitleNameRepository titleNameRepository){
        this.nationalityRepository = nationalityRepository;
        this.customerRepository = customerRepository;
        this.sexRepository = sexRepository;
        this.titleNameRepository = titleNameRepository;
    }

    @GetMapping(path = "/national", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<NationalityEntity> NationalityEntity() {
        return nationalityRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CustomerEntity> CustomerEntity() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/sex", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SexEntity> SexEntity() {
        return sexRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/title", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<TitleNameEntity> TitleNameEntity() {
        return titleNameRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/customer/{emailInput}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerEntity email(@PathVariable String emailInput) {
        CustomerEntity emails = customerRepository.findBycustomerEmail(emailInput);
        System.out.println(emailInput);
        System.out.println(emails);
        return emails;
    }

    @GetMapping(path = "/lastRecord", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerEntity getRecordcustomer() {
        return customerRepository.findByrecord();
    }


    @PostMapping("/customer/{titlenameTypeSelect}/{nametInput}/{sexTypeSelect}/{idcardInput}/{addressInput}/{nationalityNameSelect}/{emailInput}/{phoneInput}/{userInput}/{passwordInput}")
    public CustomerEntity customerEntity(@PathVariable String titlenameTypeSelect, @PathVariable String nametInput,
                             @PathVariable String sexTypeSelect, @PathVariable Long idcardInput, @PathVariable String addressInput, @PathVariable String nationalityNameSelect,
                             @PathVariable String emailInput, @PathVariable String phoneInput, @PathVariable String userInput, @PathVariable String passwordInput){
        CustomerEntity customerEntity = new CustomerEntity();
        TitleNameEntity title = titleNameRepository.findBytitlenameType(titlenameTypeSelect);
        SexEntity sex = sexRepository.findBysexType(sexTypeSelect);
        NationalityEntity nationality = nationalityRepository.findBynationalityName(nationalityNameSelect);

        customerEntity.setSex(sex);
        customerEntity.setTitleName(title);
        customerEntity.setNationality(nationality);
        customerEntity.setCustomerName(nametInput);
        customerEntity.setCustomerIdcrad(idcardInput);
        customerEntity.setCustomerAddress(addressInput);
        customerEntity.setCustomerEmail(emailInput);
        customerEntity.setCustomerPhone(phoneInput);
        customerEntity.setCustomerUsername(userInput);
        customerEntity.setCustomerPassword(passwordInput);

        return customerRepository.save(customerEntity);
    }


    @GetMapping(path = "/customer/{username}/{password}")
    public Boolean login(@PathVariable String username, @PathVariable String password) {
        Long customerId = customerRepository.findCustomerIdByUserNameAndPassword(username,password);
        if(customerId != null)
            return true;

        return false;
    }
      

}

