package com.example.finalproject.Controller;

import com.example.finalproject.Model.Booking;
import com.example.finalproject.Model.Company;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Staff;
import com.example.finalproject.Service.BookingService;
import com.example.finalproject.Service.CompanyService;
import com.example.finalproject.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final MyUserService myUserService;
    private final CompanyService companyService;
    private final BookingService bookingService;

    @GetMapping("/get-company")
    public ResponseEntity getcomany() {
        List<Company> companies = companyService.getCompany();
        return ResponseEntity.status(200).body(companies);
    }
//@PostMapping("/register")
//         public ResponseEntity myUser(@Valid @RequestBody MyUser user) {
//         myUserService.register();
//         return ResponseEntity.status(200).body("registered User!");
//    }


    @PutMapping("/change-status/{status}/{companyId}")
    public ResponseEntity changeStatus(@Valid @PathVariable String status, @PathVariable Integer  companyId) {
        companyService.changeStatus(status,companyId);
        return ResponseEntity.status(200).body("status changed ");
    }



    @PutMapping("/update/{companyId}")
    public ResponseEntity updateCompany(@AuthenticationPrincipal MyUser user,@RequestBody Company company,@PathVariable Integer companyId) {
        companyService.updateCompany(user.getId(), company,companyId);
        return ResponseEntity.status(200).body("company updated");
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity deleteCompany(@AuthenticationPrincipal MyUser user,@PathVariable Integer companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(200).body("company deleted");
    }
}
