package com.opencsvdemo.application.opencsv.annotationbased;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Data
@ToString
public class Student {
    private static final long serialVersionUID = 1L;

    @CsvBindByName(column = "rollNo_id")
    private String rollNo;
    @CsvBindByName(column = "name_id")
    private String name;
    @CsvBindByName(column = "department_id")
    private String department;
    @CsvBindByName(column = "result_id")
    private String result;
    @CsvBindByName(column = "pointer_id")
    private String pointer;
    @CsvDate("yyyy-MM-dd")
    @CsvBindByName(column = "date_id")
    private LocalDate date;
    @CsvRecurse
    private Address address;
    @CsvBindAndSplitByName(column = "interest_list", required = true, elementType = String.class, splitOn = "\\|")
    private List<String> interestList;
}
