package com.employee.technical.repository;

import com.employee.technical.model.DepartmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsModel,String> {
    @Query("Select dm From DepartmentsModel dm Where dm.deptNo = :deptNo")
    DepartmentsModel findByDeptNo(String deptNo);
}
