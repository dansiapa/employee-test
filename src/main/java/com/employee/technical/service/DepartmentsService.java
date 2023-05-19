package com.employee.technical.service;

import com.employee.technical.dto.DepartmentsDTO;
import com.employee.technical.dto.DeptEmpDTO;
import com.employee.technical.dto.DeptManagerDTO;
import com.employee.technical.dto.EmployeesDTO;
import com.employee.technical.model.DepartmentsModel;
import com.employee.technical.model.DeptEmpModel;
import com.employee.technical.model.DeptManagerModel;
import com.employee.technical.model.EmployeesModel;
import com.employee.technical.repository.DepartmentsRepository;
import com.employee.technical.repository.DeptEmpRepository;
import com.employee.technical.repository.DeptManagerRepository;
import com.employee.technical.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    @Autowired
    private DepartmentsRepository repository;
    @Autowired
    private DeptEmpRepository deptEmpRepository;
    @Autowired
    private DeptManagerRepository deptManagerRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    public DepartmentsModel addNewDepartment(DepartmentsModel item){
        DepartmentsModel departmentsModel = new DepartmentsModel();
        DepartmentsModel getDepartment = repository.findByDeptNo(item.getDeptNo());
        if (Objects.nonNull(getDepartment)){
            return null;
        }else{
            departmentsModel.setDeptNo(item.getDeptNo());
            departmentsModel.setDeptName(item.getDeptName());
            return repository.save(departmentsModel);
        }
    }

    public List<DepartmentsDTO> getAllDepartments(){
        return repository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public List<DeptManagerDTO> getDeptManagerByFromDate(LocalDate fromDate){
        List<DeptManagerModel> findDeptManagerByFromDate = deptManagerRepository.findByFromDate(fromDate);
        List<DeptManagerDTO> listDeptManager = new ArrayList<>();
        if (findDeptManagerByFromDate != null){
            DeptManagerDTO model = new DeptManagerDTO();
            findDeptManagerByFromDate.forEach(deptManager ->{

                DepartmentsModel getDepartments = repository.findByDeptNo(deptManager.getDeptNo());
                DepartmentsDTO departmentsDTO = new DepartmentsDTO();
                departmentsDTO.setNo(getDepartments.getDeptNo());
                departmentsDTO.setName(getDepartments.getDeptName());
                model.setDept(departmentsDTO);

                EmployeesModel getEmployee = employeesRepository.getByEmpNo(deptManager.getEmpNo());
                EmployeesDTO employeesDTO = new EmployeesDTO();
                employeesDTO.setNo(getEmployee.getEmpNo());
                employeesDTO.setBornDate(getEmployee.getBirthDate());
                employeesDTO.setName(getEmployee.getFirstName());
                employeesDTO.setGender(getEmployee.getGender());
                employeesDTO.setHireAt(getEmployee.getHireDate());
                model.setEmpNo(employeesDTO);

                model.setFromDate(deptManager.getFromDate());
                model.setToDate(deptManager.getToDate());

            });
            listDeptManager.add(model);
        }
        return listDeptManager;
    }

    public List<DeptEmpDTO> getDeptEmpByFromDate(LocalDate fromDate){
        List<DeptEmpModel> findDeptEmpByFromDate = deptEmpRepository.findByFromDate(fromDate);
        List<DeptEmpDTO> listDeptEmp = new ArrayList<>();
        if (findDeptEmpByFromDate != null){
            DeptEmpDTO model = new DeptEmpDTO();
            findDeptEmpByFromDate.forEach(deptEmp ->{

                EmployeesModel getEmployee = employeesRepository.getByEmpNo(deptEmp.getEmpNo());
                EmployeesDTO employeesDTO = new EmployeesDTO();
                employeesDTO.setNo(getEmployee.getEmpNo());
                employeesDTO.setBornDate(getEmployee.getBirthDate());
                employeesDTO.setName(getEmployee.getFirstName());
                employeesDTO.setGender(getEmployee.getGender());
                employeesDTO.setHireAt(getEmployee.getHireDate());
                model.setEmpNo(employeesDTO);

                DepartmentsModel getDepartments = repository.findByDeptNo(deptEmp.getDeptNo());
                DepartmentsDTO departmentsDTO = new DepartmentsDTO();
                departmentsDTO.setNo(getDepartments.getDeptNo());
                departmentsDTO.setName(getDepartments.getDeptName());
                model.setDept(departmentsDTO);

                model.setFromDate(deptEmp.getFromDate());
                model.setToDate(deptEmp.getToDate());

            });
            listDeptEmp.add(model);
        }
        return listDeptEmp;
    }

    private DepartmentsDTO convertDTO(DepartmentsModel item){
        DepartmentsDTO dto = new DepartmentsDTO();
        dto.setNo(item.getDeptNo());
        dto.setName(item.getDeptName());
        return dto;
    }
}
