package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Dto.DtoRate;
import com.example.finalproject.Model.Booking;
import com.example.finalproject.Model.Customer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Rate;
import com.example.finalproject.Repository.BookingRepository;
import com.example.finalproject.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RateService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;


//    public void add(MyUser customer, DtoRate rate,) {
//        Customer customer1 = customerRepository.findCustomerById(customer.getId());
//        if (customer == null) {
//            throw new ApiException("Cannot add order, customer not found");
//        }
//        Set<Booking> booking = customer1.getBookings();
//        if (booking == null) {
//            throw new ApiException("Booking not found");
//        }
//        //get the status from booking if its completed then add rate
//
//        bookingRepository.findBookingById();
//            Rate existingRate = booking.
//            if (existingRate == null) {
//                existingRate = new Rate();
//                existingRate.setRate(rate.getRate());
//                existingRate.setReview(rate.getReview());
//                booking.setRate(existingRate);
//            }
//
//            bookingRepository.save(booking);
//        } else {
//            throw new ApiException("Booking is not completed");
//        }
//public void add(Integer customerId, DtoRate rate) {
//    Customer customer = customerRepository.findCustomerById(customerId);
//    if (customer == null) {
//        throw new ApiException("Customer not found");
//    }
//    Booking booking = bookingRepository.findBookingById(rate.getBookingId());
//    if (booking == null) {
//        throw new ApiException("Booking not found");
//    }
//    if (!booking.getStatus().equals("new")) {
//        throw new ApiException("Booking is not completed");
//    }
//    Rate rateEntity = new Rate();
//    rateEntity.setRate(rate.getRate());
//    rateEntity.setReview(rate.getReview());
//    rateEntity.setBooking(booking); // Set the Booking property of the Rate entity
//    rateRepository.save(rateEntity); // Save the Rate entity to the database
//    booking.setRate(rateEntity); // Set the Rate property of the Booking entity
//    bookingRepository.save(booking); // Save the Booking entity to the database
//}
    }


