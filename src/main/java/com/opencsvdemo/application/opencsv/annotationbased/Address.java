package com.opencsvdemo.application.opencsv.annotationbased;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {

    @CsvBindByName(column ="doorNo_id",required = true)
    private int doorNO;
    @CsvBindByName(column ="destination_id")
    private String destination;
    @CsvBindByName(column="work_type")
    private WorkType workType;
}
