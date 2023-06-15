package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Dto.DtoCompany;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.CompanyRepository;
import com.example.finalproject.Repository.DetailsRepository;
import com.example.finalproject.Repository.MyUserRepository;
import com.example.finalproject.Repository.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MyUserRepository myUserRepository;
    private final DetailsRepository detailsRepository;
    private final StaffRepository staffRepository;

    public List<Company> getCompany(){
        return companyRepository.findAll();
    }


//    public void register(MyUser usr){
//        String hash = new BCryptPasswordEncoder().encode(usr.getPassword());
//        usr.setPassword(hash);
//        usr.setRole("Company");
//        myUserRepository.save(usr);
//       Company company   = new Company(null,null,null,null,usr,null,null,null);
//        companyRepository.save(company);
//    }

    public void updateCompany(Integer userId, Company company, Integer companyId) {
        Company company1=companyRepository.findCompanyById(companyId);
        if(company1==null){
            throw new ApiException("not found");
        }
        if(userId!=company1.getMyUser().getId()){
            throw new ApiException("unauthorized");
        }
        company1.setName(company.getName());
        company1.setAddress(company.getAddress());
        company1.setPhoneNumber(company.getPhoneNumber());
        company1.setLicensNum(company.getLicensNum());
        companyRepository.save(company1);
    }

    public void deleteCompany(Integer companyId) {

        Company company = companyRepository.findCompanyById(companyId);
        Set<Staff> staffs = company.getStaff();
        Set<Details> details=company.getDetails();
        MyUser user = company.getMyUser();

        for (Staff staff : staffs) {
            staffRepository.delete(staff);
        }
        for (Details detail:details){
            detailsRepository.delete(detail);
        }
        companyRepository.delete(company);
        myUserRepository.delete(user);
    }



    public void changeStatus(String status, Integer companyId){
        Company company  =companyRepository.findCompanyById(companyId);
        if (company == null){
            throw new ApiException("order Not Found");
        }
        company.setStatus(status);
        companyRepository.save(company);

    }



}

