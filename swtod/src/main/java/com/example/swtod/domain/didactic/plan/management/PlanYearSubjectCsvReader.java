package com.example.swtod.domain.didactic.plan.management;

import com.example.swtod.configs.csv.CsvReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Configuration
public class PlanYearSubjectCsvReader implements CsvReader {
    @Override
    public List<List<String>> writeDataFromFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        List<List<String>> records;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            records = readRecordsFromFile(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
