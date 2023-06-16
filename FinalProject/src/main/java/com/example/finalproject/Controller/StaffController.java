package com.example.finalproject.Controller;

import com.example.finalproject.Model.Details;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Staff;
import com.example.finalproject.Service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@RestController
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/add-staff")
    public ResponseEntity add(@RequestBody @Valid Staff staff) {
        staffService.addStaff(staff);
        return ResponseEntity.status(200).body("service added");
    }
    @DeleteMapping("/delete-staff/{staffId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser company,@PathVariable Integer staffId){
        staffService.deleteStaff(company.getId(),staffId);
        return ResponseEntity.status(200).body("staff deleted ");
    }
    @PutMapping("/update-staff/{staffId}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal MyUser company,@Valid @RequestBody Staff staff , @PathVariable Integer staffId){
        staffService.updateStaff(company.getId(),staff, staffId);
        return ResponseEntity.status(200).body("Staff Updated");
    }
//
//    @GetMapping("/get-staff")
//    public ResponseEntity getorder() {
//        List<Staff> staff = staffService.getStaff();
//        return ResponseEntity.status(200).body(staff);
//    }
//    @DeleteMapping("/delete-staff/{staffId}")
//    public ResponseEntity deleteOrder( @PathVariable Integer staffId){
//        staffService.deleteStaff(staffId);
//        return ResponseEntity.status(200).body("staff deleted ");
//    }
//    @PutMapping("/update-staff/{staffId}")
//    public ResponseEntity updateProduct(@Valid @RequestBody Staff staff , @PathVariable Integer staffId){
//        staffService.updateStaff(staff, staffId);
//        return ResponseEntity.status(200).body("Staff Updated");
//    }
//
//    @PostMapping("/add-staff-toCompany/{Company_id}/{staff_id}")
//    public ResponseEntity addDetails(@PathVariable Integer Company_id, @PathVariable Integer staff_id){
//        staffService.addStaffToCompany(Company_id,staff_id);
//        return ResponseEntity.status(200).body("service added");
//    }
//}
}