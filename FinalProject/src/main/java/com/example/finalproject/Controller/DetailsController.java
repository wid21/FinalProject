package com.example.finalproject.Controller;

import com.example.finalproject.Model.Details;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Staff;
import com.example.finalproject.Service.DetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/details")
@RequiredArgsConstructor
@RestController
public class DetailsController {

    private final DetailsService detailsService;


    @PostMapping("/add-details")
    public ResponseEntity add(@RequestBody @Valid Details details) {
        detailsService.add(details);
        return ResponseEntity.status(200).body("service added");
    }

    @DeleteMapping("/delete-details/{detailsId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser company, @PathVariable Integer detailsId){
        detailsService.deleteDetails(company.getId(),detailsId);
        return ResponseEntity.status(200).body("details deleted ");
    }

    @PutMapping("/update-details/{detailsId}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal MyUser company, @Valid @RequestBody Details details  , @PathVariable Integer detailsId){
        detailsService.updateDetails(company.getId(),details, detailsId);
        return ResponseEntity.status(200).body("details Updated");
    }
    @GetMapping("/get-details")
    public ResponseEntity getorder() {
        List<Details> details = detailsService.getDetails();
        return ResponseEntity.status(200).body(details);
    }


//    @PostMapping("/add-details-toCompany/{Company_id}/{details_id}")
//    public ResponseEntity addDetails(@PathVariable Integer Company_id, @PathVariable Integer details_id){
//        detailsService.addDetailsToCompany(Company_id,details_id);
//        return ResponseEntity.status(200).body("service added");
//    }
//
    @PostMapping("/add-details-toStaff/{Company_id}/{details_id}/{staff_id}")
    public ResponseEntity addDetailsTostaff(@PathVariable Integer Company_id, @PathVariable Integer details_id,@PathVariable Integer staff_id){
        detailsService.addDetailsToStaffToCompany(Company_id,details_id,staff_id);
        return ResponseEntity.status(200).body("service added");
    }
//
////    @DeleteMapping("/delete-details/{detailsId}")
////    public ResponseEntity deleteOrder( @PathVariable Integer detailsId){
////        detailsService.deleteDetails(detailsId);
////        return ResponseEntity.status(200).body("details deleted ");
////    }
//
//    @PutMapping("/update-details/{detailsId}")
//    public ResponseEntity updateProduct(@Valid @RequestBody Details details  , @PathVariable Integer detailsId){
//        detailsService.updateDetails(details, detailsId);
//        return ResponseEntity.status(200).body("details Updated");
//    }



}
