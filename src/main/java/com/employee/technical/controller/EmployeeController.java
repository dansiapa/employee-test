package com.employee.technical.controller;

import com.employee.technical.dto.DetailEmployeeDTO;
import com.employee.technical.dto.EmployeesDTO;
import com.employee.technical.model.EmployeesModel;
import com.employee.technical.response.DataResponse;
import com.employee.technical.response.HandlerResponse;
import com.employee.technical.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add-employee")
    public void addNewDepartment(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody EmployeesModel model) {
        EmployeesModel employee = employeeService.addNewEmployee(model);
        EmployeesDTO get = employeeService.getEmployee(model.getEmpNo());
        if (!model.getGender().equals("M") && !model.getGender().equals("F")) {
            HandlerResponse.responseBadRequest(response, "003", "Please input Gender only M or F");
        } else if (Objects.nonNull(get)) {
            HandlerResponse.responseBadRequest(response, "001", "Duplicate Data");
        } else if (Objects.nonNull(employee)) {
            HandlerResponse.responseSuccessOK(response, "Success add new Employee");
        }
    }

    @GetMapping("/all-employees")
    public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeesDTO> employeesDTOS = employeeService.getAllEmployees();
        DataResponse<List<EmployeesDTO>> dataResponse = new DataResponse<>();
        dataResponse.setData(employeesDTOS);
        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }

    @GetMapping("/get-employee")
    public void getEmployee(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("no")Integer no) {
        EmployeesDTO employeesDTO = employeeService.getEmployee(no);
        if (Objects.nonNull(employeesDTO)) {
            DataResponse<EmployeesDTO> dataResponse = new DataResponse<>();
            dataResponse.setData(employeesDTO);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }

    @PutMapping("/update-employee")
    public void updateEmployee(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody EmployeesModel item) {
        EmployeesModel employee = employeeService.updateEmployee(item);
        if (Objects.nonNull(employee)) {
            HandlerResponse.responseSuccessOK(response, "Success Update Employee");
        } else {
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }

    @GetMapping("/get-employee-detail")
    public void getDetailEmployee(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("no")Integer no) {
        DetailEmployeeDTO detailEmployeeDTO = employeeService.getDetailEmployee(no);
        if (Objects.nonNull(detailEmployeeDTO)) {
            DataResponse<DetailEmployeeDTO> dataResponse = new DataResponse<>();
            dataResponse.setData(detailEmployeeDTO);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response,"002","Data Not Found");
        }
    }
}
