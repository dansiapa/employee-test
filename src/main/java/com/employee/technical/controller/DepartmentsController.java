package com.employee.technical.controller;

import com.employee.technical.dto.DepartmentsDTO;
import com.employee.technical.dto.DeptEmpDTO;
import com.employee.technical.dto.DeptManagerDTO;
import com.employee.technical.model.DepartmentsModel;
import com.employee.technical.response.DataResponse;
import com.employee.technical.response.HandlerResponse;
import com.employee.technical.service.DepartmentsService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (Objects.nonNull(departments)) {
            HandlerResponse.responseSuccessOK(response, "Success Add New Department");
        } else {
            HandlerResponse.responseBadRequest(response, "001", "Duplicate Data");
        }
    }

    @GetMapping("/dept-manager")
    public void getDeptManagerByFromDate(HttpServletRequest request, HttpServletResponse response,
                                         @JsonSerialize(using = LocalDateTimeSerializer.class)
                                         @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                                         @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
                                         @RequestParam("fromDate")LocalDate fromDate) {
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
                                         @JsonSerialize(using = LocalDateTimeSerializer.class)
                                         @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                                         @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
                                         @RequestParam("fromDate")LocalDate fromDate) {
        List<DeptEmpDTO> deptEmpDTOS = departmentsService.getDeptEmpByFromDate(fromDate);
        if (Objects.nonNull(deptEmpDTOS)) {
            DataResponse<List<DeptEmpDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(deptEmpDTOS);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }

}
