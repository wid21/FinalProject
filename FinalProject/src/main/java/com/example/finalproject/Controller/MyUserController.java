package com.example.finalproject.Controller;

import com.example.finalproject.Dto.DtoCompany;
import com.example.finalproject.Dto.DtoCustomer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Repository.MyUserRepository;
import com.example.finalproject.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/myuser")
@RequiredArgsConstructor
@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/register-company")
    public ResponseEntity registerInNeedCompany(@Valid @RequestBody DtoCompany dtoCompany) {
        myUserService.registerCompany(dtoCompany);
        return ResponseEntity.status(200).body("registered");
    }
    @PostMapping("/register-customer")
    public ResponseEntity registerInNeedCompany(@Valid @RequestBody DtoCustomer dtoCustomer) {
        myUserService.registerCustomer(dtoCustomer);
        return ResponseEntity.status(200).body("registered");
    }
    @PostMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.status(200).body("login user!");
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout user!");

    }

}
