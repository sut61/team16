package sut.se.g16.Controller;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodStockController {
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private FoodTypeRepository foodTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private FoodStockRepository foodStockRepository;
    @Autowired
    private FoodStockListManyToManyRepository foodStockListManyToManyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public FoodStockController(EmployeeRepository employeeRepository, ListRepository listRepository,
            FoodTypeRepository foodTypeRepository, HotelRepository hotelRepository,
            FoodStockRepository foodStockRepository,
            FoodStockListManyToManyRepository foodStockListManyToManyRepository) {
        this.employeeRepository = employeeRepository;
        this.listRepository = listRepository;
        this.foodTypeRepository = foodTypeRepository;
        this.hotelRepository = hotelRepository;
        this.foodStockRepository = foodStockRepository;
        this.foodStockListManyToManyRepository = foodStockListManyToManyRepository;
    }

    /* Employee */
    @GetMapping("/employees")
    public Collection<EmployeeEntity> employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/employee/{username}/{password}")
    public EmployeeEntity newEmployee(@PathVariable String username, @PathVariable String password) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeUserName(username);
        employee.setEmployeePassword(password);
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/login/{username}/{password}")
    public Boolean isLogin(@PathVariable String username, @PathVariable String password) {
        Long em = employeeRepository.findEmployeeIdByUserNameAndPassword(username, password);
        if (em != null)
            return true;
        return false;
    }

    /* Food Stock */
    @GetMapping(path = "/foodstocks")
    public Collection<FoodStockEntity> foodStock() {
        return foodStockRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/foodstock/{type}")
    public Boolean isType(@PathVariable String type) {
        if(type.equals("Drink")){
            return true;
        }
        else{
            return false;
        }
    }

    @GetMapping(path = "/foodstock/{hotel}/{foodtype}/{stockname}/{price}/{amount}/{employee}")
    public Boolean newFoodStock(@PathVariable String hotel, @PathVariable String foodtype,
            @PathVariable String stockname, @PathVariable Long price, @PathVariable Long amount,
            @PathVariable String employee) {

        EmployeeEntity em = employeeRepository.findByUser(employee);

        HotelEntity ho = hotelRepository.findByName(hotel);
        Long hotelId = hotelRepository.findHotelIdByName(hotel);

        FoodTypeEntity ft = foodTypeRepository.findByName(foodtype);

        FoodStockEntity foodstock = new FoodStockEntity();

        foodstock.setNewHotelEntity(ho);
        foodstock.setNewFoodTypeEntity(ft);
        foodstock.setStockName(stockname);
        foodstock.setPrice(price);
        foodstock.setAmount(amount);
        foodstock.setNewEmployeeEntity(em);

        foodStockRepository.save(foodstock);
        System.out.print("----------------------------------"+ stockname);
        Long listId = listRepository.findListIdByName(stockname);
        if (listId == null) {
            ListEntity list = new ListEntity();
            
            list.setListName(stockname);
            list.setNewFoodTypeEntity(ft);
            list.setTotalAmount(amount);
            list.setListPrice(price);
            System.out.println("**************  "+ price);
            listRepository.save(list);

            FoodStockListManyToManyEntity fslmtm = new FoodStockListManyToManyEntity();
            fslmtm.setNewFoodStockEntity(foodstock);
            fslmtm.setNewListEntity(list);
            foodStockListManyToManyRepository.save(fslmtm);
            return true;
        } else {
            ListEntity listEntity = listRepository.findListByName(stockname);
            if(listEntity.getListName().equals(stockname)){
                listEntity.setTotalAmount(listEntity.getTotalAmount() + amount);
                listEntity.setListPrice(price);
                listRepository.save(listEntity);

                FoodStockListManyToManyEntity fslmtm = new FoodStockListManyToManyEntity();
                fslmtm.setNewFoodStockEntity(foodstock);
                fslmtm.setNewListEntity(listEntity);
                foodStockListManyToManyRepository.save(fslmtm);

            }else{

                listEntity.setTotalAmount(1L);
                listEntity.setListPrice(price);
                listRepository.save(listEntity);
                FoodStockListManyToManyEntity fslmtm = new FoodStockListManyToManyEntity();
                fslmtm.setNewFoodStockEntity(foodstock);
                fslmtm.setNewListEntity(listEntity);
                foodStockListManyToManyRepository.save(fslmtm);

            }

            return true;
        }

    }

}
