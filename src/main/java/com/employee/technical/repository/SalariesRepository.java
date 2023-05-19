package com.employee.technical.repository;

import com.employee.technical.model.SalariesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesRepository extends JpaRepository<SalariesModel,Integer> {
    @Query("Select sm From SalariesModel sm Where sm.empNo = :no")
    List<SalariesModel> findByEmpNo(Integer no);
}
