package com.example.swtod.configs.csv;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CsvReader {
    String SEMICOLON_DELIMITER = ";";
    List<List<String>> writeDataFromFile(MultipartFile file) throws IOException;

}
