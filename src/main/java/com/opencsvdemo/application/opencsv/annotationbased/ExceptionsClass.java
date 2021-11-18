package com.opencsvdemo.application.opencsv.annotationbased;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionsClass {
    private Long lineNo;
    private String exceptionMsg;
}
