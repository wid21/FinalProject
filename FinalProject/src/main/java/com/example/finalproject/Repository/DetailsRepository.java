package com.example.finalproject.Repository;

import com.example.finalproject.Model.Company;
import com.example.finalproject.Model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface DetailsRepository extends JpaRepository<Details,Integer> {

  Details findDetailsById(Integer id);
//    Details findDetailsById(Integer id);
//    List<Details> findDetailsByCompany(Company company);

  List<Details> findAllById(Integer id);
}
