package com.employee.technical.repository;

import com.employee.technical.model.DeptManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManagerModel, String> {
    @Query("Select dmm From DeptManagerModel dmm Where dmm.empNo = :no")
    List<DeptManagerModel> findByEmpNo(Integer no);

    @Query("Select dmm From DeptManagerModel dmm Where dmm.fromDate = :fromDate")
    List<DeptManagerModel> findByFromDate(LocalDate fromDate);
}
