package com.opencsvdemo.application.opencsv.annotationbased;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

class ReadCSVFileTest {

    ReadCSVFile readCSVFile = new ReadCSVFile();
    @Test
    void readDataLineByLine() throws IOException {
        InputStream inputStream  = getClass().getResourceAsStream("/Student.csv");
        MultipartFile multipartFile = new MockMultipartFile("Customer",
                "", "text/csv", IOUtils.toByteArray(inputStream));
        readCSVFile.readDataLineByLine(multipartFile);

    }
}