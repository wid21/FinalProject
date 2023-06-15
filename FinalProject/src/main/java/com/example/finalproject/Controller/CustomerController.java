package com.example.finalproject.Controller;

import com.example.finalproject.Dto.DtoPoints;
import com.example.finalproject.Model.Company;
import com.example.finalproject.Model.Customer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {

private final CustomerService customerService;


//    @PostMapping ("/add-points")
//    public ResponseEntity addPoints(@AuthenticationPrincipal MyUser customer){
//        int points = customerService.addPoints(customer);
//        return ResponseEntity.status(200).body("points added  : " + points);
//
//    }
//    @PostMapping("/login")
//    public ResponseEntity login() {
//        return ResponseEntity.status(200).body("login user!");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity logout() {
//        return ResponseEntity.status(200).body("logout user!");
//    }
//
//    @GetMapping("/get-customer")
//    public ResponseEntity getcustomer() {
//        List<Customer> customers = customerService.getCustomer();
//        return ResponseEntity.status(200).body(customers);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser user, @RequestBody Customer customer,@PathVariable Integer id) {
//        customerService.updateCustomer(user.getId(), customer,id);
//        return ResponseEntity.status(200).body("user updated");
//    }
//    @DeleteMapping("/delete/{customerId}")
//    public ResponseEntity deleteCompany(@AuthenticationPrincipal MyUser user,@PathVariable Integer customerId){
//        customerService.deleteCustomer(customerId);
//        return ResponseEntity.status(200).body("company deleted");
//    }



}
