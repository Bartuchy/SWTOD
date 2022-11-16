package com.example.swtod.common.csv;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface CsvReader {
    String SEMICOLON_DELIMITER = ";";
    List<List<String>> writeDataFromFile(MultipartFile file) throws IOException;

    default List<List<String>> readRecordsFromFile(BufferedReader br) throws IOException {
        return br
                .lines()
                .skip(1)
                .map(record -> Arrays
                        .stream(record.split(SEMICOLON_DELIMITER))
                        .toList())
                .toList();
    }
}
