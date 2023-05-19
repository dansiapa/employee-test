package com.employee.technical.dto;

import com.employee.technical.model.DeptEmpModel;
import com.employee.technical.model.DeptManagerModel;
import com.employee.technical.model.SalariesModel;
import com.employee.technical.model.TitlesModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class DetailEmployeeDTO {
    private Integer no;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate bornDate;
    private String name;
    private String gender;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate hireAt;
    private List<SalariesModel> salaries;
    private List<TitlesModel> titles;
    private List<DeptManagerModel> deptManager;
    private List<DeptEmpModel> deptEmp;
}
