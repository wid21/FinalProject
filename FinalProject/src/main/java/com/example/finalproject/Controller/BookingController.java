package com.example.finalproject.Controller;

import com.example.finalproject.Dto.DtoBooking;
import com.example.finalproject.Model.Booking;
import com.example.finalproject.Model.Customer;
import com.example.finalproject.Model.Details;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@RestController
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add-booking")
    public ResponseEntity addbooking (@AuthenticationPrincipal MyUser customer,@RequestBody DtoBooking dtoBooking){
        Double price= bookingService.addOrder(customer,dtoBooking);
        return ResponseEntity.status(200).body("order added your total price is : " + price);
    }
    @PostMapping ("/add-points")
    public ResponseEntity addPoints(@AuthenticationPrincipal MyUser user){
        int points = bookingService.addpoints(user);
        return ResponseEntity.status(200).body("points added  : " + points);

    }

//    @PutMapping("/update-booking/{bookingId}")
//    public ResponseEntity updatebooking ( @AuthenticationPrincipal MyUser customer,@RequestBody DtoBooking booking,@PathVariable Integer bookingId){
//     double price=bookingService.updateOrder(customer,booking,bookingId);
//        return ResponseEntity.status(200).body("order updated"+ price );
//    }//

    @DeleteMapping("/delete-booking/{bookingId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser customer, @PathVariable Integer bookingId){
        bookingService.deleteBooking(customer.getId(),bookingId);
        return ResponseEntity.status(200).body("order deleted ");
    }


    @PutMapping("/change-status/{status}/{bookingId}")
    public ResponseEntity changeStatus(@Valid @PathVariable String status, @PathVariable Integer  bookingId) {
        bookingService.changeStatus(status,bookingId);
        return ResponseEntity.status(200).body("status changed ");
    }
//    @GetMapping("/get-customerOrders")
//    public ResponseEntity getBookings(@AuthenticationPrincipal MyUser user) {
//        List<Booking> orders = bookingService.getCustomerOrders(user.getId());
//        return ResponseEntity.status(200).body(orders);
//    }

}
