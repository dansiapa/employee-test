package com.employee.technical.controller;

import com.employee.technical.dto.EmployeesDTO;
import com.employee.technical.model.*;
import com.employee.technical.response.HandlerResponse;
import com.employee.technical.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/other", produces = {"application/json"})
public class OtherController {
    @Autowired
    private OtherService otherService;

    @PostMapping("/add-titles")
    public void addNewTitles(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody TitlesModel model) {
        TitlesModel titles = otherService.addNewTitles(model);
        if (Objects.nonNull(titles)) {
            HandlerResponse.responseSuccessOK(response, "Success add new Titles");
        } else {
            HandlerResponse.responseBadRequest(response, "005", "Something Wrong");
        }
    }

    @PostMapping("/add-salary")
    public void addNewSalaries(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody SalariesModel model) {
        SalariesModel salaries = otherService.addNewSalaries(model);
        if (Objects.nonNull(salaries)) {
            HandlerResponse.responseSuccessOK(response, "Success add new Titles");
        } else {
            HandlerResponse.responseBadRequest(response, "005", "Something Wrong");
        }
    }

    @PostMapping("/add-dept-emp")
    public void addNewDeptEmp(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody DeptEmpModel model) {
        DeptEmpModel deptEmpModel = otherService.addNewDeptEmp(model);
        if (Objects.nonNull(deptEmpModel)) {
            HandlerResponse.responseSuccessOK(response, "Success add new Titles");
        } else {
            HandlerResponse.responseBadRequest(response, "005", "Something Wrong");
        }
    }

    @PostMapping("/add-dept-manager")
    public void addNewDeptManager(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody DeptManagerModel model) {
        DeptManagerModel deptManagerModel = otherService.addNewDeptManager(model);
        if (Objects.nonNull(deptManagerModel)) {
            HandlerResponse.responseSuccessOK(response, "Success add new Titles");
        } else {
            HandlerResponse.responseBadRequest(response, "005", "Something Wrong");
        }
    }
}
