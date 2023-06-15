package com.example.finalproject.Repository;

import com.example.finalproject.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {


    Staff findStaffById(Integer id);
}
