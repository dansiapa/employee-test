package com.employee.technical.repository;

import com.employee.technical.model.EmployeesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesModel,Integer> {
    @Query("Select emp From EmployeesModel emp Where emp.empNo = :no")
    Optional<EmployeesModel> findByEmpNo(Integer no);

    @Query("Select emp From EmployeesModel emp Where emp.empNo = :no")
    EmployeesModel getByEmpNo(Integer no);

    @Query("Delete From EmployeesModel emp where emp.empNo = :no")
    void delete(Integer no);
}
