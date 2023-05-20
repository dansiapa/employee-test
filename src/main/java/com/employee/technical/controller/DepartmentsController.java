package com.employee.technical.controller;

import com.employee.technical.dto.DepartmentsDTO;
import com.employee.technical.dto.DeptManagerDTO;
import com.employee.technical.model.DepartmentsModel;
import com.employee.technical.repository.DepartmentsRepository;
import com.employee.technical.response.DataResponse;
import com.employee.technical.response.HandlerResponse;
import com.employee.technical.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/department", produces = {"application/json"})
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;
    @Autowired
    private DepartmentsRepository repository;

    @GetMapping("/all-departments")
    public void getAllDepartments(HttpServletRequest request, HttpServletResponse response) {
        List<DepartmentsDTO> departmentsDTOS = departmentsService.getAllDepartments();
        DataResponse<List<DepartmentsDTO>> dataResponse = new DataResponse<>();
        dataResponse.setData(departmentsDTOS);
        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }

    @PostMapping("/add-departments")
    public void addNewDepartment(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody DepartmentsModel model) {
        DepartmentsModel departments = departmentsService.addNewDepartment(model);
        DepartmentsModel getDepartment = repository.findByDeptNo(model.getDeptNo());
        if (Objects.nonNull(getDepartment)){
            HandlerResponse.responseBadRequest(response, "001", "Duplicate Data");
        }
        else if (Objects.nonNull(departments)) {
            HandlerResponse.responseSuccessOK(response, "Success Add New Department");
        } else {
            HandlerResponse.responseBadRequest(response, "005", "Something Wrong");
        }
    }

    @GetMapping("/dept-manager")
    public void getDeptManagerByFromDate(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam("fromDate")
                                         @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate) {
        List<DeptManagerDTO> deptManagerDTOS = departmentsService.getDeptManagerByFromDate(fromDate);
        if (Objects.nonNull(deptManagerDTOS)) {
            DataResponse<List<DeptManagerDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(deptManagerDTOS);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }
    @GetMapping("/dept-emp")
    public void getDeptEmpByFromDate(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam("fromDate")
                                     @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate) {
        Object deptEmpDTOS = departmentsService.getDeptEmpByFromDate(fromDate);
        if (Objects.nonNull(deptEmpDTOS)) {
            DataResponse<Object> dataResponse = new DataResponse<>();
            dataResponse.setData(deptEmpDTOS);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else if (Objects.isNull(deptEmpDTOS)){
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }

}
