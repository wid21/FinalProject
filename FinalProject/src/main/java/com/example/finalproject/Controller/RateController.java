package com.example.finalproject.Controller;

import com.example.finalproject.Dto.DtoRate;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/rate")
@RequiredArgsConstructor
@RestController
public class RateController {
private final RateService rateService;

//    @PostMapping("/add-rate/{bookingId}")
//    public ResponseEntity add(@AuthenticationPrincipal MyUser customer, @RequestBody DtoRate rate ,Integer bookingId){
//        rateService.add(customer.getId(),rate,bookingId);
//        return ResponseEntity.status(200).body("cart added");
//    }
}
