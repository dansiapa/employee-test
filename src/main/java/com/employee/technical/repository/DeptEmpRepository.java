package com.employee.technical.repository;

import com.employee.technical.model.DeptEmpModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmpModel,String> {
    @Query("Select dem From DeptEmpModel dem Where dem.empNo = :no")
    List<DeptEmpModel> findByEmpNo(Integer no);

    @Query("Select dem From DeptEmpModel dem Where dem.fromDate = :fromDate")
    List<DeptEmpModel> findByFromDate(LocalDate fromDate);
}
