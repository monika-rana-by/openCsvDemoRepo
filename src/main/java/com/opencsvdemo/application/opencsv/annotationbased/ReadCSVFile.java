package com.opencsvdemo.application.opencsv.annotationbased;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class ReadCSVFile {
    public static void readDataLineByLine(MultipartFile file){
        List<ExceptionsClass> list = new ArrayList<>();
        try {

            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CsvToBean<Student> beans = new CsvToBeanBuilder<Student>(br)
                                        .withType(Student.class)
                                        .withThrowExceptions(false)
                                        .withIgnoreQuotations(true)
                                        .withIgnoreLeadingWhiteSpace(true)
                                        .build();
            List<Student> studentsList = beans.parse();
            log.info("printing the data successfully read from csv file - {}", studentsList);
            beans.getCapturedExceptions().stream().forEach(exception ->{
                list.add(new ExceptionsClass(exception.getLineNumber(), exception.getCause() != null ? exception.getCause().getLocalizedMessage(): exception.getMessage()));
            });
            System.out.println("printing the exception class"+list);
            writeToCsv(list);
        } catch (Exception ex) {
            log.info("exception occured", ex);
        }
    }

    private static void writeToCsv(List<ExceptionsClass> list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        final String CSV_LOCATION = "Employees.csv ";
        CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_LOCATION), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
        String[] columns = new String[]
                { "lineNo","exceptionMsg" };
        csvWriter.writeNext(columns);
        ColumnPositionMappingStrategy mappingStrategy=
                new ColumnPositionMappingStrategy();
        mappingStrategy.setType(ExceptionsClass.class);

        mappingStrategy.setColumnMapping(columns);
        StatefulBeanToCsvBuilder<ExceptionsClass> builder=
                new StatefulBeanToCsvBuilder(csvWriter);
        StatefulBeanToCsv beanWriter =
                builder.withMappingStrategy(mappingStrategy).build();
        beanWriter.write(list);
        csvWriter.close();
    }

}
