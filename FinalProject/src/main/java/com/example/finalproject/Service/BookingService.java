package com.example.finalproject.Service;


import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Dto.DtoBooking;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingService {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookRepository;
    private final CompanyRepository companyRepository;
    private final DetailsRepository detailsRepository;
    private final StaffRepository staffRepository;
    private final ReservationDateRepository reservationDateRepository;

//    public List<Booking> getCustomerOrders(Integer customerId) {
//        //Customer customer =customerRepository.findCustomerById(customerId);
//        List<Booking>orders=bookRepository.findBookingByCustomer_Id(customerId);
//        if (orders ==null){
//            throw new ApiException("You do not have any orders ");
//        }
//        return orders;
//    }
//
//
//    public double addOrder(MyUser customer1, DtoBooking dtoBooking) {
//        Customer customer = customerRepository.findCustomerById(customer1.getId());
//        if (customer == null) {
//            throw new ApiException("Cannot add order, customer not found");
//        }
//        Company company = companyRepository.findCompanyById(dtoBooking.getCompany_Id());
//        if (company == null) {
//            throw new ApiException("Cannot add order, company not found");
//        }
//        if (company.getDetails() == null) {
//            throw new ApiException("Cannot add order, company has no service details");
//        }
//        if (company.getStaff() == null) {
//            throw new ApiException("Cannot add order, company has no staff assigned");
//        }
//
//        Booking existingBooking = bookRepository.findBookingByCustomerAndCompany(customer, company);
//        if (existingBooking != null) {
//            // Update existing booking
//            Set<Details> companyDetails = company.getDetails();
//            double totalPrice = 0;
//            for (Details d : companyDetails) {
//                totalPrice += dtoBooking.getQuantity() * d.getPrice();
//            }
//            int newpoint = 0;
//            if (totalPrice > 500 && totalPrice < 1000) {
//                newpoint = 250;
//            } else if (totalPrice >= 1000) {
//                newpoint = 500;
//            } else {
//                newpoint = 100;
//            }
//            existingBooking.setTotalprice(totalPrice);
//            existingBooking.setNewpoints(newpoint);
//            existingBooking.setQuantity(dtoBooking.getQuantity());
//            existingBooking.setDetails(detailsRepository.findDetailsById(dtoBooking.getDetailsId()));
//            bookRepository.save(existingBooking);
//            return totalPrice;
//        } else {
//            // Create new booking
//            Set<Details> companyDetails = company.getDetails();
//            double totalPrice = 0;
//            for (Details d : companyDetails) {
//                totalPrice += dtoBooking.getQuantity() * d.getPrice();
//            }
//            int newpoint = 0;
//            if (totalPrice > 500 && totalPrice < 1000) {
//                newpoint = 250;
//            } else if (totalPrice >= 1000) {
//                newpoint = 500;
//            } else {
//                newpoint = 100;
//            }
//            Booking newOrder = new Booking(null, totalPrice, newpoint, "new", dtoBooking.getQuantity(), null, customer, company);
//            newOrder.setDetails(detailsRepository.findDetailsById(dtoBooking.getDetailsId()));
//            bookRepository.save(newOrder);
//            return totalPrice;
//        }
//    }

public double addOrder(MyUser customer1, DtoBooking dtoBooking) {
    Customer customer = customerRepository.findCustomerById(customer1.getId());
    if (customer == null) {
        throw new ApiException("Cannot add order, customer not found");
    }
     Company company = companyRepository.findCompanyById(dtoBooking.getCompany_Id());
    if (company == null) {
        throw new ApiException("Cannot add order, company not found");
    }
    if (company.getDetails() == null) {
        throw new ApiException("Cannot add order, company has no service details");
    }
    if (company.getStaff() == null) {
        throw new ApiException("Cannot add order, company has no staff assigned");
    }
    Details details = detailsRepository.findDetailsById(dtoBooking.getDetails_id());
    if (details == null) {
        throw new ApiException("Cannot add order, details not found");
    }

    if(!company.getStatus().equalsIgnoreCase("Approved")){
        throw new ApiException("Sorry! Can not order to this company");
    }

    List<ReservationDate> Dates = reservationDateRepository.findAllByDetails(details);
    for(int i = 0; i < Dates.size(); i++) {
        if (Dates.get(i).getReservationDate().isBefore(dtoBooking.getReservationDate())){
            throw new ApiException("Service is reserved already");
        }
        if (Dates.get(i).getReservationDate().equals(dtoBooking.getReservationDate())){
            throw new ApiException("Service is reserved already");}
    }
    LocalDateTime localDate = LocalDateTime.now();
    if (localDate.isAfter(dtoBooking.getReservationDate())){
        throw new ApiException("Can not reserve");
    }
    ReservationDate reservationdate = new ReservationDate();
    reservationdate.setReservationDate(dtoBooking.getReservationDate());
    reservationdate.setDetails(details);

    reservationDateRepository.save(reservationdate);

    double totalPrice = details.getPrice();
    int newpoint = 0;
    if (totalPrice > 500 && totalPrice < 1000) {
        newpoint = 250;
    } else if (totalPrice >= 1000) {
        newpoint = 500;
    } else {
        newpoint = 100;
    }
    int userpoints = customer.getPoints();
    double discountedPrice = calculateDiscount(userpoints, totalPrice);
    Booking newOrder = new Booking(null, discountedPrice  , newpoint ,"new",reservationdate,details,customer,null);

    newOrder.setDetails(details);
    newOrder.setDate(reservationdate);
    detailsRepository.save(details);
    bookRepository.save(newOrder);
    return discountedPrice;
}

    public int addPoints(MyUser customer) {
        Customer customer1 = customerRepository.findCustomerById(customer.getId());
        if (customer1 == null) {
            throw new ApiException("Cannot add points, customer not found");
        }

        int points = customer1.getPoints();
        System.out.println("Initial points: " + points);
        for (Booking booking : customer1.getBookings()) {
            if (booking.getStatus().equals("completed") && booking.getNewpoints() > 0) {
                points += booking.getNewpoints();
                booking.setNewpoints(0); // Clear the oldpoints in the booking
                bookRepository.save(booking); // Save the changes to the booking
                System.out.println("Added " + booking.getNewpoints() + " points for booking " + booking.getId());
            }
        }
        customer1.setPoints(points);
        customerRepository.save(customer1);
        System.out.println("Final points: " + points);
        return points;
    }

    public double calculateDiscount(int userpoints, double totalPrice) {
        if(userpoints ==0){
            return totalPrice;
        }
            int discount = userpoints / 100; // Calculate the number of discounts available
            double discountAmount = 5.0 * discount; // Calculate the total discount amount
            double discountedPrice = totalPrice - discountAmount;

        return discountedPrice;
    }

    public void cancelBooking (MyUser customer,Integer bookingId) {
        Customer customer1 = customerRepository.findCustomerById(customer.getId());
        if (customer1 == null) {
            throw new ApiException("Cannot add points, customer not found");
        }
        Booking booking = bookRepository.findBookingById(bookingId);

        if (booking == null) {
            throw new ApiException("Cannot update order, booking not found");
        }
        if (booking.getStatus().equalsIgnoreCase("inProgress") || booking.getStatus().equalsIgnoreCase("compleate")) {
            throw new ApiException("Sorry you can not cancel this order ");
        }
        booking.setNewpoints(0);
        booking.setStatus("canceled");
        bookRepository.save(booking);

    }



//    List<Details> selectedDetails = new ArrayList<>();
//    double totalPrice = 0;
//    int count = 0;
//    for (Integer detailsId : dtoBooking.getQuantityDetails().keySet()) {
//        int quantity = dtoBooking.getQuantityDetails().get(detailsId);
//        Details details = detailsRepository.findDetailsById(detailsId);
//        if (details != null) {
//            selectedDetails.add(details);
//            totalPrice += quantity * details.getPrice();
//            //newOrder.getDetails().add(details);
//            count++;
//        }
//    }
//
//    int newpoint = 0;
//    if (totalPrice > 500 && totalPrice < 1000) {
//        newpoint = 250;
//    } else if (totalPrice >= 1000) {
//        newpoint = 500;
//    } else {
//        newpoint = 100;
//    }
//
//    Booking newOrder = new Booking(null,totalPrice,newpoint,"new",count,null,customer,null);
//
//    bookRepository.save(newOrder);
//
//    return totalPrice;





//    public double updateOrder(MyUser user,DtoBooking updatedBooking,Integer bookingId) {
//        Customer customer = customerRepository.findCustomerByMyUser(user);
//        if (customer == null) {
//            throw new ApiException("Cannot update order, customer not found");
//        }
//        Booking booking = bookRepository.findBookingById(bookingId);
//        if (booking == null) {
//            throw new ApiException("Cannot update order, booking not found");
//        }
//        if (!Objects.equals(customer.getId(), booking.getCustomer().getId())){
//            throw new ApiException("Not Authorizer");
//        }
//
//        if (company.getDetails() == null) {
//            throw new ApiException("Cannot add order, company has no service details");
//        }
//        if (company.getStaff() == null) {
//            throw new ApiException("Cannot add order, company has no staff assigned");
//        }
////        List<Details> selectedDetails = detailsRepository.findAllById(dtoBooking.getDetails_id());
////        double totalPrice = 0;
////        for (Details d : selectedDetails) {
////            totalPrice += dtoBooking.getQuantity() * d.getPrice();
////        }
//        List<Details> selectedDetails = new ArrayList<>();
//        double totalPrice = 0;
//        for (Integer detailsId : dtoBooking.getDetailsQuantities().keySet()) {
//            int quantity = dtoBooking.getDetailsQuantities().get(detailsId);
//            Details details = detailsRepository.findDetailsById(detailsId);
//            if (details != null) {
//                selectedDetails.add(details);
//                totalPrice += quantity * details.getPrice();
//            }
//        }int newpoint = 0;
//        if (totalPrice > 500 && totalPrice < 1000) {
//            newpoint = 250;
//        } else if (totalPrice >= 1000) {
//            newpoint = 500;
//        } else {
//            newpoint = 100;
//        }
//
//        booking.setDetails(updatedBooking.getDetailsQuantities());
//
//        bookRepository.save(booking);
//        return totalPrice;



//    public double updateOrder(MyUser user, DtoBooking updatedBooking, Integer bookingId) {
//        Customer customer = customerRepository.findCustomerByMyUser(user);
//        if (customer == null) {
//            throw new ApiException("Cannot update order, customer not found");
//        }
//        Booking booking = bookRepository.findBookingById(bookingId);
//        if (booking == null) {
//            throw new ApiException("Cannot update order, booking not found");
//        }
//
//        Company company = booking.
//        if (company == null) {
//            throw new ApiException("Cannot update order, company not found");
//        }
//        if (company.getDetails() == null) {
//            throw new ApiException("Cannot update order, company has no service details");
//        }
//        if (company.getStaff() == null) {
//            throw new ApiException("Cannot update order, company has no staff assigned");
//        }
//
//        Set<Details> companyDetails = company.getDetails();
//        double totalPrice = 0;
//        for (Details d : companyDetails) {
//            totalPrice += updatedBooking.getQuantity() * d.getPrice();
//        }
//        int newpoint = 0;
//        if (totalPrice > 500 && totalPrice < 1000) {
//            newpoint = 250;
//        } else if (totalPrice >= 1000) {
//            newpoint = 500;
//        } else {
//            newpoint = 100;
//        }
//
//        booking.setTotalprice(totalPrice);
//        booking.setNewpoints(newpoint);
//        booking.setQuantity(updatedBooking.getQuantity());
//        booking.setDetails(detailsRepository.findDetailsById(updatedBooking.getDetails_id()));
//        bookRepository.save(booking);
//        return totalPrice;
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
//                bookRepository.save(booking); // Save the changes to the booking
//            }
//        }
//
//        customer1.setPoints(points);
//        customerRepository.save(customer1);
//        return points;
//    }

        public void deleteBooking (Integer customerId, Integer bookingId){
            Booking orders = bookRepository.findBookingById(bookingId);

            if (orders == null) {
                throw new ApiException("Order not found");
            }

            if (!customerId.equals(orders.getCustomer().getId())) {
                throw new ApiException("Not authorized");
            }

            if (orders.getStatus().equals("inProgress") || orders.getStatus().equals("completed")) {
                throw new ApiException("Cannot delete order, it has already been delivered");
            }

            orders.setCustomer(null);
            bookRepository.delete(orders);
        }


        public void changeStatus (String status, Integer bookingId){
            Booking booking = bookRepository.findBookingById(bookingId);
            if (booking == null) {
                throw new ApiException("order Not Found");
            }

            booking.setStatus(status);
            bookRepository.save(booking);

        }
    }

