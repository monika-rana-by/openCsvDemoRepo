package com.opencsvdemo.application.opencsv.mappingStrategy;

import com.opencsvdemo.application.opencsv.annotationbased.WorkType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressNew {

     int doorNO;
     String destination;
     WorkType workType;
}
