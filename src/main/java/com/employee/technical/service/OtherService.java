package com.employee.technical.service;

import com.employee.technical.model.*;
import com.employee.technical.repository.DeptEmpRepository;
import com.employee.technical.repository.DeptManagerRepository;
import com.employee.technical.repository.SalariesRepository;
import com.employee.technical.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OtherService {
    @Autowired
    private DeptEmpRepository deptEmpRepository;
    @Autowired
    private DeptManagerRepository deptManagerRepository;
    @Autowired
    private TitlesRepository titlesRepository;
    @Autowired
    private SalariesRepository salariesRepository;

    public SalariesModel addNewSalaries(SalariesModel item) {
        SalariesModel add = new SalariesModel();
        add.setEmpNo(item.getEmpNo());
        add.setSalary(item.getSalary());
        add.setFromDate(item.getFromDate());
        add.setToDate(item.getToDate());
        return salariesRepository.save(add);
    }

    public TitlesModel addNewTitles(TitlesModel item) {
        TitlesModel add = new TitlesModel();
        add.setEmpNo(item.getEmpNo());
        add.setTitle(item.getTitle());
        add.setFromDate(item.getFromDate());
        add.setToDate(item.getToDate());
        return titlesRepository.save(add);
    }

    public DeptEmpModel addNewDeptEmp(DeptEmpModel item) {
        DeptEmpModel add = new DeptEmpModel();
        add.setEmpNo(item.getEmpNo());
        add.setDeptNo(item.getDeptNo());
        add.setFromDate(item.getFromDate());
        add.setToDate(item.getToDate());
        return deptEmpRepository.save(add);
    }

    public DeptManagerModel addNewDeptManager(DeptManagerModel item) {
        DeptManagerModel add = new DeptManagerModel();
        add.setDeptNo(item.getDeptNo());
        add.setEmpNo(item.getEmpNo());
        add.setFromDate(item.getFromDate());
        add.setToDate(item.getToDate());
        return deptManagerRepository.save(add);
    }
}
