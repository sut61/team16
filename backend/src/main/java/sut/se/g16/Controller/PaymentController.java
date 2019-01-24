package sut.se.g16.Controller;
import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sut.se.g16.Entity.*;
import sut.se.g16.Repository.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<PaymentEntity> payment() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/payment/{name}")
    public PaymentEntity payment(@PathVariable String name) {
        PaymentEntity p = new PaymentEntity();
        return this.paymentRepository.save(p);

    }

}
