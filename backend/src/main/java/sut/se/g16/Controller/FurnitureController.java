package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import sut.se.g16.Entity.FurnitureEntity;
import sut.se.g16.Repository.FurnitureRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FurnitureController {
    private FurnitureRepository furnitureRepository;
    public FurnitureController(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }
    @GetMapping("/furniture/{name}")
    public FurnitureEntity newFurniture(@PathVariable final String  name){
        FurnitureEntity set = new FurnitureEntity();
        set.setFurnitureName(name);
        return furnitureRepository.save(set);

    }
    @GetMapping("/furnitures")
    public Collection<FurnitureEntity> Furniture() {
        return furnitureRepository.findAll().stream()
                .collect(Collectors.toList());
    }
}
