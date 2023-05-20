package com.employee.technical.repository;

import com.employee.technical.model.TitlesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitlesRepository extends JpaRepository<TitlesModel,String> {
    @Query("Select tm From TitlesModel tm Where tm.empNo = :no")
    List<TitlesModel> findByEmpNo(Integer no);
}
