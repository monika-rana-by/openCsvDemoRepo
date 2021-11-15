package com.opencsvdemo.application.opencsv.mappingStrategy;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.*;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class readCsvUsingMappingStrategy {
    public final static String[] STUDENT_NEW_COLUMNS = {"name","address.destination"};
    public static final Class<StudentNew> STUDENT_NEW_CLASS = StudentNew.class;


        public static void readDataLineByLine() throws FileNotFoundException {
            log.info("starting time in lillis-{}",System.currentTimeMillis());
            CSVReader reader = new CSVReader(new FileReader("C:\\MONIKA\\Services\\Practice\\shoppingapp\\shoppingapp\\src\\main\\resources\\Book4.csv"));
            CsvToBean beans = new CsvToBeanBuilder<StudentNew>(new FileReader("C:\\MONIKA\\Services\\Practice\\shoppingapp\\shoppingapp\\src\\main\\resources\\Book4.csv"))
                                        .withType(StudentNew.class)
                                        .withThrowExceptions(false)
                                        .withIgnoreQuotations(true)
                                        .withIgnoreLeadingWhiteSpace(true)
                                        .withMappingStrategy(setColumMapping(STUDENT_NEW_CLASS, STUDENT_NEW_COLUMNS)).withSkipLines(1)
                                        .build();

            Stream<StudentNew> StudentsList = beans.parse().stream();
            StudentsList.forEach(ele ->{
                System.out.println(ele);
            });



//            CsvToBean<StudentNew> beans = new CsvToBeanBuilder<StudentNew>(new FileReader("C:\\MONIKA\\Services\\Practice\\shoppingapp\\shoppingapp\\src\\main\\resources\\Book4.csv"))
//                                        .withType(StudentNew.class)
//                                        .withThrowExceptions(false)
//                                        .withIgnoreQuotations(true)
//                                        .withIgnoreLeadingWhiteSpace(true)
//                                        .withMappingStrategy(new PersonMappingStrategy()).withSkipLines(1)
//                                        .build();
//
//
//            Stream<StudentNew> StudentsList = beans.parse().stream();
//            StudentsList.forEach(ele ->{
//                System.out.println(ele);
//            });
//            log.info("ending time in millis - {}",System.currentTimeMillis());
//
//            CSVReader csvReader = null;
//            try {
//                csvReader = new CSVReader(new FileReader
//                        ("D:\\EclipseWorkSpace\\CSVOperations\\StudentData.csv"));
//            }
//            catch (FileNotFoundException e) {
//
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            CsvToBean csvToBean = new CsvToBean();
//
//            // call the parse method of CsvToBean
//            // pass strategy, csvReader to parse method
//            List<StudentNew> list = csvToBean.parse(strategy, csvReader);
    }

    public static ColumnPositionMappingStrategy setColumMapping(Class mappedClass, String[] mappedColumns) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(mappedClass);
        strategy.setColumnMapping(mappedColumns);
        return strategy;
    }

        public static void main(String[] args) throws FileNotFoundException {
        readDataLineByLine();
    }

}
class PersonMappingStrategy extends ColumnPositionMappingStrategy {

    public PersonMappingStrategy() {
        this.setType(StudentNew.class);
    }

    @Override
    public Object populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException, CsvConstraintViolationException, CsvValidationException {
        StudentNew person = new StudentNew();
        person.setName(line[0]);
        person.setRollNo(Integer.parseInt(line[1].trim()));
        person.setDepartment(line[2]);
        person.setResult(line[3]);
        person.setInterestList(Arrays.stream(line[5].split("\\|")).collect(Collectors.toList()));
        AddressNew address = new AddressNew();
        address.setDoorNO(Integer.parseInt(line[4].trim()));
        person.setAddress(address);
        return person;

    }

}
