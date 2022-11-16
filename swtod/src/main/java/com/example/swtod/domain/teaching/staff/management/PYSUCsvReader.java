package com.example.swtod.domain.teaching.staff.management;

import com.example.swtod.common.csv.CsvReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class PYSUCsvReader implements CsvReader {
    @Override
    public List<List<String>> writeDataFromFile(MultipartFile file) throws IOException {
        return null;
    }
}
