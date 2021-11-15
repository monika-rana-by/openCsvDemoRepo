package com.opencsvdemo.application.opencsv.annotationbased;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class ReadCSVFile {
    public static void readDataLineByLine() throws FileNotFoundException {
        List<ExceptionsClass> list = new ArrayList<>();
        try {
            CsvToBean<Student> beans = new CsvToBeanBuilder<Student>(new FileReader("C:\\MONIKA\\Services\\Practice\\shoppingapp\\OpenCsvDemoApplication\\src\\main\\resources\\Book3.csv"))
                    .withType(Student.class).withThrowExceptions(false).withIgnoreQuotations(true).build();
            Stream<Student> StudentsList = beans.parse().stream();
            StudentsList.forEach(ele ->{
                System.out.println(ele);
            });

            Map<Long, List<CsvException>> postsPerType = beans.getCapturedExceptions().stream()
                    .collect(groupingBy(CsvException::getLineNumber));
            postsPerType.entrySet().forEach(entrySet ->{
                list.add(new ExceptionsClass(entrySet.getKey(), entrySet.getValue().stream().map(element -> element.getCause() != null ? element.getCause().getLocalizedMessage(): element.getMessage()).collect(Collectors.toList())));
            });
            System.out.println("printing the exception class"+list);

        } catch (Exception ex) {
            log.info("exception occured", ex);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        readDataLineByLine();
    }
}
