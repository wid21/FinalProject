package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Dto.DtoCompany;
import com.example.finalproject.Dto.DtoCustomer;
import com.example.finalproject.Model.Booking;
import com.example.finalproject.Model.Company;
import com.example.finalproject.Model.Customer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Repository.BookingRepository;
import com.example.finalproject.Repository.CompanyRepository;
import com.example.finalproject.Repository.CustomerRepository;
import com.example.finalproject.Repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public List<MyUser> get(){
        return myUserRepository.findAll();
    }
    public void registerCompany(DtoCompany dtoCompany){
        String hash = new BCryptPasswordEncoder().encode(dtoCompany.getPassword());
        dtoCompany.setPassword(hash);
        dtoCompany.setRole("Company");
        MyUser user = new MyUser(null,dtoCompany.getUsername(), dtoCompany.getEmail(), dtoCompany.getPassword(), "Company",null,null);
        Company company = new Company(null, dtoCompany.getName(), dtoCompany.getPhoneNumber(), dtoCompany.getAddress(), dtoCompany.getLicensNum(),"Pending", user,null,null);
        user.setCompany(company);
        companyRepository.save(company);
        myUserRepository.save(user);
    }

    public void registerCustomer(DtoCustomer dtoCustomer){
        String hash = new BCryptPasswordEncoder().encode(dtoCustomer.getPassword());
        dtoCustomer.setPassword(hash);
        dtoCustomer.setRole("Customer");
        MyUser user = new MyUser(null,dtoCustomer.getUsername(), dtoCustomer.getEmail(), dtoCustomer.getPassword(), "Customer",null,null);
        Customer customer = new Customer(null, dtoCustomer.getPhoneNumber(), dtoCustomer.getAddress(),0,user,null);
        user.setCustomer(customer);
        customerRepository.save(customer);
        myUserRepository.save(user);
    }






}
