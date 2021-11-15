package com.opencsvdemo.application.opencsv.mappingStrategy;

import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class StudentNew {
    private static final long serialVersionUID = 1L;

    private int rollNo;
    private String name;
    private String department;
    private String result;
//    private String pointer;
//    private LocalDate date;
//    @CsvRecurse
    private AddressNew address;
    private List<String> interestList;
}
