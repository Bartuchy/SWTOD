package com.example.swtod.common.csv;

import com.example.swtod.domain.teaching.staff.dto.ReportDto;
import com.example.swtod.domain.teaching.staff.dto.ReportRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {
    public ByteArrayInputStream tutorialsToCSV(ReportDto reportDto) {
        List<String> columnHeaders = Arrays.asList("Nazwa przedmiotu", "Wydział", "Kierunek", "Semestr",
                "liczba tyg.", "W godz.tyg.", "W nr.gr.", "C godz.tyg.", "C nr.gr.", "L godz.tyg.", "L nr.gr.",
                "P godz.tyg.", "P nr.gr.", "S godz.tyg.", "S nr.gr.", "godz. w roku");

        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL).withDelimiter(';');
        //final CSVFormat format = CSVFormat.DEFAULT.withHeader(columnHeaders.toArray(new String[]{})).withDelimiter(';');
        List<ReportRecord> reportRecords = reportDto.getReportRecords();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            List<List<String>> userData = new ArrayList<>();
            userData.add(Arrays.asList("Prowadzący", reportDto.getUserNameSurname()));
            userData.add(Arrays.asList("Stanowisko", reportDto.getPositionName()));
            userData.add(Arrays.asList("Pensum", String.valueOf(reportDto.getPensum())));

            for (List<String> record: userData) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.printRecord(columnHeaders);
            for (ReportRecord reportRecord : reportRecords) {
                List<String> data = Arrays.asList(
                        reportRecord.getSubjectName(),
                        reportRecord.getFacultyName(),
                        reportRecord.getFieldOfStudiesName(),
                        String.valueOf(reportRecord.getSemesterNumber()),
                        String.valueOf(reportRecord.getNoWeeks()),
                        String.valueOf(reportRecord.getLectureHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerLecture()),
                        String.valueOf(reportRecord.getExerciseHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerExercise()),
                        String.valueOf(reportRecord.getLaboratoryHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerLaboratory()),
                        String.valueOf(reportRecord.getProjectHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerProject()),
                        String.valueOf(reportRecord.getSeminaryHoursPerWeek()),
                        String.valueOf(reportRecord.getGroupsPerSeminary()),
                        String.valueOf(reportRecord.getTotalHoursInYear())
                );
                csvPrinter.printRecord(data);
            }

            List<List<String>> summaryData = new ArrayList<>();
            summaryData.add(Arrays.asList("Razem stacjonarne", String.valueOf(reportDto.getTotalSTHours())));
            summaryData.add(Arrays.asList("Razem niestacjonarne", String.valueOf(reportDto.getTotalNSHours())));
            summaryData.add(Arrays.asList("Ogolem w roku", String.valueOf(reportDto.getTotalHours())));

            for (List<String> record: summaryData) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
