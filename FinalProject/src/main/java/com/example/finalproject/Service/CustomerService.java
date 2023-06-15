package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Dto.DtoCompany;
import com.example.finalproject.Dto.DtoCustomer;
import com.example.finalproject.Dto.DtoPoints;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.BookingRepository;
import com.example.finalproject.Repository.CustomerRepository;
import com.example.finalproject.Repository.MyUserRepository;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerService {

    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public List<Customer> getCustomer() {

        return customerRepository.findAll();
    }

//    public int addPoints(MyUser customer1 {
//        Customer customer = customerRepository.findCustomerById(customer1.getId());
//        if (customer == null) {
//            throw new ApiException("Customer not found");
//        }
//        Points points = customer.getPoints();
//        if (points == null) {
//            points = new Points();
//            points.setCustomer(customer);
//        }
//        int userPoints = 0;
//        for (Booking booking : customer.getBookings()) {
//            if (booking.getStatus().equals("completed") && booking.getNewpoints() > 0) {
//                userPoints += booking.getNewpoints();
//                booking.setNewpoints(0);
//                bookingRepository.save(booking);
//                System.out.println("Added " + booking.getNewpoints() + " points for booking " + booking.getId());
//            }
//        }
//
//        points.addPoints(userPoints);
//        customer.setPoints(points);
//        customerRepository.save(customer);
//        System.out.println("Final points: " + customer.getPoints().getTotalPoints());
//        return userPoints;
//    }



//    public int addpoints(MyUser customer) {
//        Customer customer1 = customerRepository.findCustomerById(customer.getId());
//        if (customer1 == null) {
//            throw new ApiException("Cannot add points, customer not found");
//        }
//
//        int points = customer1.getPoints();
//        for (Booking booking : customer1.getBookings()) {
//            if (booking.getStatus().equals("completed") && booking.getNewpoints() > 0) {
//                points += booking.getNewpoints();
//                booking.setNewpoints(0); // Clear the oldpoints in the booking
//                bookingRepository.save(booking); // Save the changes to the booking
//            }
//        }
//
//        customer1.setPoints(points);
//        customerRepository.save(customer1);
//        return points;
//    }

//
//    public void updateCustomer(Integer userId,Customer customer,Integer customerId){
//        Customer customer1 = customerRepository.findCustomerById(customerId);
//        if(customer1==null){
//            throw new ApiException("id not found");
//        }
//        if(userId!=customer1.getMyUser().getId()){
//            throw new ApiException("unauthorized");
//        }
//        customer1.setAddress(customer.getAddress());
//        customer1.setPhoneNumber(customer.getPhoneNumber());
//        customerRepository.save(customer1);
//    }
//
//    public void deleteCustomer(Integer customerId) {
//        Customer customer = customerRepository.findCustomerById(customerId);
//        Set<Booking> orders = customer.getBookings();
//
//
//        MyUser user = customer.getMyUser();
//
//
//        for (Booking order : orders) {
//            bookingRepository.delete(order);
//        }
//
//
//        customerRepository.delete(customer);
//        myUserRepository.delete(user);
//    }

}