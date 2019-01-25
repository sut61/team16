package sut.se.g16.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import sut.se.g16.Repository.ProvinceRepository;
import sut.se.g16.Entity.ProvinceEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProvinceController{
    private ProvinceRepository provinceRepository;
    public ProvinceController(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }
    @GetMapping("/provinces")
    public Collection<ProvinceEntity> provinces() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/province/{name}")
    public ProvinceEntity newProvince(@PathVariable String name) {
        ProvinceEntity p = new ProvinceEntity();
        p.setProvinceName(name);
        return this.provinceRepository.save(p);
    }
}