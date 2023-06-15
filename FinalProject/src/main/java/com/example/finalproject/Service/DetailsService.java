package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.CompanyRepository;
import com.example.finalproject.Repository.DetailsRepository;
import com.example.finalproject.Repository.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailsService {

    private final DetailsRepository detailsRepository;
    private final CompanyRepository companyRepository;
    private final StaffRepository staffRepository;


    public void add(Details details){

        detailsRepository.save(details);
}

    public void addDetailsToStaffToCompany(Integer Company_id, Integer details_id, Integer staff_id) {
        Company company = companyRepository.findCompanyById(Company_id);
        Details details = detailsRepository.findDetailsById(details_id);
        Staff staff = staffRepository.findStaffById(staff_id);

        if (company == null) {
            throw new ApiException("Cannot add staff, company not found");
        }
        if (staff == null) {
            throw new ApiException("There is no staff to add");
        }
        if (details == null) {
            throw new ApiException("There is no details to add staff in to it ");
        }

        Company existingCompany = staff.getCompany();
        if (existingCompany != null && !existingCompany.equals(company)) {
            throw new ApiException("This staff is already assigned to another company");
        }
        if (existingCompany != null && existingCompany.equals(company)) {
            throw new ApiException("This staff is already assigned to this company");
        }

        staff.setCompany(company);
        staffRepository.save(staff);
        if (details.getCompany() == null) {
            details.setCompany(company);
            detailsRepository.save(details);
        }
    }


    public void deleteDetails(Integer companyId, Integer detailsId) {
        Company company1 = companyRepository.findCompanyById(companyId);
        if (company1 == null) {
            throw new ApiException("Company not found");
        }
        Details details = detailsRepository.findDetailsById(detailsId);
        if (details == null) {
            throw new ApiException("Details not found");
        }
        if (company1.getId() != details.getCompany().getId()) {
            throw new ApiException("Details not associated with your company");
        }
        company1.getStaff().remove(details);
        detailsRepository.delete(details);
    }



    public void updateDetails(Integer companyId,Details details, Integer detailsId){
        Details details1=detailsRepository.findDetailsById(detailsId);
        if (details1 == null){
            throw new ApiException("details Not Found");
        }
        details1.setPrice(details.getPrice());
        details1.setHours(details.getHours());
        details1.setCategory(details.getCategory());
        details1.setType(details.getType());
        details1.setDescription(details.getDescription());
        detailsRepository.save(details1);
    }



    public List<Details> getDetails(){

        return detailsRepository.findAll();
    }


//    public void deleteDetails( Integer detailsId) {
//        Details details = detailsRepository.findDetailsById(detailsId);
//
//        if (details == null) {
//            throw new ApiException("Details not found");
//        }
//
//        for (Staff staff : details.getStaff()) {
//            staff.getDetails().remove(details);
//        }
//
//        for (Company company : details.getCompany()) {
//            company.getDetails().remove(details);
//        }
//        detailsRepository.delete(details);
//    }


//    public void updateDetails(Details details, Integer dedtailsId){
//            Details details1=detailsRepository.findDetailsById(dedtailsId);
//            if (details1 == null){
//                throw new ApiException("details Not Found");
//            }
//        details1.setPrice(details.getPrice());
//        details1.setHours(details.getHours());
//        details1.setCategory(details.getCategory());
//        details1.setType(details.getType());
//        details1.setDescription(details.getDescription());
//            detailsRepository.save(details1);
//        }
    }



