package com.employee.technical.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "departments")
public class DepartmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no")
    private String deptNo;
    @Column(name = "dept_name")
    private String deptName;

}
