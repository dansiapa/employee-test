package com.employee.technical.service;

import com.employee.technical.dto.DetailEmployeeDTO;
import com.employee.technical.dto.EmployeesDTO;
import com.employee.technical.model.*;
import com.employee.technical.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final String SUCCESS_UPDATE = "Success UPDATE";
    @Autowired
    private EmployeesRepository repository;
    @Autowired
    private DeptEmpRepository deptEmpRepository;
    @Autowired
    private DeptManagerRepository deptManagerRepository;
    @Autowired
    private TitlesRepository titlesRepository;
    @Autowired
    private SalariesRepository salariesRepository;


    public EmployeesModel addNewEmployee(EmployeesModel item) {
        EmployeesModel add = new EmployeesModel();
        if (item.getGender().equals("M") || item.getGender().equals("F")){
            add.setEmpNo(item.getEmpNo());
            add.setBirthDate(item.getBirthDate());
            add.setFirstName(item.getFirstName());
            add.setLastName(item.getLastName());
            add.setGender(item.getGender());
            add.setHireDate(item.getHireDate());
            return repository.save(add);
        } else {
            return null;
        }

    }


    public List<EmployeesDTO> getAllEmployees() {
        return repository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public EmployeesDTO getEmployee(Integer no) {
        Optional<EmployeesModel> get = repository.findByEmpNo(no);
        if (get.isPresent()) {
            return convertDTO(get.get());
        } else {
            return null;
        }
    }

    public boolean deleteEmployee(Integer no) {
        try {
            Optional<EmployeesModel> employeesModel = repository.findByEmpNo(no);
            if (employeesModel.isPresent()) {
                repository.delete(no);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public EmployeesModel updateEmployee(Integer no,EmployeesModel item) {
        Optional<EmployeesModel> getEmployee = repository.findByEmpNo(no);

        if (getEmployee.isPresent()) {
            EmployeesModel update = getEmployee.get();
            update.setEmpNo(getEmployee.get().getEmpNo());
            update.setBirthDate(item.getBirthDate());
            update.setFirstName(item.getFirstName());
            update.setLastName(item.getLastName());
            update.setGender(item.getGender());
            update.setHireDate(item.getHireDate());
            return repository.save(update);
        } else {
            return null;
        }

    }

    public DetailEmployeeDTO getDetailEmployee(Integer no) {
        Optional<EmployeesModel> getEmployee = repository.findByEmpNo(no);
        List<SalariesModel> getSalaries = salariesRepository.findByEmpNo(no);
        List<TitlesModel> getTitles = titlesRepository.findByEmpNo(no);
        List<DeptManagerModel> getDeptManager = deptManagerRepository.findByEmpNo(no);
        List<DeptEmpModel> getDeptEmp = deptEmpRepository.findByEmpNo(no);

        List<SalariesModel> saveSalaries = new ArrayList<>();
        List<TitlesModel> saveTitles = new ArrayList<>();
        List<DeptManagerModel> saveDeptManager = new ArrayList<>();
        List<DeptEmpModel> saveDeptEmp = new ArrayList<>();
        DetailEmployeeDTO dto = new DetailEmployeeDTO();
        if (getEmployee.isPresent()) {

            dto.setNo(getEmployee.get().getEmpNo());
            dto.setBornDate(getEmployee.get().getBirthDate());
            dto.setName(getEmployee.get().getFirstName());
            dto.setLastName(getEmployee.get().getLastName());
            dto.setGender(getEmployee.get().getGender());
            dto.setHireAt(getEmployee.get().getHireDate());
            if (getSalaries != null) {
                getSalaries.forEach(salaries -> {
                    SalariesModel salariesModel = new SalariesModel();
                    salariesModel.setEmpNo(salaries.getEmpNo());
                    salariesModel.setSalary(salaries.getSalary());
                    salariesModel.setFromDate(salaries.getFromDate());
                    salariesModel.setToDate(salaries.getToDate());

                    saveSalaries.add(salariesModel);
                });
            }
            if (getTitles != null) {
                getTitles.forEach(titles -> {
                    TitlesModel titlesModel = new TitlesModel();
                    titlesModel.setEmpNo(titles.getEmpNo());
                    titlesModel.setTitle(titles.getTitle());
                    titlesModel.setFromDate(titles.getFromDate());
                    titlesModel.setToDate(titles.getToDate());

                    saveTitles.add(titlesModel);
                });
            }
            if (getDeptManager != null) {
                getDeptManager.forEach(deptManager -> {
                    DeptManagerModel deptManagerModel = new DeptManagerModel();
                    deptManagerModel.setDeptNo(deptManager.getDeptNo());
                    deptManagerModel.setEmpNo(deptManager.getEmpNo());
                    deptManagerModel.setFromDate(deptManager.getFromDate());
                    deptManagerModel.setToDate(deptManager.getToDate());

                    saveDeptManager.add(deptManagerModel);
                });
            }
            if (getDeptEmp != null) {
                getDeptEmp.forEach(deptEmp -> {
                    DeptEmpModel deptEmpModel = new DeptEmpModel();
                    deptEmpModel.setEmpNo(deptEmp.getEmpNo());
                    deptEmpModel.setDeptNo(deptEmp.getDeptNo());
                    deptEmpModel.setFromDate(deptEmp.getFromDate());
                    deptEmpModel.setToDate(deptEmp.getToDate());

                    saveDeptEmp.add(deptEmpModel);
                });
            }

            dto.setSalaries(saveSalaries);
            dto.setTitles(saveTitles);
            dto.setDeptManager(saveDeptManager);
            dto.setDeptEmp(saveDeptEmp);

        }
        return dto;
    }

    public EmployeesDTO convertDTO(EmployeesModel item) {
        EmployeesDTO dto = new EmployeesDTO();
        dto.setNo(item.getEmpNo());
        dto.setBornDate(item.getBirthDate());
        dto.setName(item.getFirstName());
        dto.setLastName(item.getLastName());
        dto.setGender(item.getGender());
        dto.setHireAt(item.getHireDate());
        return dto;
    }
}
